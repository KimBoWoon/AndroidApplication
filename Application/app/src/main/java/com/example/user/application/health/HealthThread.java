package com.example.user.application.health;

import android.content.Context;
import android.os.AsyncTask;

import com.example.user.application.R;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 16.
 */
public class HealthThread extends AsyncTask<ArrayList<Health>, Void, ArrayList<Health>> {
    private ArrayList<Health> hospitalList;
    private Context context;

    public HealthThread(Context context, ArrayList<Health> hospitalList) {
        super();
        this.context = context;
        this.hospitalList = hospitalList;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Health> hospitals) {
        super.onPostExecute(hospitals);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(ArrayList<Health> hospitals) {
        super.onCancelled(hospitals);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected ArrayList<Health> doInBackground(ArrayList<Health>... params) {
        HealthParser parser = new HealthParser();
        ArrayList<Health> DTOList = null;
        try {
            DTOList = parser.jsonParser();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Health entity : DTOList) {
            hospitalList.add(new Health(R.drawable.nonregistered, entity.getName(), entity.getAddr(), entity.getClcdnm(), entity.getTelno(), entity.getxPos(), entity.getyPos()));
        }
        return hospitalList;
    }
}
