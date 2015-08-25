package com.example.user.application.beauty;

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
public class BeautyThread extends AsyncTask<ArrayList<Beauty>, Void, ArrayList<Beauty>> {
    ArrayList<Beauty> beautyList;
    BeautyList listAdapter;
    Context context;
    ListView listView;
    ProgressBar pro;

    public BeautyThread(Context context, ArrayList<Beauty> beautyList, ListView listView, ProgressBar pro) {
        super();
        this.context = context;
        this.listView = listView;
        this.beautyList = beautyList;
        this.pro = pro;
        this.listAdapter = new BeautyList(context, R.layout.beauty_list, beautyList);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pro.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(ArrayList<Beauty> foods) {
        super.onPostExecute(foods);
        pro.setVisibility(View.GONE);
        listView.setAdapter(listAdapter);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(ArrayList<Beauty> foods) {
        super.onCancelled(foods);
    }

    @Override
    protected ArrayList<Beauty> doInBackground(ArrayList<Beauty>... params) {
        BeautyParser parser = new BeautyParser();
        ArrayList<Beauty> DTOList = null;
        try {
            DTOList = parser.jsonParser();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Beauty entity : DTOList) {
            beautyList.add(new Beauty(R.drawable.nonregistered, entity.getName(), entity.getAddr(), entity.getClcdnm(), entity.getTelno(), entity.getCinema(), entity.getxPos(), entity.getyPos()));
        }
        return beautyList;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}