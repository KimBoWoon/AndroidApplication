package com.example.user.application.food;

import java.io.Serializable;

/**
 * Created by user on 15. 8. 15.
 */
public class Food implements Serializable {
    private String name;
    private String addr;
    private String clcdnm;
    private String telno;
    private int icon;
    private double xPos;
    private double yPos;

    public Food() {
        this.name = null;
        this.addr = null;
        this.clcdnm = null;
        this.telno = null;
        this.icon = 0;
        this.xPos = 0;
        this.yPos = 0;
    }

    public Food(int icon, String name, String addr, String clcdnm, String telno, double xPos, double yPos) {
        this.icon = icon;
        this.name = name;
        this.addr = addr;
        this.clcdnm = clcdnm;
        this.telno = telno;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setClcdnm(String clcdnm) {
        this.clcdnm = clcdnm;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
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

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }
}