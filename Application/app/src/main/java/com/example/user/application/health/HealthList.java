package com.example.user.application.health;

import android.content.Context;
import android.media.Rating;
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
public class HealthList extends BaseAdapter {
    Context maincon;
    LayoutInflater inflater;
    ArrayList<Health> hospitalsList;
    int layout;

    public HealthList(Context context, int layout, ArrayList<Health> hospitalsList) {
        this.maincon = context;
        this.layout = layout;
        this.hospitalsList = hospitalsList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return hospitalsList.size();
    }

    @Override
    public Object getItem(int position) {
        return hospitalsList.get(position).getName();
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

        ImageView img = (ImageView) convertView.findViewById(R.id.hospitalimg);
        img.setImageResource(hospitalsList.get(position).getIcon());

        TextView name = (TextView) convertView.findViewById(R.id.hospitalname);
        name.setText(hospitalsList.get(position).getName());

        TextView addr = (TextView) convertView.findViewById(R.id.hospitaladdr);
        addr.setText(hospitalsList.get(position).getAddr());
        //addr.setText("즐겨찾기 " + hospitalsList.get(position).getCnt());

        TextView clcdnm = (TextView) convertView.findViewById(R.id.hospitalclcdnm);
        clcdnm.setText(hospitalsList.get(position).getClcdnm());

        RatingBar star = (RatingBar) convertView.findViewById(R.id.healthrating);
        star.setRating(hospitalsList.get(position).getStar());

        return convertView;
    }
}