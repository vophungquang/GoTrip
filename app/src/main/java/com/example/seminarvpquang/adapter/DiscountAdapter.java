package com.example.seminarvpquang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.example.seminarvpquang.R;
import com.example.seminarvpquang.activity.DiscountActivity;
import com.example.seminarvpquang.model.Discount;

import java.util.ArrayList;

public class DiscountAdapter extends BaseAdapter {
    int x = 0;
    private DiscountActivity context;
    private int layout;
    private ArrayList<Discount> arrayComment;

    public DiscountAdapter(DiscountActivity context, int layout, ArrayList<Discount> arrayComment) {
        this.context = context;
        this.layout = layout;
        this.arrayComment = arrayComment;
    }

    @Override
    public int getCount() {
        return arrayComment.size();
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

        public CheckBox textViewName;

    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        DiscountAdapter.ViewHolder holder;
        if (view == null) {
            holder = new DiscountAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.textViewName =  view.findViewById(R.id.checkboxDiscount);


            view.setTag(holder);
        } else {
            holder = (DiscountAdapter.ViewHolder) view.getTag();
        }
        final Discount discount = arrayComment.get(i);

        holder.textViewName.setText(discount.getName());

        holder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.textViewName.isChecked())
                {
                    context.discountSanpham(discount.getGiadiscount());
                }
            }
        });


        return view;
    }
}

