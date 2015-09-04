package com.example.user.application.beauty;

import android.content.Context;
import android.os.AsyncTask;

import com.example.user.application.R;
import com.example.user.application.datamanager.Data;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 16.
 */
public class BeautyThread extends AsyncTask<ArrayList<Data>, Void, ArrayList<Data>> {
    ArrayList<Data> beautyList;

    public BeautyThread(ArrayList<Data> beautyList) {
        super();
        this.beautyList = beautyList;
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
        BeautyParser parser = new BeautyParser();
        ArrayList<Data> DTOList = null;
        try {
            DTOList = parser.jsonParser();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Data entity : DTOList) {
            beautyList.add(new Data(R.drawable.nonregistered, entity.getName(), entity.getAddr(), entity.getClcdnm(), entity.getTelno(), entity.getCinema(), entity.getxPos(), entity.getyPos()));
        }
        return beautyList;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}