package com.example.seminarvpquang.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.seminarvpquang.R;
import com.example.seminarvpquang.activity.ProductDetailActivity;
import com.example.seminarvpquang.activity.TrousersActivity;
import com.example.seminarvpquang.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class TrousersAdapter extends BaseAdapter {
    int x = 0;
    private TrousersActivity context;
    private int layout;
    private List<Product> arraListProduct;

    public TrousersAdapter(TrousersActivity context, int layout, List<Product> subjectsList) {
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
        public TextView textViewNamePhone, textViewPricePhone, textViewDescriptionPhone;
        public ImageView imageViewPhone;
        public LinearLayout linearLayoutPhone;

    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        TrousersAdapter.ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new TrousersAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            viewHolder.textViewNamePhone = view.findViewById(R.id.textViewNamePhone);
            viewHolder.textViewPricePhone = view.findViewById(R.id.textViewPricePhone);
            viewHolder.textViewDescriptionPhone =  view.findViewById(R.id.textViewDescriptionPhone);
            viewHolder.imageViewPhone = view.findViewById(R.id.imageViewPhone);
            viewHolder.linearLayoutPhone = view.findViewById(R.id.linealayoutItem);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final Product product = (Product) arraListProduct.get(i);
        viewHolder.textViewNamePhone.setText(product.getNameProduct());

        viewHolder.textViewNamePhone.setSelected(true);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.textViewPricePhone.setText("Price : " + decimalFormat
                .format(product.getPriceProduct()) + " VND");
        viewHolder.textViewDescriptionPhone.setMaxLines(2);
        viewHolder.textViewDescriptionPhone.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.textViewDescriptionPhone.setText(product.getDescriptionProduct());
        Picasso.get().load(product.getImageProduct())
                .into(viewHolder.imageViewPhone);
        viewHolder.linearLayoutPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("information",product);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return view;
    }


}
