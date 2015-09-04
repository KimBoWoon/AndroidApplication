package com.example.user.application.search;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.user.application.R;
import com.example.user.application.datamanager.Data;
import com.example.user.application.datamanager.DataManager;
import com.example.user.application.food.FoodList;

import java.util.ArrayList;

/**
 * Created by user on 15. 9. 1.
 */
public class SearchActivity extends Activity {
    private String name;
    private EditText search;
    private ArrayList<Data> searchData;
    private DataManager data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search_view);

        findViewById(R.id.findbtn).setOnClickListener(listener);
        data = DataManager.getInstance();
        searchData = new ArrayList<Data>();
    }

    public void searchArrayList(ArrayList<Data> data) {
        for (int i = 0; i < data.size(); i++) {
            if (name.equals(data.get(i).getName()))
                searchData.add(data.get(i));
        }
    }

    Button.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            search = (EditText) findViewById(R.id.searchtext);
            name = search.getText().toString();
            LinearLayout parent = (LinearLayout) findViewById(R.id.searchparent);

            searchArrayList(data.getFood());
            searchArrayList(data.getBeauty());
            searchArrayList(data.getHealth());
            searchArrayList(data.getPerformance());
            searchArrayList(data.getLodge());

            switch (v.getId()) {
                case R.id.findbtn:
                    View root = View.inflate(SearchActivity.this, R.layout.search_view, parent);
                    ListView listView = (ListView) root.findViewById(R.id.searchlist);
                    FoodList listAdapter = new FoodList(SearchActivity.this, R.layout.list_item, searchData);
                    listView.setAdapter(listAdapter);
                    break;
                default:
                    break;
            }
        }
    };
}
