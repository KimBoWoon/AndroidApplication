package com.example.user.application.lodge;

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
public class LodgeList extends BaseAdapter {
    Context maincon;
    LayoutInflater inflater;
    ArrayList<Lodge> lodgesList;
    int layout;

    public LodgeList(Context context, int layout, ArrayList<Lodge> lodgesList) {
        this.maincon = context;
        this.layout = layout;
        this.lodgesList = lodgesList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lodgesList.size();
    }

    @Override
    public Object getItem(int position) {
        return lodgesList.get(position).getName();
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
        ImageView img = (ImageView) convertView.findViewById(R.id.lodgeimg);
        img.setImageResource(lodgesList.get(position).getIcon());

        TextView name = (TextView) convertView.findViewById(R.id.lodgename);
        name.setText(lodgesList.get(position).getName());

        TextView addr = (TextView) convertView.findViewById(R.id.lodgeaddr);
        addr.setText(lodgesList.get(position).getAddr());

        TextView clcdnm = (TextView) convertView.findViewById(R.id.lodgeclcdnm);
        clcdnm.setText(lodgesList.get(position).getClcdnm());

        RatingBar star = (RatingBar) convertView.findViewById(R.id.lodgerating);
        star.setRating(lodgesList.get(position).getStar());

        return convertView;
    }
}