package com.example.user.application.performance;

import android.content.Context;
import android.os.AsyncTask;

import com.example.user.application.R;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 16.
 */
public class PerformanceThread extends AsyncTask<ArrayList<Performance>, Void, ArrayList<Performance>> {
    ArrayList<Performance> perList;
    Context context;

    public PerformanceThread(Context context, ArrayList<Performance> perList) {
        super();
        this.context = context;
        this.perList = perList;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Performance> foods) {
        super.onPostExecute(foods);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(ArrayList<Performance> foods) {
        super.onCancelled(foods);
    }

    @Override
    protected ArrayList<Performance> doInBackground(ArrayList<Performance>... params) {
        PerformanceParser parser = new PerformanceParser();
        ArrayList<Performance> DTOList = null;
        try {
            DTOList = parser.jsonParser();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Performance entity : DTOList) {
            perList.add(new Performance(R.drawable.nonregistered, entity.getName(), entity.getAddr(), entity.getClcdnm(), entity.getTelno(), entity.getCinema(), entity.getxPos(), entity.getyPos()));
        }
        return perList;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}