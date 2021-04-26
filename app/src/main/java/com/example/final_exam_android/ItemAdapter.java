package com.example.final_exam_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {

    ArrayList<PointOfInterest> itemList = new ArrayList<>();
    private LayoutInflater inflater;

    // class constructor
    public ItemAdapter(Context context, ArrayList<PointOfInterest> itemList) {
        this.itemList = itemList;
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
            // id assignation for the row elements
            holder.poiNameTv = convertView.findViewById(R.id.poiNameTv);
            holder.poiPriceTv = convertView.findViewById(R.id.poiPriceTv);
            holder.poiImg = convertView.findViewById(R.id.poiImg);

            convertView.setTag(holder);
        }else{
            holder=(ItemViewHolder) convertView.getTag();
        }
        holder.poiNameTv.setText(itemList.get(position).getName()); // sets the poi name into the textview
        holder.poiPriceTv.setText("$ " + String.format("%.2f", itemList.get(position).getVisitPrice()) );  // sets the poi price into the textview
        holder.poiImg.setImageResource(itemList.get(position).getImageId());  // sets the poi image into the imageview
        return convertView;
    }

    private class ItemViewHolder{
        private TextView poiNameTv, poiPriceTv;
        private ImageView poiImg;
    }
}
