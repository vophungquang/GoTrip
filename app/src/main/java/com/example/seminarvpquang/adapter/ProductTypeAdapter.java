package com.example.seminarvpquang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.seminarvpquang.R;
import com.example.seminarvpquang.model.ProductType;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductTypeAdapter extends BaseAdapter {

    ArrayList<ProductType> arrayListPT;
    Context context;

    public ProductTypeAdapter(ArrayList<ProductType> arrayListPT, Context context) {
        this.arrayListPT = arrayListPT;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListPT.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListPT.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        TextView textViewProductType;
        ImageView imageViewProductType;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_list_view_product_type,null);
            viewHolder.textViewProductType = (TextView) view.findViewById(R.id.textViewProductType);
            viewHolder.imageViewProductType = (ImageView) view.findViewById(R.id.imageViewProductType);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ProductType productType = (ProductType) getItem(position);
        viewHolder.textViewProductType.setText(productType.getNamePT());
//        Picasso.with(context).load(productType.getImagePT()).into(viewHolder.imageViewProductType);
        Picasso.get().load(productType.getImagePT()).into(viewHolder.imageViewProductType);
        return view;
    }
}

