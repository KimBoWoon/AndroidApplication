package com.example.user.application.beauty;

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
 * Created by user on 15. 8. 20.
 */
public class BeautyInfo extends Activity {
    private Beauty beauty;
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
        setContentView(R.layout.beauty_tabmenu);

        fragmentManager = getFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.beautyframe);

        FragmentTransaction tr = fragmentManager.beginTransaction();
        BeautyInfomation bi = new BeautyInfomation();
        tr.add(R.id.beautyframe, bi, "BeautyInfo");
        tr.commit();
    }

    public void beautyClick(View v) {
        switch (v.getId()) {
            case R.id.beautyinfobtn:
                FragmentTransaction tr1 = fragmentManager.beginTransaction();
                BeautyInfomation bi = new BeautyInfomation();
                tr1.replace(R.id.beautyframe, bi, "BeautyInfo");
                tr1.commit();
                break;
            case R.id.beautymenubtn:
                FragmentTransaction tr2 = fragmentManager.beginTransaction();
                BeautyMenu bm = new BeautyMenu();
                tr2.replace(R.id.beautyframe, bm, "BeautyMenu");
                tr2.commit();
                break;
            case R.id.beautyreviewbtn:
                FragmentTransaction tr3 = fragmentManager.beginTransaction();
                BeautyReview br = new BeautyReview();
                tr3.replace(R.id.beautyframe, br, "BeautyReview");
                tr3.commit();
                break;
            default:
                break;

        }
    }

    class BeautyReview extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.beauty_review, container, false);
            return root;
        }
    }

    class BeautyMenu extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.beauty_use, container, false);
            return root;
        }
    }

    class BeautyInfomation extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.beauty_info, container, false);

            Intent intent = getIntent();
            beauty = (Beauty) intent.getSerializableExtra("Item");

            name = (TextView) root.findViewById(R.id.beautyinfoname);
            name.setText(beauty.getName());

            time = (TextView) root.findViewById(R.id.beautyinfotime);
            time.setText("영업시간\n" + "10:00 ~ 24:00");

            useInfo = (TextView) root.findViewById(R.id.beautyinfouse);
            useInfo.setText("정보\n" + beauty.getClcdnm());

            addr = (TextView) root.findViewById(R.id.beautyinfoaddr);
            addr.setText("주소\n" + beauty.getAddr());

            telno = (TextView) root.findViewById(R.id.beautyinfotelno);
            telno.setText("연락처\n" + beauty.getTelno());

            return root;
        }
    }
}