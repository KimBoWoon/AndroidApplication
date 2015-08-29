package com.example.PhoneBookManager;

import java.io.Serializable;

/**
 * Created by 보운 on 2015-02-09.
 */
public class PhoneInfo implements Serializable {
    String name;
    String phoneNumber;

    public PhoneInfo(String name, String num) {
        this.name = name;
        phoneNumber = num;
    }

    public void showPhoneInfo() {
        System.out.println("name: " + name);
        System.out.println("phone: " + phoneNumber);
    }

    public String toString() {
        return "이름 : " + name + '\n' + "전화번호 : " + phoneNumber + '\n';
    }

    public int hashCode() {
        return name.hashCode();
    }

    public boolean equals(Object obj) {
        PhoneInfo cmp = (PhoneInfo) obj;
        if (name.compareTo(cmp.name) == 0)
            return true;
        else
            return false;
    }
}