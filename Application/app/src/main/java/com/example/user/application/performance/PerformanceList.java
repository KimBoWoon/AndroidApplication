package com.example.user.application.performance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.user.application.R;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 15.
 */
public class PerformanceList extends BaseAdapter {
    Context maincon;
    LayoutInflater inflater;
    ArrayList<Performance> performancesList;
    int layout;

    public PerformanceList(Context context, int layout, ArrayList<Performance> performancesList) {
        this.maincon = context;
        this.layout = layout;
        this.performancesList = performancesList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return performancesList.size();
    }

    @Override
    public Object getItem(int position) {
        return performancesList.get(position).getName();
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
        ImageView img = (ImageView) convertView.findViewById(R.id.perimg);
        img.setImageResource(performancesList.get(position).getIcon());

        TextView name = (TextView) convertView.findViewById(R.id.pername);
        name.setText(performancesList.get(position).getName());

        TextView addr = (TextView) convertView.findViewById(R.id.peraddr);
        addr.setText(performancesList.get(position).getAddr());

        TextView clcdnm = (TextView) convertView.findViewById(R.id.perclcdnm);
        clcdnm.setText(performancesList.get(position).getClcdnm());

        RatingBar star = (RatingBar) convertView.findViewById(R.id.perrating);
        star.setRating(performancesList.get(position).getStar());

        return convertView;
    }
}