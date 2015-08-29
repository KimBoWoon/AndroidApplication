package com.example.user.application.mypage;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.user.application.R;

/**
 * Created by user on 15. 8. 29.
 */
public class MyPageActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_page);

        TextView username = (TextView) findViewById(R.id.username);
        username.setText("김보운");
    }
}