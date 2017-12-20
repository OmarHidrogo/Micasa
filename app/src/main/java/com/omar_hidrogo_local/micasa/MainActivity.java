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

import java.util.UUID;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    private BluetoothAdapter btAdapter = null;
    private static final String TAG = "DeviceListActivity";

   /* //Bluetooth
    Handler bluetoothIn;
    final int handlerState = 0;
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private StringBuilder recDataString = new StringBuilder();

    private ConnectedThread mConnectedThread;

    // SPP UUID service - this should work for most devices
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    // String for MAC address
    private static String address = null;*/


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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        activity = this;

        /*bluetoothIn = new Handler() {
            public void handleMessage(android.os.Message msg){
                if (msg.what == handlerState){
                    String readMessage = (String) msg.obj;
                    recDataString.append(readMessage);
                    int endOfLineIndex = recDataString.indexOf("~");
                    if(endOfLineIndex > 0){
                        String dataInPrint = recDataString.substring(0,endOfLineIndex);

                    }
                }
            }
        }*/


        //entrar a las propiedades de la barra superios aplicacion
        toolbar =(Toolbar) findViewById(R.id.toolbarbar);

        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        //quitar el titulo por defecto al actionbar
        if(toolbar != null){
            bar.setDisplayShowTitleEnabled(false);
        }

        //inicializar un preferencia compartida.
        SharedPreferences miprefInternet = getSharedPreferences("cInternet", Context.MODE_PRIVATE);     // se inicializa preferencia donde cuardara la conexion  de la casa a controlar por Internet
        String cInternet = miprefInternet.getString("cInternet", "");                                       // se inicializa vacio
        SharedPreferences miprefBluetooth = getSharedPreferences("cBluetooth", Context.MODE_PRIVATE);   // se inicializa preferencia donde cuardara la conexion  de la casa a controlar por Bluetooth
        String cBluetooth = miprefBluetooth.getString("cBluetooth", "");
        SharedPreferences miprefIdb= getSharedPreferences("db", Context.MODE_PRIVATE);     // se inicializa preferencia donde cuardara la conexion  de la casa a controlar por Internet
        String db = miprefIdb.getString("db", ""); // se inicializa vacio


        //Si la preferencia compartida esta basia se redireccionara a conectarse a un dispositivo
        if (cInternet.equals("") && cBluetooth.equals("")){
            AlertDialog.Builder messageConnection = new AlertDialog.Builder(MainActivity.this);
            messageConnection.setMessage("Para continuar por favor selecciona metodo de conexion para el control de su casa")
                    .setCancelable(true)
                    .setPositiveButton("Internet", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(MainActivity.this, Connection_internet.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("Bluetooth", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            habilitarBluetooth();

                        }
                    });
            AlertDialog titulo = messageConnection.create();
            titulo.setTitle("Alerta!");
            titulo.show();

        }else{
            //Toast.makeText(MainActivity.this, "Ingrese las conexiones a controlar", Toast.LENGTH_LONG).show();
            habilitarBluetooth();

        }
        return;

    }

    public void habilitarBluetooth(){
        solicitarPermiso();

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null){
            Toast.makeText(MainActivity.this, "Tu dispositivo no tiene Bluetooth", Toast.LENGTH_SHORT).show();
        }
        if(!mBluetoothAdapter.isEnabled()){
            Intent habilitarBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(habilitarBluetoothIntent, CODIGO_SOLICITUD_HABILITAR_BLUETOOTH);
        }


    }
    public boolean chacarStatusPermiso(){
        //PREGUNTA SI EL PERMISO FUE OTORGADO EN LA APLICACION
        int resultado = ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH);
        if(resultado == PackageManager.PERMISSION_GRANTED){
            return  true;
        }else {
            return  false;
        }
    }

    //solicitar el permiso al usuario
    public  void solicitarPermiso() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.BLUETOOTH)) {
            Toast.makeText(MainActivity.this, "El permiso ya fue otorgado, si deseas desactivarlo puedes ir a los ajustes de la aplicacion", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.BLUETOOTH}, CODIGO_SOLICITUD_PERMISO);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CODIGO_SOLICITUD_PERMISO:
                if(chacarStatusPermiso()){
                   // Toast.makeText(MainActivity.this, "Ya esta activo el permiso para el Bluetooth", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "No esta activo el permiso para el Bluetooth", Toast.LENGTH_SHORT).show();
                }
                break;

        }
        SharedPreferences miprefInternet = getSharedPreferences("cInternet", Context.MODE_PRIVATE);
        SharedPreferences miprefBluetooth = getSharedPreferences("cBluetooth", Context.MODE_PRIVATE);
        String cInternet = miprefInternet.getString("cInternet", "");
        String cBluetooth = miprefBluetooth.getString("cBluetooth", "");
        if(cInternet.equals("") && cBluetooth.equals("")) {
            Intent intent = new Intent(MainActivity.this, Device_Lists.class);
            startActivity(intent);
            finish();

        }else
        {

            Intent intent = new Intent(MainActivity.this, Splash_screen.class);
            startActivity(intent);
            finish();
        }
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
                Intent intent = new Intent(this, Connection_internet.class);
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
                Intent intent5 = new Intent(this, Connection_internet.class);
                this.startActivity(intent5);
                break;
            case R.id.acerca:
                Intent intent4 = new Intent(this, Connection_internet.class);
                this.startActivity(intent4);
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    public void checkBTState() {
        // Check device has Bluetooth and that it is turned on
        btAdapter=BluetoothAdapter.getDefaultAdapter(); // CHECK THIS OUT THAT IT WORKS!!!
        if(btAdapter==null) {
            Toast.makeText(getBaseContext(), "Device does not support Bluetooth", Toast.LENGTH_SHORT).show();
        } else {
            if (btAdapter.isEnabled()) {
                Log.d(TAG, "...Bluetooth ON...");
            } else {
                //Prompt user to turn on Bluetooth
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
                /*AlertDialog.Builder messageConnection = new AlertDialog.Builder(Splash_screen.getContext());
                messageConnection.setMessage("Deseas que esta aplicacion envie solititud ")
                        .setCancelable(true)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                                startActivityForResult(enableBtIntent, 1);
                            }
                        })
                        .setNegativeButton("Bluetooth", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();

                            }
                        });
                AlertDialog titulo = messageConnection.create();
                titulo.setTitle("Atencion!");
                titulo.show();*/


            }
        }

    }



}
