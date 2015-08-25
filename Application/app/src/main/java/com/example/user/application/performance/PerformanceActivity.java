package com.example.user.application.performance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.user.application.R;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 9.
 */
public class PerformanceActivity extends Activity {
    private ListView listView;
    private ArrayList<Performance> persList;
    private ProgressBar pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.performance_list);

        listView = (ListView) findViewById(R.id.perlist);
        pro = (ProgressBar) findViewById(R.id.perpro);
        persList = new ArrayList<Performance>();

        new PerformanceThread(PerformanceActivity.this, persList, listView, pro).execute();
        listView.setOnItemClickListener(listener);
    }
    public AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent info = new Intent(PerformanceActivity.this, PerformanceInfo.class);
            Performance per = new Performance(persList.get(position).getIcon(), persList.get(position).getName(),
                    persList.get(position).getAddr(), persList.get(position).getClcdnm(),
                    persList.get(position).getTelno(), persList.get(position).getCinema(), persList.get(position).getxPos(), persList.get(position).getyPos());
            info.putExtra("Item", per);
            startActivity(info);
        }
    };
}
