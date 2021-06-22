package com.example.seminarvpquang.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seminarvpquang.R;
import com.example.seminarvpquang.activity.HomeActivity;
import com.example.seminarvpquang.activity.HaNoiActivity;
import com.example.seminarvpquang.activity.DaLatActivity;
import com.example.seminarvpquang.activity.SaPaActivity;
import com.example.seminarvpquang.activity.SaiGonActivity;
import com.example.seminarvpquang.activity.HueActivity;
import com.example.seminarvpquang.activity.DaNangActivity;
import com.example.seminarvpquang.model.PlaceType;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlaceTypeAdapter1 extends RecyclerView.Adapter<PlaceTypeAdapter1.ViewHolder>  {

    ArrayList<PlaceType> datashops;
    HomeActivity context;
    static int i=0;
    public PlaceTypeAdapter1(ArrayList<PlaceType> datashops, HomeActivity context) {
        this.datashops = datashops;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView=layoutInflater.inflate(R.layout.phanloai,parent,false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtNameType.setText(datashops.get(position).getnamePlace());
        Picasso.get().load(datashops.get(position).getimagePlace()).into(holder.imgHinhAnh);
        PlaceType placeType =datashops.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (position)
                {
                    case 0:{
                        Intent intent = new Intent(context, SaiGonActivity.class);
                        intent.putExtra("idPlaceType", placeType.getId());
                        context.startActivity(intent);
                        break;
                    }
                    case 1:{
                        Intent intent = new Intent(context, HaNoiActivity.class);
                        intent.putExtra("idPlaceType", placeType.getId());
                        context.startActivity(intent);
                        break;
                    }
                    case 2:{
                        Intent intent = new Intent(context, DaLatActivity.class);
                        intent.putExtra("idPlaceType", placeType.getId());
                        context.startActivity(intent);
                        break;
                    }
                    case 3:{
                        Intent intent = new Intent(context, HueActivity.class);
                        intent.putExtra("idPlaceType", placeType.getId());
                        context.startActivity(intent);
                        break;
                    }
                    case 4:{
                        Intent intent = new Intent(context, SaPaActivity.class);
                        intent.putExtra("idPlaceType", placeType.getId());
                        context.startActivity(intent);
                        break;
                    }
                    case 5:{
                        Intent intent = new Intent(context, DaNangActivity.class);
                        intent.putExtra("idPlaceType", placeType.getId());
                        context.startActivity(intent);
                        break;
                    }

                }



            }
        });
    }

    @Override
    public int getItemCount() {
        return datashops.size();
    }

    public  void RemoveItem(int position)
    {
        datashops.remove(position);
        notifyItemRemoved(position);
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtNameType;
        ImageView imgHinhAnh;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNameType=itemView.findViewById(R.id.txtNameType);
            imgHinhAnh=itemView.findViewById(R.id.imgHinhAnh);


        }
    }
}
