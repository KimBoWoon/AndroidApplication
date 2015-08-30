package com.example.user.application.course;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.user.application.R;
import com.example.user.application.beauty.Beauty;
import com.example.user.application.beauty.BeautyThread;
import com.example.user.application.food.Food;
import com.example.user.application.food.FoodThread;
import com.example.user.application.health.HealthThread;
import com.example.user.application.health.Hospital;
import com.example.user.application.lodge.Lodge;
import com.example.user.application.lodge.LodgeThread;
import com.example.user.application.performance.Performance;
import com.example.user.application.performance.PerformanceThread;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 12.
 */
public class CourseActivity extends Activity {
    private ArrayList<Food> food;
    private ArrayList<Hospital> hos;
    private ArrayList<Lodge> lodge;
    private ArrayList<Performance> per;
    private ArrayList<Beauty> beauty;
    private FragmentManager fragmentManager;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_view);
        init();
    }

    public void init() {
        food = new ArrayList<Food>();
        hos = new ArrayList<Hospital>();
        lodge = new ArrayList<Lodge>();
        per = new ArrayList<Performance>();
        beauty = new ArrayList<Beauty>();

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
            ProgressBar pro = (ProgressBar) root.findViewById(R.id.foodpro);
            new FoodThread(CourseActivity.this, food, listView, pro).execute();
            listView.setOnItemClickListener(Click);

            return root;
        }
    }

    class HospitalView extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.hospital_list, container, false);

            ListView listView = (ListView) root.findViewById(R.id.healthlist);
            ProgressBar pro = (ProgressBar) root.findViewById(R.id.hospitalpro);
            new HealthThread(CourseActivity.this, hos, listView, pro).execute();
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
            ProgressBar pro = (ProgressBar) root.findViewById(R.id.lodgepro);
            new LodgeThread(CourseActivity.this, lodge, listView, pro).execute();
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
            ProgressBar pro = (ProgressBar) root.findViewById(R.id.perpro);
            new PerformanceThread(CourseActivity.this, per, listView, pro).execute();
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
            ProgressBar pro = (ProgressBar) root.findViewById(R.id.beautypro);
            new BeautyThread(CourseActivity.this, beauty, listView, pro).execute();
            listView.setOnItemClickListener(Click);

            return root;
        }
    }

    public AdapterView.OnItemClickListener Click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            LinearLayout itemSelect = (LinearLayout) findViewById(R.id.courselayout);
            ImageView img = new ImageView(CourseActivity.this);
            itemSelect.setOrientation(LinearLayout.HORIZONTAL);
            itemSelect.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams imageSize = (LinearLayout.LayoutParams) itemSelect.getLayoutParams();
            imageSize.gravity = Gravity.CENTER;
            imageSize.width = 100;
            imageSize.height = 100;
            img.setBackground(getDrawable(R.drawable.nonregistered));
            itemSelect.addView(img, imageSize);
        }
    };
}
