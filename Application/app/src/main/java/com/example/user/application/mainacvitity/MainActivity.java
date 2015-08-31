package com.example.user.application.mainacvitity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.user.application.R;
import com.example.user.application.beauty.BeautyActivity;
import com.example.user.application.beauty.BeautyThread;
import com.example.user.application.camera.MyCamera;
import com.example.user.application.course.CourseActivity;
import com.example.user.application.event.EventActivity;
import com.example.user.application.food.FoodActivity;
import com.example.user.application.food.FoodThread;
import com.example.user.application.health.HealthActivity;
import com.example.user.application.health.HealthThread;
import com.example.user.application.lodge.LodgeActivity;
import com.example.user.application.lodge.LodgeThread;
import com.example.user.application.maps.MapActivity;
import com.example.user.application.mypage.MyPageActivity;
import com.example.user.application.performance.PerformanceActivity;
import com.example.user.application.performance.PerformanceThread;
import com.example.user.application.spectacle.SpectacleActivity;

public class MainActivity extends Activity {
    Button.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.food:
                    Intent foodIntent = new Intent(MainActivity.this, FoodActivity.class);
                    startActivity(foodIntent);
                    break;
                case R.id.lodge:
                    Intent lodgeIntent = new Intent(MainActivity.this, LodgeActivity.class);
                    startActivity(lodgeIntent);
                    break;
                case R.id.beauty:
                    Intent beautyIntent = new Intent(MainActivity.this, BeautyActivity.class);
                    startActivity(beautyIntent);
                    break;
                case R.id.health:
                    Intent healthIntent = new Intent(MainActivity.this, HealthActivity.class);
                    startActivity(healthIntent);
                    break;
                case R.id.performance:
                    Intent performanceIntent = new Intent(MainActivity.this, PerformanceActivity.class);
                    startActivity(performanceIntent);
                    break;
                case R.id.course:
                    Intent courseIntent = new Intent(MainActivity.this, CourseActivity.class);
                    startActivity(courseIntent);
                    break;
                case R.id.spectacle:
                    Intent specIntent = new Intent(MainActivity.this, SpectacleActivity.class);
                    startActivity(specIntent);
                    break;
                case R.id.event:
                    Intent eventIntent = new Intent(MainActivity.this, EventActivity.class);
                    startActivity(eventIntent);
                    break;
                case R.id.locabtn:
                    Intent mapsIntent = new Intent(MainActivity.this, MapActivity.class);
                    startActivity(mapsIntent);
                    break;
                case R.id.camerabtn:
                    Intent cameraIntent = new Intent(MainActivity.this, MyCamera.class);
                    startActivity(cameraIntent);
                    break;
                case R.id.mypagebtn:
                    Intent mypageIntent = new Intent(MainActivity.this, MyPageActivity.class);
                    startActivity(mypageIntent);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

        findViewById(R.id.food).setOnClickListener(onClick);
        findViewById(R.id.lodge).setOnClickListener(onClick);
        findViewById(R.id.health).setOnClickListener(onClick);
        findViewById(R.id.performance).setOnClickListener(onClick);
        findViewById(R.id.course).setOnClickListener(onClick);
        findViewById(R.id.beauty).setOnClickListener(onClick);
        findViewById(R.id.spectacle).setOnClickListener(onClick);
        findViewById(R.id.event).setOnClickListener(onClick);
        findViewById(R.id.homebtn).setOnClickListener(onClick);
        findViewById(R.id.searchbtn).setOnClickListener(onClick);
        findViewById(R.id.camerabtn).setOnClickListener(onClick);
        findViewById(R.id.locabtn).setOnClickListener(onClick);
        findViewById(R.id.mypagebtn).setOnClickListener(onClick);

        new FoodThread(this, FoodActivity.foodList).execute();
        new BeautyThread(this, BeautyActivity.beautyList).execute();
        new HealthThread(this, HealthActivity.hospitalList).execute();
        new LodgeThread(this, LodgeActivity.lodgesList).execute();
        new PerformanceThread(this, PerformanceActivity.persList).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
