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
import com.example.seminarvpquang.activity.LaptopActivity;
import com.example.seminarvpquang.activity.ShirtActivity;
import com.example.seminarvpquang.activity.ShoesActivity;
import com.example.seminarvpquang.activity.SmartPhoneActivity;
import com.example.seminarvpquang.activity.TrousersActivity;
import com.example.seminarvpquang.activity.WatchActivity;
import com.example.seminarvpquang.model.ProductType;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductTypeAdapter1 extends RecyclerView.Adapter<ProductTypeAdapter1.ViewHolder>  {

    ArrayList<ProductType> datashops;
    HomeActivity context;
    static int i=0;
    public ProductTypeAdapter1(ArrayList<ProductType> datashops, HomeActivity context) {
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

        holder.txtNameType.setText(datashops.get(position).getNamePT());
//        Picasso.with(context).load(datashops.get(position).getImagePT()).into(holder.imgHinhAnh);
        Picasso.get().load(datashops.get(position).getImagePT()).into(holder.imgHinhAnh);
        ProductType productType=datashops.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (position)
                {
                    case 0:{
                        Intent intent = new Intent(context, SmartPhoneActivity.class);
                        intent.putExtra("idProductType",productType.getId());
                        context.startActivity(intent);
                        break;
                    }
                    case 1:{
                        Intent intent = new Intent(context, LaptopActivity.class);
                        intent.putExtra("idProductType",productType.getId());
                        context.startActivity(intent);
                        break;
                    }
                    case 2:{
                        Intent intent = new Intent(context, ShirtActivity.class);
                        intent.putExtra("idProductType",productType.getId());
                        context.startActivity(intent);
                        break;
                    }
                    case 3:{
                        Intent intent = new Intent(context, TrousersActivity.class);
                        intent.putExtra("idProductType",productType.getId());
                        context.startActivity(intent);
                        break;
                    }
                    case 4:{
                        Intent intent = new Intent(context, ShoesActivity.class);
                        intent.putExtra("idProductType",productType.getId());
                        context.startActivity(intent);
                        break;
                    }
                    case 5:{
                        Intent intent = new Intent(context, WatchActivity.class);
                        intent.putExtra("idProductType",productType.getId());
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
