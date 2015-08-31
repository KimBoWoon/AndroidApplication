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
import android.widget.TextView;

import com.example.user.application.R;

/**
 * Created by user on 15. 8. 16.
 */
public class HospitalInfo extends Activity {
    private Hospital hospital;
    private TextView name;
    private TextView time;
    private TextView useInfo;
    private TextView addr;
    private TextView telno;
    private FragmentManager fragmentManager;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_tabmenu);

        fragmentManager = getFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.healthframe);

        FragmentTransaction tr = fragmentManager.beginTransaction();
        HealthInfomation hi = new HealthInfomation();
        tr.add(R.id.healthframe, hi, "HospitalInfo");
        tr.commit();
    }
    public void hosClick(View v) {
        switch (v.getId()) {
            case R.id.healthinfobtn:
                FragmentTransaction tr1 = fragmentManager.beginTransaction();
                HealthInfomation hi = new HealthInfomation();
                tr1.replace(R.id.healthframe, hi, "HospitalInfo");
                tr1.commit();
                break;
            case R.id.healthmenubtn:
                FragmentTransaction tr2 = fragmentManager.beginTransaction();
                HealthUse hm = new HealthUse();
                tr2.replace(R.id.healthframe, hm, "HospitalMenu");
                tr2.commit();
                break;
            case R.id.healthreviewbtn:
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
            View root = inflater.inflate(R.layout.hospital_info, container, false);

            Intent intent = getIntent();
            hospital = (Hospital) intent.getSerializableExtra("Item");

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