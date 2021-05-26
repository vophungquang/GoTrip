package com.example.seminarvpquang.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.seminarvpquang.R;
import com.example.seminarvpquang.activity.CartActicity;
import com.example.seminarvpquang.model.Cart;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class CartApdapter extends BaseAdapter {
    int x = 0;
    private CartActicity context;
    private int layout;
    private List<Cart> arraListProduct;

    public CartApdapter(CartActicity context, int layout, List<Cart> subjectsList) {
        this.context = context;
        this.layout = layout;
        this.arraListProduct = subjectsList;
    }

    @Override
    public int getCount() {
        return arraListProduct.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        CheckBox checkBox;
        public TextView textViewNameSanPham, textViewPrice, textViewThuongHieu,textViewSanPhamConLai,txtsoluongdamua;
        public ImageView imageViewSanPham;
        public Button btnCong,btnTru;
        LinearLayout dongcartne;

    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        CartApdapter.ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new CartApdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            viewHolder.checkBox=view.findViewById(R.id.checkboxCart);
            viewHolder.imageViewSanPham = view.findViewById(R.id.imageHinhSanPham);
            viewHolder.textViewNameSanPham = view.findViewById(R.id.textViewNameSanPham);
            viewHolder.textViewThuongHieu =  view.findViewById(R.id.textviewThuonghieu);
            viewHolder.textViewPrice = view.findViewById(R.id.textviewGiaSanPham);
            viewHolder.textViewSanPhamConLai = view.findViewById(R.id.textViewSanPhamConLai);
            viewHolder.txtsoluongdamua      =view.findViewById(R.id.textviewSoluongdamua);
            viewHolder.btnCong              =view.findViewById(R.id.btnCongCart);
            viewHolder.btnTru               =view.findViewById(R.id.btntruCart);
            viewHolder.dongcartne       =view.findViewById(R.id.dongcartne);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final Cart cart = (Cart) arraListProduct.get(i);
        viewHolder.textViewNameSanPham.setText(cart.getTensanpham());
        viewHolder.textViewThuongHieu.setText(cart.getTenhuonghieu());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.textViewPrice.setText("Price : " + decimalFormat
                .format(cart.getGiasanpham()) + " VND");
        viewHolder.textViewSanPhamConLai.setText("Còn lại " + cart.getSosanphamtonkho()+" sản phẩm");
        viewHolder.txtsoluongdamua.setText(cart.getSoluong()+"");

        Picasso.get().load(cart.getImageSanPham())
                .into(viewHolder.imageViewSanPham);



//        if(context.checkCheckBoxTatCa(1))
//        {
//            viewHolder.checkBox.setChecked(false);
//        }


        if(context.checkCheckBoxTatCaIsTrue())
        {
            viewHolder.checkBox.setChecked(true);
        }

        if(context.checkCheckBoxTatCaIsFalse())
        {
            viewHolder.checkBox.setChecked(false);
        }




        ViewHolder finalViewHolder2 = viewHolder;
        finalViewHolder2.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soluong= finalViewHolder2.txtsoluongdamua.getText().toString();
                int soluongdadoi=Integer.parseInt(soluong);
                int giuso=soluongdadoi;
                soluongdadoi=soluongdadoi+1;
                int sotruyen=soluongdadoi-giuso;
                finalViewHolder2.txtsoluongdamua.setText(""+soluongdadoi);
                context.TongTien(null,cart.getGiasanpham()*sotruyen,0);
                Log.e("phong","id don hang la : "+cart.getId());
                Log.e("phong","so luong don hang la : "+soluongdadoi);
                context.CapNhatDonHang(cart.getId(),soluongdadoi);

            }
        });

        finalViewHolder2.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String soluong = finalViewHolder2.txtsoluongdamua.getText().toString();
                int soluongdadoi = Integer.parseInt(soluong);
                int giuso = soluongdadoi;
                if (soluongdadoi > 0) {
                    soluongdadoi = soluongdadoi - 1;

                    if (soluongdadoi > 0) {
                        int sotruyen = giuso - soluongdadoi;
                        finalViewHolder2.txtsoluongdamua.setText("" + soluongdadoi);
                        context.TongTien(null, 0, cart.getGiasanpham() * sotruyen);
                        Log.e("phong", "id don hang la : " + cart.getId());
                        Log.e("phong", "so luong don hang la : " + soluongdadoi);
                        context.CapNhatDonHang(cart.getId(), soluongdadoi);
                    }
                    else
                    {

                        AlertDialog.Builder dialogXoa=new AlertDialog.Builder(context);
                        dialogXoa.setMessage("Ban co muon xoa san phẩm  "+cart.getTensanpham()+ " này ra khỏi đơn hàng khong?");
                        dialogXoa.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                context.deleteMonHoc(cart.getId(),cart.getGiasanpham());
                                context.TongTien(null, 0, cart.getGiasanpham() );

                            }
                        });
                        dialogXoa.setNegativeButton("khong", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        dialogXoa.show();
                    }


                }
            }
        });


        ViewHolder finalViewHolder = viewHolder;
        viewHolder.dongcartne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalViewHolder.checkBox.isChecked()) {
                    finalViewHolder.checkBox.setChecked(false);
                    context.isFalseTatCa();
                    context.TongTien(null, 0, cart.getGiasanpham() * cart.getSoluong());
                } else {
                    context.isTrueTatCa(1);
                    finalViewHolder.checkBox.setChecked(true);
                    context.TongTien(null, cart.getGiasanpham() * cart.getSoluong(), 0);

                }

            }
        });

        ViewHolder finalViewHolder1 = viewHolder;
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalViewHolder1.checkBox.isChecked())
                {
                    context.isTrueTatCa(1);
                    context.TongTien(null, cart.getGiasanpham() * cart.getSoluong(), 0);
                }
                else
                {
                    context.isFalseTatCa();
                    context.TongTien(null, 0, cart.getGiasanpham() * cart.getSoluong());

                }

            }
        });





        return view;
    }



}