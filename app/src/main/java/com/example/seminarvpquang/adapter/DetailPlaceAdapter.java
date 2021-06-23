package com.example.seminarvpquang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.seminarvpquang.R;
import com.example.seminarvpquang.activity.PlaceDetailActivity;
import com.example.seminarvpquang.model.Place;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DetailPlaceAdapter extends BaseAdapter {
    int x = 0;
    private PlaceDetailActivity context;
    private int layout;
    private ArrayList<Place> arrayPlace;

    public DetailPlaceAdapter(PlaceDetailActivity context, int layout, ArrayList<Place> subjectsList) {
        this.context = context;
        this.layout = layout;
        this.arrayPlace = subjectsList;
    }

    @Override
    public int getCount() {
        return arrayPlace.size();
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
        public ImageView imageViewPlace;
        public TextView textViewNamePlacet, textViewDoanhThu;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.imageViewPlace =  view.findViewById(R.id.imageViewnew);
            holder.textViewNamePlacet = view.findViewById(R.id.textViewNamenew);
            holder.textViewDoanhThu =  view.findViewById(R.id.textviewdoanhthu);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final Place place = arrayPlace.get(i);
        int x =arrayPlace.get(i).getId();
        holder.textViewNamePlacet.setText(place.getnamePlace());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.textViewDoanhThu.setText("Price : " + decimalFormat
                .format(place.getdoanhthu()) + "VND");
        Picasso.get().load(place.getimagePlace())
                .into(holder.imageViewPlace);
        return view;
    }
}