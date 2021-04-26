package com.example.final_exam_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class    ItemAdapter extends BaseAdapter {
    ArrayList<PointOfInterest> itemList = new ArrayList<>();
    private LayoutInflater inflater;


    public ItemAdapter(Context context, ArrayList<PointOfInterest> bookList) {
        this.itemList = bookList;
        inflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewHolder holder;
        if(convertView==null) {
            convertView = inflater.inflate(R.layout.list_row, null);
            holder = new ItemViewHolder();
            holder.nameTv = convertView.findViewById(R.id.nameTv);

            convertView.setTag(holder);
        }else{
            holder=(ItemViewHolder) convertView.getTag();
        }
        holder.nameTv.setText(itemList.get(position).getName());
        return convertView;
    }

    private class ItemViewHolder{
        private TextView nameTv;
    }
}
