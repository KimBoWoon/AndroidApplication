package com.example.PhoneBookManager;

/**
 * Created by 보운 on 2015-02-13.
 */
public class PhoneUnivInfo extends PhoneInfo {
    String major, year;

    public PhoneUnivInfo(String name, String num, String major, String year) {
        super(name, num);
        this.major = major;
        this.year = year;
    }

    public void showPhoneInfo() {
        super.showPhoneInfo();
        System.out.println("major: " + major);
        System.out.println("year: " + year);
    }

    public String toString() {
        return super.toString() + "전공 : " + major + '\n' + "학년 : " + year + "학년\n";
    }
}