package com.example.user.application.health;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.user.application.R;

/**
 * Created by user on 15. 8. 16.
 */
public class HealthInfo extends Activity {
    private Health hospital;
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
        setContentView(R.layout.health_tabmenu);

        infobtn = (ImageButton) findViewById(R.id.healthinfobtn);
        menubtn = (ImageButton) findViewById(R.id.healthmenubtn);
        reviewbtn = (ImageButton) findViewById(R.id.healthreviewbtn);

        fragmentManager = getFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.healthframe);

        infobtn.setBackground(getDrawable(R.drawable.information_click));
        FragmentTransaction tr = fragmentManager.beginTransaction();
        HealthInfomation hi = new HealthInfomation();
        tr.add(R.id.healthframe, hi, "HospitalInfo");
        tr.commit();
    }

    public void hosClick(View v) {
        switch (v.getId()) {
            case R.id.healthinfobtn:
                infobtn.setBackground(getDrawable(R.drawable.information_click));
                menubtn.setBackground(getDrawable(R.drawable.menu));
                reviewbtn.setBackground(getDrawable(R.drawable.review));
                FragmentTransaction tr1 = fragmentManager.beginTransaction();
                HealthInfomation hi = new HealthInfomation();
                tr1.replace(R.id.healthframe, hi, "HospitalInfo");
                tr1.commit();
                break;
            case R.id.healthmenubtn:
                infobtn.setBackground(getDrawable(R.drawable.information));
                menubtn.setBackground(getDrawable(R.drawable.menu_click));
                reviewbtn.setBackground(getDrawable(R.drawable.review));
                FragmentTransaction tr2 = fragmentManager.beginTransaction();
                HealthUse hm = new HealthUse();
                tr2.replace(R.id.healthframe, hm, "HospitalMenu");
                tr2.commit();
                break;
            case R.id.healthreviewbtn:
                infobtn.setBackground(getDrawable(R.drawable.information));
                menubtn.setBackground(getDrawable(R.drawable.menu));
                reviewbtn.setBackground(getDrawable(R.drawable.review_click));
                FragmentTransaction tr3 = fragmentManager.beginTransaction();
                HealthReview hr = new HealthReview();
                tr3.replace(R.id.healthframe, hr, "HospitalReview");
                tr3.commit();
                break;
            default:
                break;
        }
    }

    class HealthReview extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.health_review, container, false);
            return root;
        }
    }

    class HealthUse extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.health_use, container, false);
            return root;
        }
    }

    class HealthInfomation extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.health_info, container, false);

            Intent intent = getIntent();
            hospital = (Health) intent.getSerializableExtra("Item");

            name = (TextView) root.findViewById(R.id.hosinfoname);
            name.setText(hospital.getName());

            time = (TextView) root.findViewById(R.id.hosinfotime);
            time.setText("진료시간\n" + "10:00 ~ 24:00");

            useInfo = (TextView) root.findViewById(R.id.hosinfouse);
            useInfo.setText("진료목록\n" + hospital.getClcdnm());

            addr = (TextView) root.findViewById(R.id.hosinfoaddr);
            addr.setText("주소\n" + hospital.getAddr());

            telno = (TextView) root.findViewById(R.id.hosinfotelno);
            telno.setText("연락처\n" + hospital.getTelno());

            return root;
        }
    }
}