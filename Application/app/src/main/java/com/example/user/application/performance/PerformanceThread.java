package com.example.user.application.performance;

import android.content.Context;
import android.os.AsyncTask;

import com.example.user.application.R;
import com.example.user.application.datamanager.Data;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 16.
 */
public class PerformanceThread extends AsyncTask<ArrayList<Data>, Void, ArrayList<Data>> {
    ArrayList<Data> perList;

    public PerformanceThread(ArrayList<Data> perList) {
        super();
        this.perList = perList;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Data> foods) {
        super.onPostExecute(foods);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(ArrayList<Data> foods) {
        super.onCancelled(foods);
    }

    @Override
    protected ArrayList<Data> doInBackground(ArrayList<Data>... params) {
        PerformanceParser parser = new PerformanceParser();
        ArrayList<Data> DTOList = null;
        try {
            DTOList = parser.jsonParser();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Data entity : DTOList) {
            perList.add(new Data(R.drawable.nonregistered, entity.getName(), entity.getAddr(), entity.getClcdnm(), entity.getTelno(), entity.getCinema(), entity.getxPos(), entity.getyPos()));
        }
        return perList;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}