package com.example.user.application.health;

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
public class HealthThread extends AsyncTask<ArrayList<Hospital>, Void, ArrayList<Hospital>> {
    private ArrayList<Hospital> hospitalList;
    private HospitalList listAdapter;
    private Context context;
    private ProgressBar pro;
    private ListView listView;

    public HealthThread(Context context, ArrayList<Hospital> hospitalList, ListView listView, ProgressBar pro) {
        super();
        this.context = context;
        this.hospitalList = hospitalList;
        this.pro = pro;
        this.listView = listView;
        this.listAdapter = new HospitalList(context, R.layout.hospital_list, hospitalList);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pro.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(ArrayList<Hospital> hospitals) {
        super.onPostExecute(hospitals);
        pro.setVisibility(View.GONE);
        listView.setAdapter(listAdapter);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(ArrayList<Hospital> hospitals) {
        super.onCancelled(hospitals);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected ArrayList<Hospital> doInBackground(ArrayList<Hospital>... params) {
        HospitalParser parser = new HospitalParser();
        ArrayList<Hospital> DTOList = null;
        try {
            //DTOList = parser.apiParserSearch();
            DTOList = parser.jsonParser();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Hospital entity : DTOList) {
            hospitalList.add(new Hospital(R.drawable.nonregistered, entity.getName(), entity.getAddr(), entity.getClcdnm(), entity.getTelno(), entity.getxPos(), entity.getyPos()));
        }
        return hospitalList;
    }
}
