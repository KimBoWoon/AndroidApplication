package com.example.user.application.lodge;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.user.application.R;
import com.example.user.application.health.HospitalList;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 16.
 */
public class LodgeThread extends AsyncTask<ArrayList<Lodge>, Void, ArrayList<Lodge>> {
    private ArrayList<Lodge> lodgesList;
    private LodgeList listAdapter;
    private Context context;
    private ListView listView;
    private ProgressBar pro;

    public LodgeThread(Context context, ArrayList<Lodge> lodgesList, ListView listView, ProgressBar pro) {
        super();
        this.context = context;
        this.listView = listView;
        this.lodgesList = lodgesList;
        this.pro = pro;
        this.listAdapter = new LodgeList(context, R.layout.lodge_list, lodgesList);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pro.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(ArrayList<Lodge> lodges) {
        super.onPostExecute(lodges);
        pro.setVisibility(View.GONE);
        listView.setAdapter(listAdapter);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(ArrayList<Lodge> lodges) {
        super.onCancelled(lodges);
    }

    @Override
    protected ArrayList<Lodge> doInBackground(ArrayList<Lodge>... params) {
        LodgeParser parser = new LodgeParser();
        ArrayList<Lodge> DTOList = null;
        try {
            DTOList = parser.jsonParser();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Lodge entity : DTOList) {
            lodgesList.add(new Lodge(R.drawable.nonregistered, entity.getName(), entity.getAddr(), entity.getClcdnm(), entity.getTelno(), entity.getxPos(), entity.getyPos()));
        }
        return lodgesList;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
