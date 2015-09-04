package com.example.user.application.camera;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.user.application.R;

public class MyCamera extends Activity {
    /**
     * Called when the activity is first created.
     */
    private ControlCamera Control;
    Button.OnClickListener s = new Button.OnClickListener() {
        public void onClick(View v) {
            Control.takePicture();
        }
    };
    Button.OnClickListener f = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Control.getCamera().autoFocus(autoFocus);
        }
    };
    private FrameLayout mPreview;
    private Button shutterbtn;
    Camera.AutoFocusCallback autoFocus = new Camera.AutoFocusCallback() {
        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            shutterbtn.setEnabled(success);
        }
    };
    private Button focuseBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_main);

        Control = new ControlCamera();
        mPreview = (FrameLayout) findViewById(R.id.camera_preview);
        Control.initialize(this, mPreview, 0);
        shutterbtn = (Button) findViewById(R.id.shutter);
        focuseBtn = (Button) findViewById(R.id.focuse);
        shutterbtn.setOnClickListener(s);
        focuseBtn.setOnClickListener(f);

        //this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
        //context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
    }
}