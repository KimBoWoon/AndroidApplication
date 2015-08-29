/*
 * Copyright (C) 2011 Eirik Taylor
 *
 * This work is licensed under a Creative Commons Attribution-Noncommercial-Share Alike 3.0 Unported License.
 * See the following website for more information: 
 * http://creativecommons.org/licenses/by-nc-sa/3.0/
 * 
 */

package org.sam_mem.omni_wheel;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uzzors2k.blu_car.R;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class omni_wheel extends Activity {

    // Intent request codes
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;

    private byte data;
    // private boolean ledStat;
    private boolean connectStat = false;
    // private Button led_button;
    private Button connect_button;
    // private text
    protected static final int MOVE_TIME = 80;
    private View aboutView;
    private View controlView;
    OnClickListener myClickListener;
    OnClickListener buttonClickListener;
    ProgressDialog myProgressDialog;
    private Toast failToast;
    private Handler mHandler;

    ImageButton button1, button2, button3, button4, button5, button6;
    ImageButton button7, button8, button9, button10, button11, button12;
    ImageView iv;
    TextView tv;

    // Bluetooth Stuff
    private BluetoothAdapter mBluetoothAdapter = null;
    private BluetoothSocket btSocket = null;
    private OutputStream outStream = null;
    private ConnectThread mConnectThread = null;
    private String deviceAddress = null;
    // Well known SPP UUID (will *probably* map to RFCOMM channel 1 (default) if
    // not in use);
    private static final UUID SPP_UUID = UUID
            .fromString("00001101-0000-1000-8000-00805F9B34FB");

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Create main button view
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        aboutView = inflater.inflate(R.layout.aboutview, null);
        controlView = inflater.inflate(R.layout.activity_sammem, null);
        controlView.setKeepScreenOn(true);
        setContentView(controlView);

        connect_button = (Button) findViewById(R.id.connect_button);
        ImageButton btn1 = (ImageButton) findViewById(R.id.button1);
        ImageButton btn2 = (ImageButton) findViewById(R.id.button2);
        ImageButton btn3 = (ImageButton) findViewById(R.id.button3);
        ImageButton btn4 = (ImageButton) findViewById(R.id.button4);
        ImageButton btn5 = (ImageButton) findViewById(R.id.button5);
        ImageButton btn6 = (ImageButton) findViewById(R.id.button6);
        ImageButton btn7 = (ImageButton) findViewById(R.id.button7);
        ImageButton btn8 = (ImageButton) findViewById(R.id.button8);
        ImageButton btn9 = (ImageButton) findViewById(R.id.button9);
        ImageButton btn10 = (ImageButton) findViewById(R.id.button10);
        ImageButton btn11 = (ImageButton) findViewById(R.id.button11);
        ImageButton btn12 = (ImageButton) findViewById(R.id.button12);
        ImageButton btn13 = (ImageButton) findViewById(R.id.button13);
        ImageButton btn14 = (ImageButton) findViewById(R.id.button14);


        iv = (ImageView) findViewById(R.id.imageView);
        tv = (TextView) findViewById(R.id.TextView);

        btn1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                iv.setImageResource(R.drawable.n_bl);
            }
        });

        btn2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                iv.setImageResource(R.drawable.n_l);
            }
        });

        btn3.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                iv.setImageResource(R.drawable.n_tl);
            }
        });

        btn4.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                iv.setImageResource(R.drawable.n_t);
            }
        });

        btn5.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                iv.setImageResource(R.drawable.n_tr);
            }
        });

        btn6.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                iv.setImageResource(R.drawable.n_r);
            }
        });

        btn7.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                iv.setImageResource(R.drawable.n_br);
            }
        });

        btn8.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                iv.setImageResource(R.drawable.n_b);
            }
        });

        btn9.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                iv.setImageResource(R.drawable.n_l_rota);
            }
        });

        btn10.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                iv.setImageResource(R.drawable.n_r_rota);
            }
        });

        btn11.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                tv.setText("왼쪽");
            }
        });

        btn12.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                tv.setText("오른쪽");
            }
        });

        btn13.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                iv.setImageResource(R.drawable.car_stop);
            }
        });

        btn14.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                tv.setText("고정");
            }
        });

        myClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        dialog.dismiss();
                        break;
                    case DialogInterface.BUTTON_NEUTRAL:
                        // Display website
                        Intent browserIntent = new Intent(
                                "android.intent.action.VIEW",
                                Uri.parse(getResources().getString(
                                        R.string.website_url)));
                        startActivity(browserIntent);
                        break;
                    default:
                        dialog.dismiss();
                }
            }
        };

        myProgressDialog = new ProgressDialog(this);
        failToast = Toast.makeText(this, R.string.failedToConnect,
                Toast.LENGTH_SHORT);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (myProgressDialog.isShowing()) {
                    myProgressDialog.dismiss();
                }

                // Check if bluetooth connection was made to selected device
                if (msg.what == 1) {
                    // Set button to display current status
                    connectStat = true;
                    connect_button.setText(R.string.connected);

                    // Reset the BluCar
                    data = 0;

                } else {
                    // Connection failed
                    failToast.show();
                }
            }
        };

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, R.string.no_bt_device, Toast.LENGTH_LONG)
                    .show();
            finish();
            return;
        }

        // If BT is not on, request that it be enabled.
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }

        // Connect to Bluetooth Module
        connect_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (connectStat) {
                    // Attempt to disconnect from the device
                    disconnect();
                } else {
                    // Attempt to connect to the device
                    connect();
                }
            }
        });

    }

    public class ConnectThread extends Thread {
        private String address;
        private boolean connectionStatus;

        ConnectThread(String MACaddress) {
            address = MACaddress;
            connectionStatus = true;
        }

        public void run() {
            // When this returns, it will 'know' about the server,
            // via it's MAC address.
            try {
                BluetoothDevice device = mBluetoothAdapter
                        .getRemoteDevice(address);

                try {
                    btSocket = device
                            .createRfcommSocketToServiceRecord(SPP_UUID);
                } catch (IOException e) {
                    connectionStatus = false;
                }
            } catch (IllegalArgumentException e) {
                connectionStatus = false;
            }

            mBluetoothAdapter.cancelDiscovery();

            try {
                btSocket.connect();
            } catch (IOException e1) {
                try {
                    btSocket.close();
                } catch (IOException e2) {
                }
            }


            try {
                outStream = btSocket.getOutputStream();
            } catch (IOException e2) {
                connectionStatus = false;
            }

            // Send final result
            if (connectionStatus) {
                mHandler.sendEmptyMessage(1);
            } else {
                mHandler.sendEmptyMessage(0);
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    // Show please wait dialog
                    myProgressDialog = ProgressDialog.show(this, getResources()
                            .getString(R.string.pleaseWait), getResources()
                            .getString(R.string.makingConnectionString), true);

                    // Get the device MAC address
                    deviceAddress = data.getExtras().getString(
                            DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                    // Connect to device with specified MAC address
                    mConnectThread = new ConnectThread(deviceAddress);
                    mConnectThread.start();

                } else {
                    // Failure retrieving MAC address
                    Toast.makeText(this, R.string.macFailed, Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            case REQUEST_ENABLE_BT:
                // When the request to enable Bluetooth returns
                if (resultCode == Activity.RESULT_OK) {
                    // Bluetooth is now enabled
                } else {
                    // User did not enable Bluetooth or an error occured
                    Toast.makeText(this, R.string.bt_not_enabled_leaving,
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }

    public void write(byte data) {
        if (outStream != null) {
            try {
                outStream.write(data);
            } catch (IOException e) {
            }
        }
    }

    public void emptyOutStream() {
        if (outStream != null) {
            try {
                outStream.flush();
            } catch (IOException e) {
            }
        }
    }

    public void connect() {
        // Launch the DeviceListActivity to see devices and do scan
        Intent serverIntent = new Intent(this, DeviceListActivity.class);
        startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
    }

    public void disconnect() {
        if (outStream != null) {
            try {
                outStream.close();
                connectStat = false;
                connect_button.setText(R.string.disconnected);
            } catch (IOException e) {
            }
        }
    }
}