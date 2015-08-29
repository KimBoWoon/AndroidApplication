package com.example.PhoneBookManager;

import android.app.Activity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.util.HashSet;
import java.util.Iterator;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private PhoneBookManager manager;
    private HashSet<PhoneInfo> info;
    private View nomalInput, univInput, companyInput, searchLayout, deleteLayout, printLayout;
    private String fileName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initActivity();
        manager.fileInput(fileName, info);
    }

    public void onDestroy() {
        super.onDestroy();
        manager.fileOutput(fileName, info);
        finish();
    }

    Button.OnClickListener inputVisible = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            invisibleLayout();

            switch (v.getId()) {
                case R.id.search:
                    searchLayout.setVisibility(View.VISIBLE);
                    break;
                case R.id.delete:
                    deleteLayout.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
    Button.OnClickListener nomal = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText editName = (EditText) findViewById(R.id.nomalname);
            EditText editPhone = (EditText) findViewById(R.id.nomalphone);

            if (manager.nomalInput(editName, editPhone, info))
                Toast.makeText(MyActivity.this, "입력 완료", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MyActivity.this, "입력 실패", Toast.LENGTH_SHORT).show();

            setEditTextNull(editName, editPhone);
            nomalInput.setVisibility(View.INVISIBLE);
            univInput.setVisibility(View.INVISIBLE);
            companyInput.setVisibility(View.INVISIBLE);
        }
    };
    Button.OnClickListener univ = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText editName = (EditText) findViewById(R.id.univname);
            EditText editPhone = (EditText) findViewById(R.id.univphone);
            EditText editMajor = (EditText) findViewById(R.id.univmajor);
            EditText editYear = (EditText) findViewById(R.id.univyear);

            if (manager.univInput(editName, editPhone, editMajor, editYear, info))
                Toast.makeText(MyActivity.this, "입력 완료", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MyActivity.this, "입력 실패", Toast.LENGTH_SHORT).show();

            setEditTextNull(editName, editPhone);
            editMajor.setText("");
            editYear.setText("");
            nomalInput.setVisibility(View.INVISIBLE);
            univInput.setVisibility(View.INVISIBLE);
            companyInput.setVisibility(View.INVISIBLE);
        }
    };
    Button.OnClickListener company = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText editName = (EditText) findViewById(R.id.companyname);
            EditText editPhone = (EditText) findViewById(R.id.companyphone);
            EditText editCompany = (EditText) findViewById(R.id.company);

            if (manager.compayInput(editName, editPhone, editCompany, info))
                Toast.makeText(MyActivity.this, "입력 완료", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MyActivity.this, "입력 실패", Toast.LENGTH_SHORT).show();

            setEditTextNull(editName, editPhone);
            editCompany.setText("");
            nomalInput.setVisibility(View.INVISIBLE);
            univInput.setVisibility(View.INVISIBLE);
            companyInput.setVisibility(View.INVISIBLE);
        }
    };
    Button.OnClickListener search = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText editName = (EditText) findViewById(R.id.searchname);

            if (manager.searchPhoneNumber(editName, info, MyActivity.this))
                Toast.makeText(MyActivity.this, "검색 완료", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MyActivity.this, "존재하지 않는 정보입니다.", Toast.LENGTH_SHORT).show();

            editName.setText("");
            searchLayout.setVisibility(View.INVISIBLE);
        }
    };
    Button.OnClickListener delete = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText editName = (EditText) findViewById(R.id.deletename);

            if (manager.deletePhoneNumber(editName, info))
                Toast.makeText(MyActivity.this, "제거 완료", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MyActivity.this, "존재하지 않는 정보입니다.", Toast.LENGTH_SHORT).show();

            editName.setText("");
            deleteLayout.setVisibility(View.INVISIBLE);
        }
    };
    Button.OnClickListener print = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            invisibleLayout();

            printLayout.setVisibility(View.VISIBLE);

            ScrollView scroll = (ScrollView) findViewById(R.id.scroll);
            LinearLayout linear = new LinearLayout(MyActivity.this);
            scroll.removeAllViews();
            linear.removeAllViews();
            linear.setOrientation(LinearLayout.VERTICAL);
            Iterator<PhoneInfo> iter = info.iterator();

            while (iter.hasNext()) {
                TextView text = new TextView(MyActivity.this);
                PhoneInfo cur = iter.next();

                text.setText(cur.toString());
                text.setTextSize(20);
                Linkify.addLinks(text, Linkify.PHONE_NUMBERS);

                linear.addView(text);
            }
            scroll.addView(linear);
        }
    };

    public void setEditTextNull(EditText editName, EditText editPhone) {
        editName.setText("");
        editPhone.setText("");
    }

    public void initActivity() {
        if (manager == null)
            manager = new PhoneBookManager();
        if (info == null)
            info = new HashSet<PhoneInfo>();

        fileName = getFilesDir().getPath() + "data.txt";

        nomalInput = findViewById(R.id.nomalinput);
        univInput = findViewById(R.id.univinput);
        companyInput = findViewById(R.id.companyinput);
        searchLayout = findViewById(R.id.searchlayout);
        deleteLayout = findViewById(R.id.deletelayout);
        printLayout = findViewById(R.id.scroll);

        findViewById(R.id.search).setOnClickListener(inputVisible);
        findViewById(R.id.delete).setOnClickListener(inputVisible);
        findViewById(R.id.print).setOnClickListener(print);
        findViewById(R.id.nomalbtn).setOnClickListener(nomal);
        findViewById(R.id.unvibtn).setOnClickListener(univ);
        findViewById(R.id.companybtn).setOnClickListener(company);
        findViewById(R.id.searchbutton).setOnClickListener(search);
        findViewById(R.id.deletebutton).setOnClickListener(delete);
    }

    public void invisibleLayout() {
        nomalInput.setVisibility(View.INVISIBLE);
        univInput.setVisibility(View.INVISIBLE);
        companyInput.setVisibility(View.INVISIBLE);
        searchLayout.setVisibility(View.INVISIBLE);
        deleteLayout.setVisibility(View.INVISIBLE);
        printLayout.setVisibility(View.INVISIBLE);
    }

    public void popupMenu(View v) {
        invisibleLayout();

        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        Menu menu = popup.getMenu();
        inflater.inflate(R.menu.menu, menu);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nomal:
                        findViewById(R.id.nomalinput).setVisibility(View.VISIBLE);
                        break;
                    case R.id.univ:
                        findViewById(R.id.univinput).setVisibility(View.VISIBLE);
                        break;
                    case R.id.company:
                        findViewById(R.id.companyinput).setVisibility(View.VISIBLE);
                        break;
                }
                return false;
            }
        });
        popup.show();
    }
}