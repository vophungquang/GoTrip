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
import com.example.seminarvpquang.activity.PlaceDetailActivity;
import com.example.seminarvpquang.model.Place;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PlaceAdapter extends BaseAdapter {
    int x = 0;
    private HomeActivity context;
    private int layout;
    private ArrayList<Place> arrayPlace;

    public PlaceAdapter(Activity context, int layout, ArrayList<Place> subjectsList) {
        this.context = (HomeActivity) context;
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
        public TextView textViewNamePlace,textviewdanhgiandiem,textViewDoanhThu,textViewMoTa;

    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.imageViewPlace =  view.findViewById(R.id.imageViewPlace);
            holder.textViewNamePlace = view.findViewById(R.id.textViewNamePlace);
            holder.textviewdanhgiandiem=view.findViewById(R.id.textviewdanhgiandiem);
            holder.textViewDoanhThu=view.findViewById(R.id.textviewdoanhthu);
            holder.textViewMoTa=view.findViewById(R.id.textviewmota);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final Place place = arrayPlace.get(i);
        holder.textViewNamePlace.setText(place.getnamePlace());
        Picasso.get().load(place.getimagePlace())
                .into(holder.imageViewPlace);
        holder.textviewdanhgiandiem.setText(place.getDiemdanhgia()+"");
        holder.textViewDoanhThu.setText(place.getdoanhthu()+"");
        holder.textViewMoTa.setText(place.getmota()+"");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, PlaceDetailActivity.class);
                intent.putExtra("information", place);
                context.startActivity(intent);
            }
        });

        return view;
    }
}

