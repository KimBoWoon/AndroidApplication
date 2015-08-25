package com.example.user.application.health;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.user.application.R;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 9.
 */
public class HealthActivity extends Activity {
    private ListView listView;
    private ArrayList<Hospital> hospitalList;
    private ProgressBar pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_list);

        listView = (ListView) findViewById(R.id.healthlist);
        pro = (ProgressBar) findViewById(R.id.hospitalpro);
        hospitalList = new ArrayList<Hospital>();

        new HealthThread(HealthActivity.this, hospitalList, listView, pro).execute();
        listView.setOnItemClickListener(listener);
    }

    public AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent info = new Intent(HealthActivity.this, HospitalInfo.class);
            Hospital hospital = new Hospital(hospitalList.get(position).getIcon(), hospitalList.get(position).getName(),
                    hospitalList.get(position).getAddr(), hospitalList.get(position).getClcdnm(),
                    hospitalList.get(position).getTelno(), hospitalList.get(position).getxPos(), hospitalList.get(position).getyPos());
            info.putExtra("Item", hospital);
            startActivity(info);
        }
    };
}