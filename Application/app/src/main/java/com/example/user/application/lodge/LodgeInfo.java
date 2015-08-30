package com.example.user.application.lodge;

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
public class LodgeInfo extends Activity {
    private Lodge lodge;
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
        setContentView(R.layout.lodge_tabmenu);

        fragmentManager = getFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.lodgeframe);

        FragmentTransaction tr = fragmentManager.beginTransaction();
        LodgeInfomation li = new LodgeInfomation();
        tr.add(R.id.lodgeframe, li, "LodgeInfo");
        tr.commit();
    }

    public void lodgeClick(View v) {
        switch (v.getId()) {
            case R.id.lodgeinfobtn:
                FragmentTransaction tr1 = fragmentManager.beginTransaction();
                LodgeInfomation li = new LodgeInfomation();
                tr1.replace(R.id.lodgeframe, li, "LodgeInfo");
                tr1.commit();
                break;
            case R.id.lodgemenubtn:
                FragmentTransaction tr2 = fragmentManager.beginTransaction();
                LodgeUse lm = new LodgeUse();
                tr2.replace(R.id.lodgeframe, lm, "LodgeMenu");
                tr2.commit();
                break;
            case R.id.lodgereviewbtn:
                FragmentTransaction tr3 = fragmentManager.beginTransaction();
                LodgeReview lr = new LodgeReview();
                tr3.replace(R.id.lodgeframe, lr, "LodgeReview");
                tr3.commit();
                break;
            default:
                break;
        }
    }

    class LodgeReview extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.lodge_review, container, false);
            return root;
        }
    }

    class LodgeUse extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.lodge_use, container, false);
            return root;
        }
    }

    class LodgeInfomation extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.lodge_info, container, false);

            Intent intent = getIntent();
            lodge = (Lodge) intent.getSerializableExtra("Item");

            name = (TextView) root.findViewById(R.id.lodgeinfoname);
            name.setText(lodge.getName());

            time = (TextView) root.findViewById(R.id.lodgeinfotime);
            time.setText("영업시간\n" + "10:00 ~ 24:00");

            useInfo = (TextView) root.findViewById(R.id.lodgeinfouse);
            useInfo.setText("숙박업\n" + lodge.getClcdnm());

            addr = (TextView) root.findViewById(R.id.lodgeinfoaddr);
            addr.setText("주소\n" + lodge.getAddr());

            telno = (TextView) root.findViewById(R.id.lodgeinfotelno);
            telno.setText("연락처\n" + lodge.getTelno());

            return root;
        }
    }
}