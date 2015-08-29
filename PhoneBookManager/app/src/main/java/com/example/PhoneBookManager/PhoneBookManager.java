package com.example.PhoneBookManager;

import android.text.util.Linkify;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by 보운 on 2015-02-09.
 */
public class PhoneBookManager {
    public boolean nomalInput(EditText editName, EditText editPhone, HashSet<PhoneInfo> info) {
        String name = editName.getText().toString();
        String phone = editPhone.getText().toString();

        if ((name == null || phone == null) || (name.compareTo("") == 0 || phone.compareTo("") == 0))
            return false;
        else {
            info.add(new PhoneInfo(name, phone));
            return true;
        }
    }

    public boolean univInput(EditText editName, EditText editPhone, EditText editMajor, EditText editYear, HashSet<PhoneInfo> info) {
        String name = editName.getText().toString();
        String phone = editPhone.getText().toString();
        String major = editMajor.getText().toString();
        String year = editYear.getText().toString();

        if ((name == null || phone == null || major == null || year == null) || (name.compareTo("") == 0 || phone.compareTo("") == 0 || major.compareTo("") == 0 || year.compareTo("") == 0))
            return false;
        else {
            info.add(new PhoneUnivInfo(name, phone, major, year));
            return true;
        }
    }

    public boolean compayInput(EditText editName, EditText editPhone, EditText editCompany, HashSet<PhoneInfo> info) {
        String name = editName.getText().toString();
        String phone = editPhone.getText().toString();
        String company = editCompany.getText().toString();

        if ((name == null || phone == null || company == null) || (name.compareTo("") == 0 || phone.compareTo("") == 0 || company.compareTo("") == 0))
            return false;
        else {
            info.add(new PhoneCompanyInfo(name, phone, company));
            return true;
        }
    }

    public boolean searchPhoneNumber(EditText editName, HashSet<PhoneInfo> info, MyActivity myActivity) {
        String name = editName.getText().toString();

        ScrollView scroll = (ScrollView) myActivity.findViewById(R.id.scroll);
        scroll.removeAllViews();
        scroll.setVisibility(View.VISIBLE);

        Iterator<PhoneInfo> iter = info.iterator();

        while (iter.hasNext()) {
            PhoneInfo cur = iter.next();
            if (cur.name.compareTo(name) == 0) {
                TextView text = new TextView(myActivity);

                text.setText(cur.toString());
                text.setTextSize(20);
                Linkify.addLinks(text, Linkify.PHONE_NUMBERS);

                scroll.addView(text);
                return true;
            }
        }
        return false;
    }

    public boolean deletePhoneNumber(EditText editName, HashSet<PhoneInfo> info) {
        String name = editName.getText().toString();

        PhoneInfo searchData = new PhoneInfo(name, "");

        Iterator<PhoneInfo> iter = info.iterator();

        while (iter.hasNext()) {
            PhoneInfo cur = iter.next();
            if (cur.name.compareTo(searchData.name) == 0) {
                iter.remove();
                return true;
            }
        }
        return false;
    }

    public void fileInput(String fileName, HashSet<PhoneInfo> info) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));

            while (true) {
                PhoneInfo cur = (PhoneInfo) in.readObject();

                if (cur == null)
                    break;

                info.add(cur);
            }
            in.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fileOutput(String fileName, HashSet<PhoneInfo> info) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            Iterator<PhoneInfo> iter = info.iterator();

            while (iter.hasNext()) {
                PhoneInfo cur = iter.next();
                out.writeObject(cur);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}