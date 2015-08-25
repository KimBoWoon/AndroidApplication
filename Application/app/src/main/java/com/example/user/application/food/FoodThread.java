package com.example.user.application.food;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.user.application.R;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 16.
 */
public class FoodThread extends AsyncTask<ArrayList<Food>, Void, ArrayList<Food>> {
    ArrayList<Food> foodList;
    FoodList listAdapter;
    Context context;
    ListView listView;
    ProgressBar pro;

    public FoodThread(Context context, ArrayList<Food> foodList, ListView listView, ProgressBar pro) {
        super();
        this.context = context;
        this.listView = listView;
        this.foodList = foodList;
        this.pro = pro;
        this.listAdapter = new FoodList(context, R.layout.food_list, foodList);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pro.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(ArrayList<Food> foods) {
        super.onPostExecute(foods);
        pro.setVisibility(View.GONE);
        listView.setAdapter(listAdapter);
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