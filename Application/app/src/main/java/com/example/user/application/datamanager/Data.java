package com.example.user.application.datamanager;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by user on 15. 9. 4.
 */
public class Data implements Serializable {
    private int icon;
    private String name;
    private String addr;
    private String clcdnm;
    private String telno;
    private String cinema;
    private int order;
    private float star;
    private double xPos;
    private double yPos;

    public Data() {
        this.name = null;
        this.addr = null;
        this.clcdnm = null;
        this.telno = null;
        this.cinema = null;
        this.order = 0;
        this.icon = 0;
        this.xPos = 0;
        this.yPos = 0;
        this.star = 0;
    }

    public Data(int icon, String name, String addr, String clcdnm, String telno, double xPos, double yPos) {
        this.icon = icon;
        this.name = name;
        this.addr = addr;
        this.clcdnm = clcdnm;
        this.telno = telno;
        this.xPos = xPos;
        this.yPos = yPos;
        this.order = 0;
        Random r = new Random();
        this.star = (r.nextFloat() % 5.0f) * 10;
    }

    public Data(int icon, String name, String addr, String clcdnm, String telno, String cinema, double xPos, double yPos) {
        this(icon, name, addr, clcdnm, telno, xPos, yPos);
        this.cinema = cinema;
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

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public void setStar(float star) {
        this.star = star;
    }

    public float getStar() {
        return star;
    }

    public String getCinema() {
        return cinema;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }
}
