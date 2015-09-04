package com.example.user.application.beauty;

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
 * Created by user on 15. 8. 12.
 */
public class BeautyActivity extends Activity {
    private ArrayList<Data> beautyList;
    private BeautyList listAdapter;
    private ListView listView;
    private ProgressBar pro;
    private DataManager data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        data = DataManager.getInstance();
        beautyList = data.getBeauty();
        listView = (ListView) findViewById(R.id.listview);
        pro = (ProgressBar) findViewById(R.id.listpro);
        listAdapter = new BeautyList(this, R.layout.list_item, beautyList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(listener);
    }

    public AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent info = new Intent(BeautyActivity.this, BeautyInfo.class);
            Data beauty = new Data(beautyList.get(position).getIcon(), beautyList.get(position).getName(),
                    beautyList.get(position).getAddr(), beautyList.get(position).getClcdnm(),
                    beautyList.get(position).getTelno(), beautyList.get(position).getCinema(),
                    beautyList.get(position).getxPos(), beautyList.get(position).getyPos());
            info.putExtra("Item", beauty);
            startActivity(info);
        }
    };
}
