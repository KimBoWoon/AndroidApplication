package com.example.MyCamera;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MyCamera extends Activity {
    /**
     * Called when the activity is first created.
     */
    private ControlCamera   Control;
    FrameLayout      mPreview;
    Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Control = new ControlCamera();
        mPreview = (FrameLayout)findViewById(R.id.camera_preview);
        Control.initialize(this, mPreview, 0);
        btn = (Button)findViewById(R.id.shutter);
        btn.setOnClickListener(s);
    }
    Button.OnClickListener s = new Button.OnClickListener()
    {
        public void onClick(View v)
        {
            Control.takePicture();
        }
    };
}