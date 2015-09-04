package com.example.user.application.beauty;

import android.content.Context;
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
public class BeautyList extends BaseAdapter {
    Context maincon;
    LayoutInflater inflater;
    ArrayList<Data> beautysList;
    int layout;

    public BeautyList(Context context, int layout, ArrayList<Data> beautysList) {
        this.maincon = context;
        this.layout = layout;
        this.beautysList = beautysList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return beautysList.size();
    }

    @Override
    public Object getItem(int position) {
        return beautysList.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }
        ImageView img = (ImageView) convertView.findViewById(R.id.listimg);
        img.setImageResource(beautysList.get(position).getIcon());

        TextView name = (TextView) convertView.findViewById(R.id.listname);
        name.setText(beautysList.get(position).getName());

        TextView addr = (TextView) convertView.findViewById(R.id.listaddr);
        addr.setText(beautysList.get(position).getAddr());

        TextView clcdnm = (TextView) convertView.findViewById(R.id.listclcdnm);
        clcdnm.setText(beautysList.get(position).getClcdnm());

        ImageView order = (ImageView) convertView.findViewById(R.id.listorder);
//        order.setImageResource(beautysList.get(position).getIcon());

        RatingBar star = (RatingBar) convertView.findViewById(R.id.listrating);
        star.setRating(beautysList.get(position).getStar());

        return convertView;
    }
}