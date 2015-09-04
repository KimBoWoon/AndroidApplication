package com.example.user.application.food;

import android.content.Context;
import android.os.AsyncTask;

import com.example.user.application.R;
import com.example.user.application.datamanager.Data;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 16.
 */
public class FoodThread extends AsyncTask<ArrayList<Data>, Void, ArrayList<Data>> {
    ArrayList<Data> foodList;

    public FoodThread(ArrayList<Data> foodList) {
        super();
        this.foodList = foodList;
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
        FoodParser parser = new FoodParser();
        ArrayList<Data> DTOList = null;
        try {
            DTOList = parser.jsonParser();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Data entity : DTOList) {
            foodList.add(new Data(R.drawable.nonregistered, entity.getName(), entity.getAddr(), entity.getClcdnm(), entity.getTelno(), entity.getxPos(), entity.getyPos()));
        }
        return foodList;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}