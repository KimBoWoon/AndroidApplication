package com.example.user.application.performance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
public class PerformanceActivity extends Activity {
    private ArrayList<Data> perList;
    private ListView listView;
    private ProgressBar pro;
    private PerformanceList listAdapter;
    private DataManager data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        data = DataManager.getInstance();
        perList = data.getPerformance();
        listView = (ListView) findViewById(R.id.listview);
        pro = (ProgressBar) findViewById(R.id.listpro);
        listAdapter = new PerformanceList(this, R.layout.list_item, perList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(listener);
    }

    public AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent info = new Intent(PerformanceActivity.this, PerformanceInfo.class);
            Data per = new Data(perList.get(position).getIcon(), perList.get(position).getName(),
                    perList.get(position).getAddr(), perList.get(position).getClcdnm(),
                    perList.get(position).getTelno(), perList.get(position).getCinema(),
                    perList.get(position).getxPos(), perList.get(position).getyPos());
            info.putExtra("Item", per);
            startActivity(info);
        }
    };
}
