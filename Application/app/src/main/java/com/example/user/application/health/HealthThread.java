package com.example.user.application.health;

import android.content.Context;
import android.os.AsyncTask;

import com.example.user.application.R;
import com.example.user.application.datamanager.Data;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 16.
 */
public class HealthThread extends AsyncTask<ArrayList<Data>, Void, ArrayList<Data>> {
    private ArrayList<Data> hospitalList;

    public HealthThread(ArrayList<Data> hospitalList) {
        super();
        this.hospitalList = hospitalList;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Data> hospitals) {
        super.onPostExecute(hospitals);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(ArrayList<Data> hospitals) {
        super.onCancelled(hospitals);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected ArrayList<Data> doInBackground(ArrayList<Data>... params) {
        HealthParser parser = new HealthParser();
        ArrayList<Data> DTOList = null;
        try {
            DTOList = parser.jsonParser();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Data entity : DTOList) {
            hospitalList.add(new Data(R.drawable.nonregistered, entity.getName(), entity.getAddr(), entity.getClcdnm(), entity.getTelno(), entity.getxPos(), entity.getyPos()));
        }
        return hospitalList;
    }
}
