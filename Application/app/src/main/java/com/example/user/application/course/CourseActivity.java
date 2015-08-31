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
import com.example.user.application.beauty.Beauty;
import com.example.user.application.beauty.BeautyActivity;
import com.example.user.application.beauty.BeautyList;
import com.example.user.application.food.Food;
import com.example.user.application.food.FoodActivity;
import com.example.user.application.food.FoodList;
import com.example.user.application.health.Health;
import com.example.user.application.health.HealthActivity;
import com.example.user.application.health.HealthList;
import com.example.user.application.lodge.Lodge;
import com.example.user.application.lodge.LodgeActivity;
import com.example.user.application.lodge.LodgeList;
import com.example.user.application.performance.Performance;
import com.example.user.application.performance.PerformanceActivity;
import com.example.user.application.performance.PerformanceList;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 12.
 */
public class CourseActivity extends Activity {
    public AdapterView.OnItemClickListener Click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            LinearLayout item = (LinearLayout) View.inflate(CourseActivity.this, R.layout.course_item, null);
            LinearLayout itemSelect = (LinearLayout) findViewById(R.id.courselayout);
            itemSelect.setGravity(Gravity.CENTER);
            itemSelect.addView(item);
        }
    };
    private FragmentManager fragmentManager;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_view);
        init();
    }

    public void init() {
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
            View root = inflater.inflate(R.layout.food_list, container, false);
            ListView listView = (ListView) root.findViewById(R.id.foodlist);
            FoodList listAdapter = new FoodList(CourseActivity.this, R.layout.food_item, FoodActivity.foodList);
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(Click);
            return root;
        }
    }

    class HospitalView extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.health_list, container, false);
            ListView listView = (ListView) root.findViewById(R.id.healthlist);
            HealthList listAdapter = new HealthList(CourseActivity.this, R.layout.health_item, HealthActivity.hospitalList);
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(Click);
            return root;
        }
    }

    class LodgeView extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.lodge_list, container, false);
            ListView listView = (ListView) root.findViewById(R.id.lodgelist);
            LodgeList listAdapter = new LodgeList(CourseActivity.this, R.layout.lodge_item, LodgeActivity.lodgesList);
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(Click);
            return root;
        }
    }

    class PerformanceView extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.performance_list, container, false);
            ListView listView = (ListView) root.findViewById(R.id.perlist);
            PerformanceList listAdapter = new PerformanceList(CourseActivity.this, R.layout.performance_item, PerformanceActivity.persList);
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(Click);
            return root;
        }
    }

    class BeautyView extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.beauty_list, container, false);
            ListView listView = (ListView) root.findViewById(R.id.beautylist);
            BeautyList listAdapter = new BeautyList(CourseActivity.this, R.layout.beauty_item, BeautyActivity.beautyList);
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(Click);
            return root;
        }
    }
}
