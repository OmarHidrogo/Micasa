package com.omar_hidrogo_local.micasa;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.omar_hidrogo_local.micasa.Conunication.Network;
import com.omar_hidrogo_local.micasa.adaptador.PageAdapter;
import com.omar_hidrogo_local.micasa.fragment.Fragment_RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class Splash_screen extends AppCompatActivity {

    private static final String TAG = "DeviceListActivity";

    private static Splash_screen instance;

    public Splash_screen(){
        instance = this;
    }

    public static Context getContext(){
        return  instance;
    }


    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private BluetoothAdapter btAdapter = null;
    public static BluetoothSocket btSocket = null;
    // SPP UUID service - this should work for most devices
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    //private ConnectedThread mConnectedThread;
    private Activity activity;

    // String for MAC address
    private static String address = null;

    private int v1 =0;
    private BluetoothSocket btSocket1 = null;

    public int getV1() {
        return v1;
    }

    public BluetoothSocket getBtSocket1() {
        return btSocket1;
    }

    public int mconex;

    //variable para activar retardo de ejecucion de codigo
    private Handler mHandler = new Handler();

    public  WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        toolbar = (Toolbar) findViewById(R.id.toolbarbar);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        setUpViewPager();

        //establecer informacion de accion bar
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        //quitar el titulo por defecto al actionbar
        if(toolbar != null){
            bar.setDisplayShowTitleEnabled(false);
        }

        SharedPreferences miprefConexion = getSharedPreferences("mconex", Context.MODE_PRIVATE);
       mconex = miprefConexion.getInt("mconex", 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        //mainActivity.habilitarBluetooth();
        if(mconex ==1) {
            checkBTState();
        }else{
            //isConnectedWifi(getContext());
            checkwifiState(getContext());
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        try
        {
            //Don't leave Bluetooth sockets open when leaving activity
            btSocket.close();
        } catch (IOException e2) {
            //insert code to deal with this
        }
    }


    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragment_RecyclerView());
        return fragments;
    }


    private void setUpViewPager() {

        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ccasa:
                /*Intent intent = new Intent(this, Connection_internet.class);
                this.startActivity(intent);*/
                break;
            case R.id.nconexion:
                Intent intent2 = new Intent(this, Devices_controller.class);
                this.startActivity(intent2);
                break;
            case R.id.cbluetooth:
                Intent intent3 = new Intent(this, Device_Lists.class);
                this.startActivity(intent3);
                break;
            case R.id.cInternet:
                Intent intent5 = new Intent(this, Connection_internet.class);
                this.startActivity(intent5);
                break;
            case R.id.acerca:
                Intent intent4 = new Intent(this, Acerca_de.class);
                this.startActivity(intent4);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Checks that the Android device Bluetooth is available and prompts to be turned on if off
    public void checkBTState() {
        // Check device has Bluetooth and that it is turned on
        btAdapter=BluetoothAdapter.getDefaultAdapter(); // CHECK THIS OUT THAT IT WORKS!!!
        if(btAdapter==null) {
            Toast.makeText(getBaseContext(), "Device does not support Bluetooth", Toast.LENGTH_SHORT).show();
        } else {
            if (btAdapter.isEnabled()) {
                Log.d(TAG, "...Bluetooth ON...");
                btAdapter = BluetoothAdapter.getDefaultAdapter();       // get Bluetooth adapter

                SharedPreferences miprefBluetooth = getSharedPreferences("cBluetooth", Context.MODE_PRIVATE);  // se inicializa preferencia donde cuardara la conexion  de la casa a controlar por Bluetooth
                address = miprefBluetooth.getString("cBluetooth", "");
                BluetoothDevice device = btAdapter.getRemoteDevice(address);
                try {
                    btSocket = createBluetoothSocket(device);
                } catch (IOException e) {
                    Toast.makeText(getBaseContext(), "Se desvinculo el dispositivo bluetooth", Toast.LENGTH_LONG).show();
                }
                // Establish the Bluetooth socket connection.
                try
                {
                    btSocket.connect();
                } catch (IOException e) {
                    try
                    {
                        btSocket.close();
                    } catch (IOException e2)
                    {
                        //insert code to deal with this
                    }
                }
            } else {
                //Prompt user to turn on Bluetooth
                Log.d(TAG, "...Pregunta para Bluetooth ON...");

                AlertDialog.Builder messageConnection = new AlertDialog.Builder(Splash_screen.getContext());
                messageConnection.setMessage("La aplicacion esta configurada para conectarse por Bluetooth, desea activar el Bluetooth")
                        .setCancelable(false)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                btAdapter.enable();
                                //ejecutar lo siguiente despues de 2 segundos
                                mHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btAdapter = BluetoothAdapter.getDefaultAdapter();       // get Bluetooth adapter

                                        SharedPreferences miprefBluetooth = getSharedPreferences("cBluetooth", Context.MODE_PRIVATE);  // se inicializa preferencia donde cuardara la conexion  de la casa a controlar por Bluetooth
                                        address = miprefBluetooth.getString("cBluetooth", "");
                                        BluetoothDevice device = btAdapter.getRemoteDevice(address);
                                        try {
                                            btSocket = createBluetoothSocket(device);
                                        } catch (IOException e) {
                                            Toast.makeText(getBaseContext(), "Se desvinculo el dispositivo bluetooth", Toast.LENGTH_LONG).show();
                                        }
                                        // Establish the Bluetooth socket connection.
                                        try
                                        {
                                            btSocket.connect();
                                        } catch (IOException e) {
                                            try
                                            {
                                                btSocket.close();
                                            } catch (IOException e2)
                                            {
                                                //insert code to deal with this
                                            }
                                        }

                                    }
                                }, 2000);// 2 segundos

                            }

                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences miprefConexion = getSharedPreferences("mconex", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor1 = miprefConexion.edit();
                                editor1.putInt("mconex", 0);
                                editor1.commit();
                                Intent intent = new Intent(Splash_screen.this, MainActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        });
                AlertDialog titulo = messageConnection.create();
                titulo.setTitle("Alerta!");
                titulo.show();
            }
        }

    }

    public static boolean isConnectedWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    public void checkwifiState(Context context){
        wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        if(wifiManager.isWifiEnabled()){
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivityManager.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED) {
                Toast.makeText(getBaseContext(), "La aplicacion esta configurada ", Toast.LENGTH_LONG).show();
                startActivity(new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK));
            }
        }else{
            AlertDialog.Builder messageConnection = new AlertDialog.Builder(Splash_screen.getContext());
            messageConnection.setMessage("La aplicacion esta configurada para conectarse por WIFI, deseas activar el WIFI")
                    .setCancelable(false)
                    .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            wifiManager.setWifiEnabled(true);
                            Log.d(TAG, "...Wifi ON...");
                        }

                    })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences miprefConexion = getSharedPreferences("mconex", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = miprefConexion.edit();
                            editor1.putInt("mconex", 0);
                            editor1.commit();
                            Intent intent = new Intent(Splash_screen.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                        }
                    });
            AlertDialog titulo = messageConnection.create();
            titulo.setTitle("Alerta!");
            titulo.show();


        }

    }

    /*public boolean Network(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();
        return (actNetInfo != null && actNetInfo.isConnected());
    }*/

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {

        return  device.createRfcommSocketToServiceRecord(BTMODULEUUID);
        //creates secure outgoing connecetion with BT device using UUID
    }

    public void conexion (String v){
        /*mConnectedThread = new ConnectedThread(btSocket);
        mConnectedThread.start();*/
        //mConnectedThread.write(v);

    }

   /* //cLASE DE CONNECT THREAD
    public class ConnectedThread extends Thread {

        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        Handler bluetoothIn;
        final int handlerState = 0;

        public ConnectedThread(BluetoothSocket socket){
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                //Create I/O streams for connection
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[256];
            int bytes;

            // Keep looping to listen for received messages
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);         //read bytes from input buffer
                    String readMessage = new String(buffer, 0, bytes);
                    // Send the obtained bytes to the UI Activity via handler
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }

        //write method
        public void write(String input) {
            byte[] msgBuffer = input.getBytes();           //converts entered String into bytes
            try {
                mmOutStream.write(msgBuffer);                //write bytes over BT connection via outstream
            } catch (IOException e) {
                //if you cannot write, close the application
                Toast.makeText(Splash_screen.getContext(), "La Conexión fallo", Toast.LENGTH_LONG).show();
            }
        }
    }*/




}
