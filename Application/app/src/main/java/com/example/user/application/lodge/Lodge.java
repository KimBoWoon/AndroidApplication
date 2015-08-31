package com.example.user.application.lodge;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by user on 15. 8. 15.
 */
public class Lodge implements Serializable {
    private String name;
    private String addr;
    private String clcdnm;
    private String telno;
    private int icon;
    private int cnt;
    private double xPos;
    private double yPos;
    private float star;

    public Lodge() {
        this.icon = 0;
        this.name = null;
        this.addr = null;
        this.clcdnm = null;
        this.telno = null;
        this.cnt = 0;
        this.xPos = 0;
        this.yPos = 0;
        this.star = 0;
    }

    public Lodge(int icon, String name, String addr, String clcdnm, String telno, double xPos, double yPos) {
        this.icon = icon;
        this.name = name;
        this.addr = addr;
        this.clcdnm = clcdnm;
        this.telno = telno;
        this.cnt = 0;
        this.xPos = xPos;
        this.yPos = yPos;
        Random r = new Random();
        this.star = (r.nextFloat() % 5.0f) * 10;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getClcdnm() {
        return clcdnm;
    }

    public void setClcdnm(String clcdnm) {
        this.clcdnm = clcdnm;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }
}
