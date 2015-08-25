package com.example.user.application.beauty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.user.application.R;
import com.example.user.application.food.Food;
import com.example.user.application.food.FoodThread;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 12.
 */
public class BeautyActivity extends Activity {
    private ListView listView;
    private ArrayList<Beauty> beautyList;
    private ProgressBar pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beauty_list);

        listView = (ListView) findViewById(R.id.beautylist);
        pro = (ProgressBar) findViewById(R.id.beautypro);
        beautyList = new ArrayList<Beauty>();

        new BeautyThread(BeautyActivity.this, beautyList, listView, pro).execute();
        listView.setOnItemClickListener(listener);
    }
    public AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent info = new Intent(BeautyActivity.this, BeautyInfo.class);
            Beauty beauty = new Beauty(beautyList.get(position).getIcon(), beautyList.get(position).getName(),
                    beautyList.get(position).getAddr(), beautyList.get(position).getClcdnm(),
                    beautyList.get(position).getTelno(), beautyList.get(position).getCinema(), beautyList.get(position).getxPos(), beautyList.get(position).getyPos());
            info.putExtra("Item", beauty);
            startActivity(info);
        }
    };
}
