package com.example.seminarvpquang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.seminarvpquang.R;
import com.example.seminarvpquang.model.PlaceType;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlaceTypeAdapter extends BaseAdapter {

    ArrayList<PlaceType> arrayListPT;
    Context context;

    public PlaceTypeAdapter(ArrayList<PlaceType> arrayListPT, Context context) {
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
        ImageView imageViewPlaceType;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_list_view_place_type,null);
            viewHolder.textViewProductType = (TextView) view.findViewById(R.id.textViewPlaceType);
            viewHolder.imageViewPlaceType = (ImageView) view.findViewById(R.id.imageViewPlaceType);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        PlaceType placeType = (PlaceType) getItem(position);
        viewHolder.textViewProductType.setText(placeType.getnamePlace());
        Picasso.get().load(placeType.getimagePlace()).into(viewHolder.imageViewPlaceType);
        return view;
    }
}

