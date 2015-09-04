package com.example.user.application.lodge;

import android.content.Context;
import android.os.AsyncTask;

import com.example.user.application.R;
import com.example.user.application.datamanager.Data;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 16.
 */
public class LodgeThread extends AsyncTask<ArrayList<Data>, Void, ArrayList<Data>> {
    private ArrayList<Data> lodgesList;

    public LodgeThread(ArrayList<Data> lodgesList) {
        super();
        this.lodgesList = lodgesList;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Data> lodges) {
        super.onPostExecute(lodges);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(ArrayList<Data> lodges) {
        super.onCancelled(lodges);
    }

    @Override
    protected ArrayList<Data> doInBackground(ArrayList<Data>... params) {
        LodgeParser parser = new LodgeParser();
        ArrayList<Data> DTOList = null;
        try {
            DTOList = parser.jsonParser();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Data entity : DTOList) {
            lodgesList.add(new Data(R.drawable.nonregistered, entity.getName(), entity.getAddr(), entity.getClcdnm(), entity.getTelno(), entity.getxPos(), entity.getyPos()));
        }
        return lodgesList;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
