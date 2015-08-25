package com.example.user.application.lodge;

import java.io.Serializable;

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

    public Lodge() {
    }

    public Lodge(int icon, String name, String addr, String clcdnm, String telno, double xPos, double yPos) {
        this.icon = icon;
        this.name = name;
        this.addr = addr;
        this.clcdnm = clcdnm;
        this.telno = telno;
        this.cnt = 0;
        this.xPos = 0;
        this.yPos = 0;
    }

    public int getCnt() {
        return cnt;
    }

    public int getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public String getAddr() {
        return addr;
    }

    public String getClcdnm() {
        return clcdnm;
    }

    public String getTelno() {
        return telno;
    }

    public double getyPos() {
        return yPos;
    }

    public double getxPos() {
        return xPos;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setClcdnm(String clcdnm) {
        this.clcdnm = clcdnm;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }
}
