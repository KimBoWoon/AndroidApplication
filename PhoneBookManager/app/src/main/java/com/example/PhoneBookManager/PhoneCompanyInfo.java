package com.example.PhoneBookManager;
/**
 * Created by 보운 on 2015-02-13.
 */
public class PhoneCompanyInfo extends PhoneInfo {
    String company;

    public PhoneCompanyInfo(String name, String num, String company) {
        super(name, num);
        this.company = company;
    }

    public void showPhoneInfo() {
        super.showPhoneInfo();
        System.out.println("company: " + company);
    }

    public String toString() {
        return super.toString() + "회사 : " + company + '\n';
    }
}