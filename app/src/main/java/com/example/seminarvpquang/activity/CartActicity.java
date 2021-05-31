package com.example.seminarvpquang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.seminarvpquang.R;
import com.example.seminarvpquang.adapter.CartApdapter;
import com.example.seminarvpquang.model.Cart;
import com.example.seminarvpquang.ultil.CheckConnection;
import com.example.seminarvpquang.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartActicity extends AppCompatActivity {
    CheckBox checkBoxTatCa;
    Button btnDiscount,btnMuaHang;
    ImageView imgLogo,iconvanchuyen,imageIconxetaine;
    TextView Tongtiensanpham;
    ArrayList<Integer> mangidDiscount;
    ArrayList<Cart> cartArrayList;
    ArrayList<Cart> cartArrayListFiler;
    ListView listViewCart;
    CartApdapter cartApdapter;
    int REQUEST_CODE_DISCOUNT=123;
    int k=0;

    int idUsers;
    int tongtienphaitra=0;
    public static String a="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_acticity);
        mangidDiscount=new ArrayList<>();
        Intent intent=getIntent();
        idUsers=    intent.getIntExtra("idUser",123);

        AnhXa();


        checkBoxTatCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxTatCa.isChecked())
                {

                    checkCheckBoxTatCaIsTrue();
                    GetDataCart();
                }
                else
                {

                    checkCheckBoxTatCaIsFalse();
                    GetDataCart();
                }

            }
        });

        btnDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tongtien=Tongtiensanpham.getText().toString().trim();
                Log.e("vpq","tong tien la : "+tongtien);
                int tien=Integer.parseInt(tongtien);
                if(tien!=0) {
                    Intent intent1 = new Intent(CartActicity.this, DiscountActivity.class);

                    startActivityForResult(intent1, REQUEST_CODE_DISCOUNT);
                }
                else if(cartArrayListFiler.size()==0)
                {
                    Toast.makeText(CartActicity.this, "giỏ hàng của bạn rỗng ", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(CartActicity.this, "bạn chưa check món hàng", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=Tongtiensanpham.getText().toString();
                int tongtien=Integer.parseInt(a);

                if(cartArrayListFiler.size()==0)
                {
                    Toast.makeText(CartActicity.this, "giỏ hàng của bạn rỗng ", Toast.LENGTH_SHORT).show();
                }
                else if(tongtien==0)
                {
                    Toast.makeText(CartActicity.this, "Bạn chưa có mặt hàng nào", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    muaHang();
                }

            }
        });


    }
    private void Dialog_chinhsua(){

    }
    private void muaHang(){
        String tien=Tongtiensanpham.getText().toString();
        int tongtien=Integer.parseInt(tien);
        Intent intent=new Intent(CartActicity.this,ConfirmCart.class);
        intent.putExtra("idUser",idUsers);
        intent.putExtra("tongtienphaitra",tongtien);
        startActivity(intent);
        finish();

    }

    public void isFalseTatCa()
    {
        checkBoxTatCa.setChecked(false);
    }
    public void isTrueTatCa(int x)
    {
        k=k+x;
        if ((cartArrayListFiler.size())==k)
        {
            checkBoxTatCa.setChecked(true);
            k=0;
        }

    }
    private  void AnhXa(){
        imgLogo=findViewById(R.id.imageLogo);
        imgLogo.setImageResource(R.drawable.iconshop);
        iconvanchuyen=findViewById(R.id.imageIconxetai);
        imageIconxetaine=findViewById(R.id.imageIconxetaine);
        iconvanchuyen.setImageResource(R.drawable.vanchuyen);
        imageIconxetaine.setImageResource(R.drawable.vanchuyen);
        listViewCart    =findViewById(R.id.listviewCart);
        Tongtiensanpham=findViewById(R.id.Tongtiensanpham);
        checkBoxTatCa=findViewById(R.id.checkboxTatCa);
        cartArrayList   =new ArrayList<>();
        cartArrayListFiler=new ArrayList<>();
        GetDataCart();
        cartApdapter =new CartApdapter(this,R.layout.dong_cart,cartArrayListFiler);
        listViewCart.setAdapter(cartApdapter);
        btnDiscount=findViewById(R.id.btnDiscount);
        btnMuaHang=findViewById(R.id.btnMuaHang);
    }



    public boolean checkCheckBoxTatCaIsTrue()
    {
        if(checkBoxTatCa.isChecked())
        {
            int x=0;
            for (int i=0;i<cartArrayListFiler.size();i++)
            {
                x+=cartArrayListFiler.get(i).getGiasanpham()*cartArrayListFiler.get(i).getSoluong();
            }
            Tongtiensanpham.setText(x+"");

            return true;
        }


        return false;


    }
    public boolean checkCheckBoxTatCaIsFalse()
    {
        if(!checkBoxTatCa.isChecked())
        {
            String a=Tongtiensanpham.getText().toString();
            int x=Integer.parseInt(a);
            for (int i=0;i<cartArrayListFiler.size();i++)
            {
                x=0;
            }
            Tongtiensanpham.setText(x+"");

            return true;
        }


        return false;


    }

    public void TongTien(Cart cart,int cong,int tru){
        String a=Tongtiensanpham.getText().toString();
        int tongtien=Integer.parseInt(a);

        if (cong != 0) {
            tongtien = tongtien + cong;
            tongtienphaitra=tongtien;
            Tongtiensanpham.setText("" + tongtien);
        } else if (tru != 0) {

            tongtien = tongtien - tru;
            tongtienphaitra=tongtien;
            Tongtiensanpham.setText("" + tongtien);

        }
        if (cart != null) {
            tongtien = tongtien + cart.getGiasanpham() * cart.getSoluong();
            tongtienphaitra=tongtien;
            Tongtiensanpham.setText("" + tongtien);
        }


    }
    public   void deleteMonHoc( int idDonhang,int giasanpham)
    {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.pathXoaDonHang,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {

                            Toast.makeText(CartActicity.this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                            GetDataCart();
                        }
                        else
                        {
                            Toast.makeText(CartActicity.this, "Loi Xoa", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CartActicity.this, "Da xay ra loi", Toast.LENGTH_SHORT).show();
                        Log.d("aaa","Loi!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();
                params.put("iddonhang",String.valueOf(idDonhang));


                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


    public void CapNhatDonHang(int iddonhang,int soluongitem){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.pathCapNhatDonHang,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {
                            Toast.makeText(CartActicity.this, "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Toast.makeText(CartActicity.this, "Loi cap nhat", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CartActicity.this, "Da xay ra loi", Toast.LENGTH_SHORT).show();
                        Log.d("aaa","Loi!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Log.e("phong","id don hang la"+iddonhang);
                Log.e("phong","soluong don hang la"+soluongitem);
                Map<String,String> params=new HashMap<>();
                params.put("iddonhang",String.valueOf(iddonhang));
                params.put("soluong",String.valueOf(soluongitem));


                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


    public void GetDataCart() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathDonHang, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                cartArrayList.clear();
                cartArrayListFiler.clear();
                if (response != null){
                    for (int i = 0; i < response.length(); i++){
                        try {
                            int id=0;
                            int idUser=0;
                            String tensanpham="";
                            int soluong=0;
                            String ngaymuahang="";
                            int giasanpham=0;
                            String tenthuonghieu="";
                            int sosanphamtonkho=0;
                            String hinhanhsanpham="";
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            idUser = jsonObject.getInt("idUser");
                            tensanpham = jsonObject.getString("tensanpham");
                            soluong = jsonObject.getInt("soluong");
                            ngaymuahang = jsonObject.getString("ngaymuahang");
                            giasanpham = jsonObject.getInt("giasanpham");
                            tenthuonghieu = jsonObject.getString("tenthuonghieu");

                            sosanphamtonkho = jsonObject.getInt("sosanphamtonkho");
                            hinhanhsanpham = jsonObject.getString("hinhanhsanpham");
                            cartArrayList.add(new Cart(id,idUser,tensanpham,soluong,ngaymuahang,giasanpham,tenthuonghieu,sosanphamtonkho,hinhanhsanpham));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                    for (int j=0;j<cartArrayList.size();j++)
                    {

                        if(idUsers==cartArrayList.get(j).getIdUser())
                        {
                            Cart cart=cartArrayList.get(j);
                            cartArrayListFiler.add(new Cart(cart.getId(),cart.getIdUser(),cart.getTensanpham(),cart.getSoluong(),cart.getNgaymuahang(),cart.getGiasanpham(),cart.getTenhuonghieu(),
                                    cart.getSosanphamtonkho(),cart.getImageSanPham()));

                        }

                    }
                    int x=0;
                    for(int i=0;i<cartArrayListFiler.size()-1;i++)
                    {
                        x= cartArrayListFiler.get(i).getSoluong();
                        for(int j=cartArrayListFiler.size()-1;j>i;j--)
                        {
                            if(cartArrayListFiler.get(i).getTensanpham().equals(cartArrayListFiler.get(j).getTensanpham()))
                            {
                                x=x+cartArrayListFiler.get(j).getSoluong();
                                cartArrayListFiler.get(i).setSoluong(x);
                                cartArrayListFiler.remove(j);

                            }
                        }
                    }
                    cartApdapter.notifyDataSetChanged();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(), error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_DISCOUNT && resultCode == RESULT_OK && data != null) {
            int discount = data.getIntExtra("discount",1234);
            String tongtien=Tongtiensanpham.getText().toString();
            int tongtienphaitra=Integer.valueOf(tongtien);
            tongtienphaitra=tongtienphaitra-discount;
            Tongtiensanpham.setText(tongtienphaitra+"");



        }

    }


}
