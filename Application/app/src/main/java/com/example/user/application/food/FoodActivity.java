package com.example.user.application.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.user.application.R;
import com.example.user.application.datamanager.Data;
import com.example.user.application.datamanager.DataManager;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 9.
 */
public class FoodActivity extends Activity {
    private ArrayList<Data> foodList;
    private ListView listView;
    private FoodList listAdapter;
    private ProgressBar pro;
    private DataManager data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        data = DataManager.getInstance();
        foodList = data.getFood();
        listView = (ListView) findViewById(R.id.listview);
        pro = (ProgressBar) findViewById(R.id.listpro);
        listAdapter = new FoodList(this, R.layout.list_item, foodList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(listener);
    }

    public AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent info = new Intent(FoodActivity.this, FoodInfo.class);
            Data food = new Data(foodList.get(position).getIcon(), foodList.get(position).getName(),
                    foodList.get(position).getAddr(), foodList.get(position).getClcdnm(),
                    foodList.get(position).getTelno(), foodList.get(position).getxPos(), foodList.get(position).getyPos());
            info.putExtra("Item", food);
            startActivity(info);
        }
    };
}