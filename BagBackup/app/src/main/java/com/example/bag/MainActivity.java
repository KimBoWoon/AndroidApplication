package com.example.bag;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity implements InputTextDialog.ButtonListener {
    private final Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private BluetoothService btService = null;
    private boolean lockAndRele = false;
    private boolean onAndOff = false;
    Button.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            InputTextDialog mMainDialog;
            //TextView text = (TextView) findViewById(R.id.print);
            switch (v.getId()) {
                case R.id.input:
                    mMainDialog = new InputTextDialog();
                    mMainDialog.show(getFragmentManager(), "MYTAG");
                    break;
                case R.id.location:
                    GPSInfo gps = new GPSInfo(MainActivity.this);
                    if (gps.isGetLocation()) {
                        Intent maps = new Intent(MainActivity.this, MapsActivity.class);
                        startActivity(maps);
                    } else {
                        gps.showSettingsAlert();
                    }
                    break;
                case R.id.weight:
                    //text.setText("무게확인");
                    btService = new BluetoothService(MainActivity.this, mHandler);
                    btService.enableBluetooth();
                    break;
                case R.id.lockAndRele:
                    ImageButton lockButton = (ImageButton) findViewById(R.id.lockAndRele);

                    if (lockAndRele == true) {
                        lockButton.setImageResource(R.drawable.lock);
                        lockAndRele = false;
                    } else {
                        lockButton.setImageResource(R.drawable.unlock);
                        lockAndRele = true;
                    }
                    break;
                case R.id.on:
                    ImageButton onButton = (ImageButton) findViewById(R.id.on);

                    if (onAndOff == true) {
                        onButton.setImageResource(R.drawable.on);
                        onAndOff = false;
                    } else {
                        onButton.setImageResource(R.drawable.off);
                        onAndOff = true;
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.input).setOnClickListener(onClick);
        findViewById(R.id.location).setOnClickListener(onClick);
        findViewById(R.id.weight).setOnClickListener(onClick);
        findViewById(R.id.lockAndRele).setOnClickListener(onClick);
        findViewById(R.id.track).setOnClickListener(onClick);
        findViewById(R.id.on).setOnClickListener(onClick);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String str) {
        //TextView text = (TextView) findViewById(R.id.print);
        //text.setText(str);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        dialog.onStop();
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
