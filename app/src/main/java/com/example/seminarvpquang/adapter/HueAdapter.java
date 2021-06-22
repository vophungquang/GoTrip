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
import com.example.seminarvpquang.activity.PlaceDetailActivity;
import com.example.seminarvpquang.activity.HueActivity;
import com.example.seminarvpquang.model.Place;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class HueAdapter extends BaseAdapter {
    int x = 0;
    private HueActivity context;
    private int layout;
    private List<Place> arraListPlace;

    public HueAdapter(HueActivity context, int layout, List<Place> subjectsList) {
        this.context = context;
        this.layout = layout;
        this.arraListPlace = subjectsList;
    }

    @Override
    public int getCount() {
        return arraListPlace.size();
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
        public TextView textViewName, textViewVote, textViewDescription;
        public ImageView imageViewPlace;
        public LinearLayout linearLayoutPlace;

    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        HueAdapter.ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new HueAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            viewHolder.textViewName = view.findViewById(R.id.textViewNamePlace);
            viewHolder.textViewVote = view.findViewById(R.id.textViewVotePlace);
            viewHolder.textViewDescription =  view.findViewById(R.id.textViewDescriptionPlace);
            viewHolder.imageViewPlace = view.findViewById(R.id.imageViewPlace);
            viewHolder.linearLayoutPlace = view.findViewById(R.id.linealayoutItem);
            view.setTag(viewHolder);
        }else {
            viewHolder = (HueAdapter.ViewHolder) view.getTag();
        }
        final Place place = (Place) arraListPlace.get(i);
        viewHolder.textViewName.setText(place.getnamePlace());
        viewHolder.textViewName.setSelected(true);
        viewHolder.textViewDescription.setMaxLines(2);
        viewHolder.textViewDescription.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.textViewDescription.setText(place.getdescriptionPlace());
        Picasso.get().load(place.getimagePlace())
                .into(viewHolder.imageViewPlace);
        viewHolder.linearLayoutPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlaceDetailActivity.class);
                intent.putExtra("information", place);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return view;
    }


}