package com.omar_hidrogo_local.micasa;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.omar_hidrogo_local.micasa.adaptador.PageAdapter;
import com.omar_hidrogo_local.micasa.fragment.Fragment_RecyclerView;
import com.omar_hidrogo_local.micasa.sharedPreferences.Preferences;

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
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");  // SPP UUID service - this should work for most devices
    private Activity activity; //private ConnectedThread mConnectedThread;
    private static String address = null; // String for MAC address
    private int v1 =0;
    private BluetoothSocket btSocket1 = null;
    public int getV1() {
        return v1;
    }
    public BluetoothSocket getBtSocket1() {
        return btSocket1;
    }
    public int mconex;
    private Handler mHandler = new Handler(); //variable para activar retardo de ejecucion de codigo
    public  WifiManager wifiManager;
    private Preferences preferences;

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
       mconex = preferences.getmiprefConexion(getApplicationContext());
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
        //mainActivity.habilitarBluetooth();
        if(mconex ==1) {
            try {
                //Don't leave Bluetooth sockets open when leaving activity
                btSocket.close();
            } catch (IOException e2) {
                //insert code to deal with this
            }
        }
    }


    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragment_RecyclerView());
        fragments.add(new Device_consumption_log());
        return fragments;
    }


    private void setUpViewPager() {

        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_power);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            /*case R.id.ccasa:
                Intent intent = new Intent(this, Splash_screen.class);
                this.startActivity(intent);
                break;*/
            case R.id.nconexion:
                Intent intent2 = new Intent(this, Devices_controller.class);
                this.startActivity(intent2);
                break;
            case R.id.cbluetooth:
                Intent intent3 = new Intent(this, Device_Lists.class);
                this.startActivity(intent3);
                break;
            case R.id.cInternet:
                Intent intent4 = new Intent(this, Connection_internet.class);
                this.startActivity(intent4);
                break;
            case R.id.datosenergia:
                Intent intent5 = new Intent(this, Datos_proveedor.class);
                this.startActivity(intent5);
                break;
            case R.id.acerca:
                Intent intent6 = new Intent(this, Acerca_de.class);
                this.startActivity(intent6);
                break;
            case R.id.close:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Checks that the Android device Bluetooth is available and prompts to be turned on if off
    public void checkBTState() {
        // Check device has Bluetooth and that it is turned on
        btAdapter=BluetoothAdapter.getDefaultAdapter(); // CHECK THIS OUT THAT IT WORKS!!!
        if(btAdapter==null) {
            Toast.makeText(getBaseContext(), R.string.toast001, Toast.LENGTH_SHORT).show();
        } else {
            if (btAdapter.isEnabled()) {
                Log.d(TAG, "...Bluetooth ON...");
                btAdapter = BluetoothAdapter.getDefaultAdapter();       // get Bluetooth adapter
                address =preferences.getmiprefBluetooth(MainActivity.getContext());
                BluetoothDevice device = btAdapter.getRemoteDevice(address);
                try {
                    btSocket = createBluetoothSocket(device);
                } catch (IOException e) {
                    Toast.makeText(getBaseContext(),R.string.toast002, Toast.LENGTH_LONG).show();
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
                Log.d(TAG, "...Bluetooth ON...");

                AlertDialog.Builder messageConnection = new AlertDialog.Builder(Splash_screen.getContext());
                messageConnection.setMessage(R.string.AlertDialog02)
                        .setCancelable(false)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                btAdapter.enable();
                                //ejecutar lo siguiente despues de 2 segundos
                                mHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btAdapter = BluetoothAdapter.getDefaultAdapter();       // get Bluetooth adapter
                                        address =preferences.getmiprefBluetooth(MainActivity.getContext());
                                        BluetoothDevice device = btAdapter.getRemoteDevice(address);
                                        try {
                                            btSocket = createBluetoothSocket(device);
                                        } catch (IOException e) {
                                            Toast.makeText(getBaseContext(), R.string.toast002, Toast.LENGTH_LONG).show();
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
                        .setNegativeButton(R.string.not, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences.Editor editor1 = preferences.getSharedPreferencesmiprefConexion(getApplicationContext()).edit(); // se Extrae preferencia de conexion Internet de la clase Preferences
                                editor1.putInt("mconex", 0);
                                editor1.commit();
                                Intent intent = new Intent(Splash_screen.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                AlertDialog titulo = messageConnection.create();
                titulo.setTitle(R.string.alert001);
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
            messageConnection.setMessage(R.string.AlertDialog03)
                    .setCancelable(false)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            wifiManager.setWifiEnabled(true);
                            Log.d(TAG, "...Wifi ON...");
                        }

                    })
                    .setNegativeButton(R.string.not, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences.Editor editor1 = preferences.getSharedPreferencesmiprefConexion(getApplicationContext()).edit(); // se Extrae preferencia de conexion Internet de la clase Preferences
                            editor1.putInt("mconex", 0);
                            editor1.commit();
                            Intent intent = new Intent(Splash_screen.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
            AlertDialog titulo = messageConnection.create();
            titulo.setTitle(R.string.alert001);
            titulo.show();


        }

    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {

        return  device.createRfcommSocketToServiceRecord(BTMODULEUUID);
        //creates secure outgoing connecetion with BT device using UUID
    }

    /*public void conexion (String v){
        mConnectedThread = new ConnectedThread(btSocket);
        mConnectedThread.start();
        mConnectedThread.write(v);

    }*/

}
