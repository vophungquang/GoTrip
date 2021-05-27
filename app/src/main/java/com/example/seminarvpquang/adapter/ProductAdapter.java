package com.example.seminarvpquang.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.seminarvpquang.R;
import com.example.seminarvpquang.activity.HomeActivity;
import com.example.seminarvpquang.activity.ProductDetailActivity;
import com.example.seminarvpquang.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    int x = 0;
    private HomeActivity context;
    private int layout;
    private ArrayList<Product> arrayProduct;

    public ProductAdapter(Activity context, int layout, ArrayList<Product> subjectsList) {
        this.context = (HomeActivity) context;
        this.layout = layout;
        this.arrayProduct = subjectsList;
    }

    @Override
    public int getCount() {
        return arrayProduct.size();
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
        public ImageView imageViewProduct;
        public TextView textViewNameProduct, textViewPriceProduct,textviewdanhgiandiem,textviewSosanphamdaban,textviewsosanphamconlai;

    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.imageViewProduct =  view.findViewById(R.id.imageViewProduct);
            holder.textViewNameProduct = view.findViewById(R.id.textViewNameProduct);
            holder.textViewPriceProduct =  view.findViewById(R.id.textViewPriceProduct);
            holder.textviewdanhgiandiem=view.findViewById(R.id.textviewdanhgiandiem);
            holder.textviewSosanphamdaban=view.findViewById(R.id.textviewSosanphamdaban);
            holder.textviewsosanphamconlai=view.findViewById(R.id.textviewsosanphamconlai);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final Product product = arrayProduct.get(i);

        holder.textViewNameProduct.setText(product.getNameProduct());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.textViewPriceProduct.setText(decimalFormat
                .format(product.getPriceProduct()) + "VND");
//        Picasso.with(context).load(product.getImageProduct())
        Picasso.get().load(product.getImageProduct())
                .into(holder.imageViewProduct);
        holder.textviewdanhgiandiem.setText(product.getDiemdanhgia()+"");
        holder.textviewSosanphamdaban.setText(product.getSosanphamdaban()+"");
        holder.textviewsosanphamconlai.setText(product.getSosanphamcontonkho()+"");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ProductDetailActivity.class);
                intent.putExtra("information",product);
                context.startActivity(intent);
            }
        });

        return view;
    }
}

