package com.example.user.application.course;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.user.application.R;
import com.example.user.application.beauty.BeautyList;
import com.example.user.application.datamanager.DataManager;
import com.example.user.application.food.FoodList;
import com.example.user.application.health.HealthList;
import com.example.user.application.lodge.LodgeList;
import com.example.user.application.performance.PerformanceList;

/**
 * Created by user on 15. 8. 12.
 */
public class CourseActivity extends Activity {
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private DataManager data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_view);
        init();
    }

    public void init() {
        data = DataManager.getInstance();
        fragmentManager = getFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.foodframe);

        FragmentTransaction fvf = fragmentManager.beginTransaction();
        FoodView fv = new FoodView();
        fvf.add(R.id.courseframe, fv, "FoodView");
        fvf.commit();
    }

    public void courseClick(View v) {
        switch (v.getId()) {
            case R.id.foodview:
                FragmentTransaction fvf = fragmentManager.beginTransaction();
                FoodView fv = new FoodView();
                fvf.replace(R.id.courseframe, fv, "FoodView");
                fvf.commit();
                break;
            case R.id.hosview:
                FragmentTransaction hvf = fragmentManager.beginTransaction();
                HospitalView hv = new HospitalView();
                hvf.replace(R.id.courseframe, hv, "HospitalView");
                hvf.commit();
                break;
            case R.id.lodgeview:
                FragmentTransaction lvf = fragmentManager.beginTransaction();
                LodgeView lv = new LodgeView();
                lvf.replace(R.id.courseframe, lv, "LodgeView");
                lvf.commit();
                break;
            case R.id.beautyview:
                FragmentTransaction bvf = fragmentManager.beginTransaction();
                BeautyView bv = new BeautyView();
                bvf.replace(R.id.courseframe, bv, "BeautyView");
                bvf.commit();
                break;
            case R.id.perview:
                FragmentTransaction pvf = fragmentManager.beginTransaction();
                PerformanceView pv = new PerformanceView();
                pvf.replace(R.id.courseframe, pv, "PerformanceView");
                pvf.commit();
                break;
            default:
                break;

        }
    }

    class FoodView extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.list_view, container, false);
            ListView listView = (ListView) root.findViewById(R.id.listview);
            FoodList listAdapter = new FoodList(CourseActivity.this, R.layout.list_item, data.getFood());
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(Click);
            return root;
        }
    }

    class HospitalView extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.list_view, container, false);
            ListView listView = (ListView) root.findViewById(R.id.listview);
            HealthList listAdapter = new HealthList(CourseActivity.this, R.layout.list_item, data.getHealth());
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(Click);
            return root;
        }
    }

    class LodgeView extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.list_view, container, false);
            ListView listView = (ListView) root.findViewById(R.id.listview);
            LodgeList listAdapter = new LodgeList(CourseActivity.this, R.layout.list_item, data.getLodge());
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(Click);
            return root;
        }
    }

    class PerformanceView extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.list_view, container, false);
            ListView listView = (ListView) root.findViewById(R.id.listview);
            PerformanceList listAdapter = new PerformanceList(CourseActivity.this, R.layout.list_item, data.getPerformance());
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(Click);
            return root;
        }
    }

    class BeautyView extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.list_view, container, false);
            ListView listView = (ListView) root.findViewById(R.id.listview);
            BeautyList listAdapter = new BeautyList(CourseActivity.this, R.layout.list_item, data.getBeauty());
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(Click);
            return root;
        }
    }

    public AdapterView.OnItemClickListener Click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            LinearLayout item = (LinearLayout) View.inflate(CourseActivity.this, R.layout.course_item, null);
            LinearLayout itemSelect = (LinearLayout) findViewById(R.id.courselayout);
            itemSelect.setGravity(Gravity.CENTER);
            itemSelect.addView(item);
        }
    };
}
