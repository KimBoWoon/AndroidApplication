package com.example.user.application.lodge;

import android.content.Context;
import android.os.AsyncTask;

import com.example.user.application.R;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 16.
 */
public class LodgeThread extends AsyncTask<ArrayList<Lodge>, Void, ArrayList<Lodge>> {
    private ArrayList<Lodge> lodgesList;
    private Context context;

    public LodgeThread(Context context, ArrayList<Lodge> lodgesList) {
        super();
        this.context = context;
        this.lodgesList = lodgesList;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Lodge> lodges) {
        super.onPostExecute(lodges);
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
