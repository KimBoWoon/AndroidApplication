package com.example.user.application.datamanager;

import com.example.user.application.beauty.BeautyThread;
import com.example.user.application.food.FoodThread;
import com.example.user.application.health.HealthThread;
import com.example.user.application.lodge.LodgeThread;
import com.example.user.application.performance.PerformanceThread;

import java.util.ArrayList;

/**
 * Created by user on 15. 9. 4.
 */
public class DataManager {
    private ArrayList<Data> food;
    private ArrayList<Data> lodge;
    private ArrayList<Data> health;
    private ArrayList<Data> per;
    private ArrayList<Data> beauty;
    private final static DataManager instance = new DataManager();

    private DataManager() {
        food = new ArrayList<Data>();
        lodge = new ArrayList<Data>();
        health = new ArrayList<Data>();
        per = new ArrayList<Data>();
        beauty = new ArrayList<Data>();
    }

    public void executeThread() {
        new FoodThread(food).execute();
        new BeautyThread(beauty).execute();
        new HealthThread(health).execute();
        new LodgeThread(lodge).execute();
        new PerformanceThread(per).execute();
    }

    public ArrayList<Data> getFood() {
        return food;
    }

    public ArrayList<Data> getBeauty() {
        return beauty;
    }

    public ArrayList<Data> getHealth() {
        return health;
    }

    public ArrayList<Data> getLodge() {
        return lodge;
    }

    public ArrayList<Data> getPerformance() {
        return per;
    }

    public static DataManager getInstance() {
        return instance;
    }
}
