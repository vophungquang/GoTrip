package com.example.seminarvpquang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.seminarvpquang.R;
import com.example.seminarvpquang.activity.PlaceDetailActivity;
import com.example.seminarvpquang.model.Comment;


import java.util.ArrayList;

public class CommentAdapter extends BaseAdapter {
    int x = 0;
    private PlaceDetailActivity context;
    private int layout;
    private ArrayList<Comment> arrayComment;

    public CommentAdapter(PlaceDetailActivity context, int layout, ArrayList<Comment> arrayComment) {
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

        public TextView textViewName, textViewnoidung;

    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        CommentAdapter.ViewHolder holder;
        if (view == null) {
            holder = new CommentAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.textViewName =  view.findViewById(R.id.tenuserdacomment);
            holder.textViewnoidung = view.findViewById(R.id.noidungdacomment);

            view.setTag(holder);
        } else {
            holder = (CommentAdapter.ViewHolder) view.getTag();
        }
        final Comment comment = arrayComment.get(i);

        holder.textViewName.setText(comment.getUsername());
        holder.textViewnoidung.setText(comment.getContent()+"");

        return view;
    }
}

