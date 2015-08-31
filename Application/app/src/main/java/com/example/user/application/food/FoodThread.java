package com.example.user.application.food;

import android.content.Context;
import android.os.AsyncTask;

import com.example.user.application.R;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 16.
 */
public class FoodThread extends AsyncTask<ArrayList<Food>, Void, ArrayList<Food>> {
    ArrayList<Food> foodList;
    Context context;

    public FoodThread(Context context, ArrayList<Food> foodList) {
        super();
        this.context = context;
        this.foodList = foodList;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Food> foods) {
        super.onPostExecute(foods);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(ArrayList<Food> foods) {
        super.onCancelled(foods);
    }

    @Override
    protected ArrayList<Food> doInBackground(ArrayList<Food>... params) {
        FoodParser parser = new FoodParser();
        ArrayList<Food> DTOList = null;
        try {
            DTOList = parser.jsonParser();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Food entity : DTOList) {
            foodList.add(new Food(R.drawable.nonregistered, entity.getName(), entity.getAddr(), entity.getClcdnm(), entity.getTelno(), entity.getxPos(), entity.getyPos()));
        }
        return foodList;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}