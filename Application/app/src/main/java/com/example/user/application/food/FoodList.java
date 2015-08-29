package com.example.user.application.food;

import android.content.Context;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.application.R;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 15.
 */
public class FoodList extends BaseAdapter {
    Context maincon;
    LayoutInflater inflater;
    ArrayList<Food> foodsList;
    int layout;

    public FoodList(Context context, int layout, ArrayList<Food> foodsList) {
        this.maincon = context;
        this.layout = layout;
        this.foodsList = foodsList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return foodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodsList.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;

        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }
        ImageView img = (ImageView) convertView.findViewById(R.id.foodimg);
        img.setImageResource(foodsList.get(position).getIcon());

        TextView name = (TextView) convertView.findViewById(R.id.foodname);
        name.setText(foodsList.get(position).getName());

        TextView addr = (TextView) convertView.findViewById(R.id.foodaddr);
        addr.setText(foodsList.get(position).getAddr());

        TextView clcdnm = (TextView) convertView.findViewById(R.id.foodclcdnm);
        clcdnm.setText(foodsList.get(position).getClcdnm());

        return convertView;
    }
}