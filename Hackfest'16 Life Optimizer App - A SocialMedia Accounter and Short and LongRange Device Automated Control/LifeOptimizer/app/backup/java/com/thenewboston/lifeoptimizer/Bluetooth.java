package com.thenewboston.lifeoptimizer;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.thenewboston.lifeoptimizer.R;

import java.util.ArrayList;
import java.util.Set;

public class Bluetooth extends AppCompatActivity implements View.OnClickListener {
    TextView textview1;
    Button scan, connect, control;
    private static final int REQUEST_ENABLE_BT = 1;
    BluetoothAdapter btAdapter;
    BroadcastReceiver mReceiver;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        textview1 = (TextView) findViewById(R.id.tvBluetooth);
        scan = (Button) findViewById((R.id.bBlutoothScan));
        connect = (Button) findViewById((R.id.bBluetoothConnect));
        control = (Button) findViewById(R.id.bBluetoothControl);

        scan.setOnClickListener(this);
        connect.setOnClickListener(this);
        control.setOnClickListener(this);


        // Getting the Bluetooth adapter
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        textview1.append("\nAdapter: " + btAdapter);


    }

    /* It is called when an activity completes.*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT) {
            CheckBluetoothState();
            textview1.append("\n These are the Paired Devices. To add New Devices or Connect to one :");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void CheckBluetoothState() {
        // Checks for the Bluetooth support and then makes sure it is turned on
        // If it isn't turned on, request to turn it on
        // List paired devices
        if (btAdapter == null) {
            textview1.append("\nBluetooth NOT supported. Aborting.");
            return;
        } else {
            if (btAdapter.isEnabled()) {
                textview1.append("\nBluetooth is enabled...");

                // Listing paired devices
                textview1.append("\nPaired Devices are:");
                Set<BluetoothDevice> devices = btAdapter.getBondedDevices();
                for (BluetoothDevice device : devices) {
                    textview1.append("\n  Device: " + device.getName() + device);
                }
            } else {
                //Prompt user to turn on Bluetooth
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bBlutoothScan:
                textview1.setText("");
                CheckBluetoothState();
                break;
            case R.id.bBluetoothConnect:
                Intent intentOpenBluetoothSettings = new Intent();
                intentOpenBluetoothSettings.setAction(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
                startActivity(intentOpenBluetoothSettings);
                break;
            case R.id.bBluetoothControl:
              //  IntentFilter filter1 = new IntentFilter(BluetoothDevice.ACTION_ACL_CONNECTED);
               // IntentFilter filter2 = new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
                //IntentFilter filter3 = new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECTED);

                //this.registerReceiver(mReceiver, filter1);
                //this.registerReceiver(mReceiver, filter2);
                //this.registerReceiver(mReceiver, filter3);
                //The BroadcastReceiver that listens for bluetooth broadcasts
               // final BroadcastReceiver BTReceiver = new BroadcastReceiver() {
                 //   @Override
                   // public void onReceive(Context context, Intent intent) {
                     //   String action = intent.getAction();

                       // if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
                         //   //Do something if connected
                           // Toast.makeText(getApplicationContext(), "BT Connected", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Bluetooth.this, BTModule.class);
                            startActivity(i);
                        //}
                        //else if...
                    //}
                //};
                break;
        }

    }


}