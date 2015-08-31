package com.example.user.application.lodge;

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
public class LodgeActivity extends Activity {
    public static ArrayList<Lodge> lodgesList = new ArrayList<Lodge>();
    public AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent info = new Intent(LodgeActivity.this, LodgeInfo.class);
            Lodge lodge = new Lodge(lodgesList.get(position).getIcon(), lodgesList.get(position).getName(),
                    lodgesList.get(position).getAddr(), lodgesList.get(position).getClcdnm(),
                    lodgesList.get(position).getTelno(), lodgesList.get(position).getxPos(), lodgesList.get(position).getyPos());
            info.putExtra("Item", lodge);
            startActivity(info);
        }
    };
    ;
    private ListView listView;
    private ProgressBar pro;
    private LodgeList listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lodge_list);

        listView = (ListView) findViewById(R.id.lodgelist);
        pro = (ProgressBar) findViewById(R.id.lodgepro);
        listAdapter = new LodgeList(this, R.layout.lodge_item, lodgesList);


        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(listener);
    }
}
