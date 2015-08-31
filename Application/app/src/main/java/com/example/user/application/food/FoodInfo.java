package com.example.user.application.food;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.user.application.R;

/**
 * Created by user on 15. 8. 20.
 */
public class FoodInfo extends Activity {
    private Food food;
    private TextView name;
    private TextView time;
    private TextView useInfo;
    private TextView addr;
    private TextView telno;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private ImageButton infobtn;
    private ImageButton menubtn;
    private ImageButton reviewbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_tabmenu);

        infobtn = (ImageButton) findViewById(R.id.foodinfobtn);
        menubtn = (ImageButton) findViewById(R.id.foodmenubtn);
        reviewbtn = (ImageButton) findViewById(R.id.foodreviewbtn);

        fragmentManager = getFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.foodframe);

        infobtn.setBackground(getDrawable(R.drawable.information_click));
        FragmentTransaction tr = fragmentManager.beginTransaction();
        FoodInfomation fi = new FoodInfomation();
        tr.add(R.id.foodframe, fi, "FoodInfo");
        tr.commit();
    }

    public void foodClick(View v) {
        switch (v.getId()) {
            case R.id.foodinfobtn:
                infobtn.setBackground(getDrawable(R.drawable.information_click));
                menubtn.setBackground(getDrawable(R.drawable.menu));
                reviewbtn.setBackground(getDrawable(R.drawable.review));

                FragmentTransaction tr1 = fragmentManager.beginTransaction();
                FoodInfomation fi = new FoodInfomation();
                tr1.replace(R.id.foodframe, fi, "FoodInfo");
                tr1.commit();
                break;
            case R.id.foodmenubtn:
                infobtn.setBackground(getDrawable(R.drawable.information));
                menubtn.setBackground(getDrawable(R.drawable.menu_click));
                reviewbtn.setBackground(getDrawable(R.drawable.review));

                FragmentTransaction tr2 = fragmentManager.beginTransaction();
                FoodMenu fm = new FoodMenu();
                tr2.replace(R.id.foodframe, fm, "FoodMenu");
                tr2.commit();
                break;
            case R.id.foodreviewbtn:
                infobtn.setBackground(getDrawable(R.drawable.information));
                menubtn.setBackground(getDrawable(R.drawable.menu));
                reviewbtn.setBackground(getDrawable(R.drawable.review_click));

                FragmentTransaction tr3 = fragmentManager.beginTransaction();
                FoodReview fr = new FoodReview();
                tr3.replace(R.id.foodframe, fr, "FoodReview");
                tr3.commit();
                break;
            default:
                break;

        }
    }

    class FoodReview extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.food_review, container, false);
            return root;
        }
    }

    class FoodMenu extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.food_menu, container, false);
            return root;
        }
    }

    class FoodInfomation extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.food_info, container, false);

            Intent intent = getIntent();
            food = (Food) intent.getSerializableExtra("Item");

            name = (TextView) root.findViewById(R.id.foodinfoname);
            name.setText(food.getName());

            time = (TextView) root.findViewById(R.id.foodinfotime);
            time.setText("영업시간\n" + "10:00 ~ 24:00");

            useInfo = (TextView) root.findViewById(R.id.foodinfouse);
            useInfo.setText("주된음식\n" + food.getClcdnm());

            addr = (TextView) root.findViewById(R.id.foodinfoaddr);
            addr.setText("주소\n" + food.getAddr());

            telno = (TextView) root.findViewById(R.id.foodinfotelno);
            telno.setText("연락처\n" + food.getTelno());

            return root;
        }
    }
}
