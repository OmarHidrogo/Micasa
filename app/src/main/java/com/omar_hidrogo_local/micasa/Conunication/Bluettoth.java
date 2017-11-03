package com.omar_hidrogo_local.micasa.Conunication;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.omar_hidrogo_local.micasa.MainActivity;
import com.omar_hidrogo_local.micasa.Splash_screen;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by tmhidrooma on 01/11/2017.
 */

public class Bluettoth extends AppCompatActivity {

    //private BluetoothSocket btSocket = null;
    private static BluetoothAdapter btAdapter;
    private static String address = null;
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private ConnectedThread mConnectedThread;




    public void cbt(String v) {

        mConnectedThread = new ConnectedThread(Splash_screen.btSocket);
        mConnectedThread.start();
        mConnectedThread.write(v);
    }

}
