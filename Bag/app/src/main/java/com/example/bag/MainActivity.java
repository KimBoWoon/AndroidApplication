package com.example.bag;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class MainActivity extends Activity implements InputTextDialog.ButtonListener {
    private static final String TAG = "Main";
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    private static final UUID SPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private BluetoothService btService = null;
    private boolean lockAndRele = false;
    private boolean onAndOff = true;
    private BluetoothAdapter mBluetoothAdapter = null;
    private BluetoothSocket btSocket = null;
    private OutputStream outStream = null;
    private ConnectThread mConnectThread = null;
    private String deviceAddress = null;
    private ProgressDialog myProgressDialog;
    private Handler mHandler;
    private ConnectedThread connectedThread;
    private boolean bluetooth = true;
    private Button bluetoothButton;

    Button.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.input:
                    InputTextDialog mMainDialog = new InputTextDialog();
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
                    connectedThread = new ConnectedThread(btSocket);

                    if (onAndOff == true) {
                        onButton.setImageResource(R.drawable.off);
                        connectedThread.write("1".getBytes());
                        onAndOff = false;
                    } else {
                        onButton.setImageResource(R.drawable.on);
                        connectedThread.write("2".getBytes());
                        onAndOff = true;
                    }
                    break;
                case R.id.bluetooth:
                    bluetoothButton.setText("Bluetooth Connect");

                    if (bluetooth == true) {
                        btService.enableBluetooth();
                    } else {
                        try {
                            btSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        bluetooth = true;
                    }
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

    private void init() {
        findViewById(R.id.input).setOnClickListener(onClick);
        findViewById(R.id.location).setOnClickListener(onClick);
        findViewById(R.id.weight).setOnClickListener(onClick);
        findViewById(R.id.lockAndRele).setOnClickListener(onClick);
        findViewById(R.id.track).setOnClickListener(onClick);
        findViewById(R.id.on).setOnClickListener(onClick);
        findViewById(R.id.bluetooth).setOnClickListener(onClick);

        bluetoothButton = (Button) findViewById(R.id.bluetooth);
        btService = new BluetoothService(MainActivity.this, mHandler);
        myProgressDialog = new ProgressDialog(this);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, R.string.no_bt_device, Toast.LENGTH_LONG).show();
        }

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (myProgressDialog.isShowing()) {
                    myProgressDialog.dismiss();
                }
                if (msg.what == 1) {
                    bluetooth = false;
                    bluetoothButton.setText("Bluetooth Disconnect");
                }
            }
        };
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult " + resultCode);

        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    // Show please wait dialog
                    myProgressDialog = ProgressDialog.show(MainActivity.this, getResources().getString(R.string.pleaseWait), getResources().getString(R.string.makingConnectionString), true);

                    // Get the device MAC address
                    deviceAddress = data.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                    // Connect to device with specified MAC address
                    mConnectThread = new ConnectThread(deviceAddress);
                    mConnectThread.start();
                }
                break;
            case REQUEST_ENABLE_BT:
                // When the request to enable Bluetooth returns
                if (resultCode == Activity.RESULT_OK) {
                    // Bluetooth is now enabled
                } else {
                    // User did not enable Bluetooth or an error occured
                    Toast.makeText(this, R.string.bt_not_enabled_leaving, Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
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
                BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);

                try {
                    btSocket = device.createRfcommSocketToServiceRecord(SPP_UUID);
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

    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the BluetoothSocket input and output streams
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Log.e(TAG, "temp sockets not created", e);
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            Log.i(TAG, "BEGIN mConnectedThread");
            byte[] buffer = new byte[1024];
            int bytes;

            // Keep listening to the InputStream while connected
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.read(buffer);

                    // Send the obtained bytes to the UI Activity
                    mHandler.obtainMessage(Constants.MESSAGE_READ, bytes, -1, buffer)
                            .sendToTarget();
                } catch (IOException e) {
                    Log.e(TAG, "disconnected", e);
                    //connectionLost();
                    // Start the service over to restart listening mode
                    this.start();
                    break;
                }
            }
        }

        /**
         * Write to the connected OutStream.
         *
         * @param buffer The bytes to write
         */
        public void write(byte[] buffer) {
            try {
                mmOutStream.write(buffer);

                // Share the sent message back to the UI Activity
                mHandler.obtainMessage(Constants.MESSAGE_WRITE, -1, -1, buffer).sendToTarget();
            } catch (IOException e) {
                Log.e(TAG, "Exception during write", e);
            }
        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "close() of connect socket failed", e);
            }
        }
    }
}
