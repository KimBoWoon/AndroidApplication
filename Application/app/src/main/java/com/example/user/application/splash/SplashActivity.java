package com.example.user.application.splash;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.example.user.application.R;

/**
 * Created by user on 15. 8. 23.
 */
public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_NoTitleBar);
        setContentView(R.layout.splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);
    }
}
