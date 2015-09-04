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
import com.example.user.application.datamanager.Data;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 15.
 */
public class HealthList extends BaseAdapter {
    Context maincon;
    LayoutInflater inflater;
    ArrayList<Data> hospitalsList;
    int layout;

    public HealthList(Context context, int layout, ArrayList<Data> hospitalsList) {
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

        ImageView img = (ImageView) convertView.findViewById(R.id.listimg);
        img.setImageResource(hospitalsList.get(position).getIcon());

        TextView name = (TextView) convertView.findViewById(R.id.listname);
        name.setText(hospitalsList.get(position).getName());

        TextView addr = (TextView) convertView.findViewById(R.id.listaddr);
        addr.setText(hospitalsList.get(position).getAddr());
        //addr.setText("즐겨찾기 " + hospitalsList.get(position).getCnt());

        TextView clcdnm = (TextView) convertView.findViewById(R.id.listclcdnm);
        clcdnm.setText(hospitalsList.get(position).getClcdnm());

        ImageView order = (ImageView) convertView.findViewById(R.id.listorder);
//        order.setImageResource(hospitalsList.get(position).getIcon());

        RatingBar star = (RatingBar) convertView.findViewById(R.id.listrating);
        star.setRating(hospitalsList.get(position).getStar());

        return convertView;
    }
}