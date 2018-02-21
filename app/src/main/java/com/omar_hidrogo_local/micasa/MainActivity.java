package com.omar_hidrogo_local.micasa;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.omar_hidrogo_local.micasa.Database.ConstructorDevices;
import com.omar_hidrogo_local.micasa.sharedPreferences.Preferences;

import java.util.UUID;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    //private BluetoothAdapter btAdapter = null;
    private static final String TAG = "DeviceListActivity";

   private static MainActivity instance;

    public MainActivity(){
        instance = this;

    }

    public static Context getContext(){
        return  instance;
    }


    public static final int CODIGO_SOLICITUD_PERMISO = 1;
    public static final int CODIGO_SOLICITUD_HABILITAR_BLUETOOTH = 0;
    public Context context;
    public static Activity activity;
    private Menu menu;
    private Toolbar toolbar;  //barra superior aplicacion
    private ConstructorDevices constructorDevices;
    private Splash_screen splash_screen;
    public Devices_controller devices_controller;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        activity = this;

        //entrar a las propiedades de la barra superios aplicacion
        toolbar =(Toolbar) findViewById(R.id.toolbarbar);

        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        //quitar el titulo por defecto al actionbar
        if(toolbar != null){
            bar.setDisplayShowTitleEnabled(false);
        }

        //inicializar un preferencia compartida.
       /* SharedPreferences miprefInternet = getSharedPreferences("cInternet", Context.MODE_PRIVATE);     // se inicializa preferencia donde cuardara la conexion  de la casa a controlar por Internet
        String cInternet = miprefInternet.getString("cInternet", "");                                       // se inicializa vacio
        SharedPreferences miprefBluetooth = getSharedPreferences("cBluetooth", Context.MODE_PRIVATE);   // se inicializa preferencia donde cuardara la conexion  de la casa a controlar por Bluetooth
        String cBluetooth = miprefBluetooth.getString("cBluetooth", "");
        SharedPreferences miprefIdb= getSharedPreferences("db", Context.MODE_PRIVATE);     // se inicializa preferencia donde cuardara la conexion  de la casa a controlar por Internet
        String db = miprefIdb.getString("db", ""); // se inicializa vacio
        SharedPreferences miprefConexion = getSharedPreferences("mconex", Context.MODE_PRIVATE);
        int mconex = miprefConexion.getInt("mconex", 0);*/


        int conex = preferences.getmiprefConexion(getApplicationContext());
        if(conex==0){
            AlertDialog.Builder messageConnection = new AlertDialog.Builder(MainActivity.this);
            messageConnection.setMessage(R.string.AlertDialog01)
                    .setCancelable(true)
                    .setPositiveButton(R.string.internet, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(MainActivity.this, Connection_internet.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.bluetooth, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // habilitarBluetooth();

                            //miprefInternet = getSharedPreferences("cInternet", Context.MODE_PRIVATE);
                            //miprefBluetooth = getSharedPreferences("cBluetooth", Context.MODE_PRIVATE);
                           // String cInternet = miprefInternet.getString("cInternet", "");
                           // String cBluetooth = miprefBluetooth.getString("cBluetooth", "");
                            //if(cInternet.equals("") && cBluetooth.equals("")) {

                                Intent intent = new Intent(MainActivity.this, Device_Lists.class);
                                startActivity(intent);
                                finish();
                            /*}/*else
                            {
                                //si  ya existe un dispositivo bluetooth guardado va directo a la actividad de los dispositivos de la casa a controlar
                                Intent intent = new Intent(MainActivity.this, Splash_screen.class);
                                startActivity(intent);
                                finish();
                            }*/

                        }
                    });
            AlertDialog titulo = messageConnection.create();
            titulo.setTitle(R.string.alert001);
            titulo.show();

        }else{
            //si  ya existe un dispositivo bluetooth guardado va directo a la actividad de los dispositivos de la casa a controlar
            Intent intent = new Intent(MainActivity.this, Splash_screen.class);
            startActivity(intent);
            finish();

        }
        return;
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
                Intent intent = new Intent(this, Splash_screen.class);
                this.startActivity(intent);
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







}
