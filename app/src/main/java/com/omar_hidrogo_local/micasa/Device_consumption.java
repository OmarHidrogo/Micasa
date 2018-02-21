package com.omar_hidrogo_local.micasa;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.omar_hidrogo_local.micasa.Class.Calendario;
import com.omar_hidrogo_local.micasa.Database.ConstructorDevices;
import com.omar_hidrogo_local.micasa.pojo.ConsumptionDevice;
import com.omar_hidrogo_local.micasa.sharedPreferences.Preferences;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Device_consumption extends AppCompatActivity {

    //declaracion de objetos en activity
    private Toolbar toolbar;  //barra superior aplicacion
    private Button btntime1, btntime2, btncalcular;
    public static EditText ettime1;
    public static EditText ettime2;
    private TextView etnamedevice;
    private  String channel;
    private int idd;
    public static EditText etallwatts, etallco2, etallpay;
    public static int wtts;
    public static long fechade=0, fechafin=0;
    private ConstructorDevices constructorDevices;
    private ArrayList<ConsumptionDevice> consumptionDevices;
    private Preferences preferences;

    private double vt;
    private long v1;
    private long v2;
    //Calculo de consumo Electrico
    private long milisegundos=1000;
    private long minutos=milisegundos*60;
    private double horas=minutos*60;
    private double dias=horas*24;
    private double co2=0;//0.000454;
    private double pago=0;//0.62;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_consumption);


        //Se enlaza a las propiedades de cada objeto del activity
        toolbar =(Toolbar) findViewById(R.id.action_bar_consumption);
        etnamedevice = (TextView) findViewById(R.id.etnamedevice);
        btntime1 =(Button) findViewById(R.id.b1);
        btntime2 =(Button) findViewById(R.id.b2);
        btncalcular = (Button) findViewById(R.id.b3);
        ettime1 = (EditText) findViewById(R.id.ettime1);
        ettime2 = (EditText) findViewById(R.id.ettime2);

        etallwatts = (EditText) findViewById(R.id.etallwatts);
        etallco2 = (EditText) findViewById(R.id.etallco2);
        etallpay = (EditText) findViewById(R.id.etallpay);


        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        //quitar el titulo por defecto al actionbar
        if(toolbar != null) {
            bar.setDisplayHomeAsUpEnabled(true);//poner boton de regresar en la parte superior
            bar.setDisplayShowTitleEnabled(false);//Desabilitar
            //bar.setTitle(R.string.device_consumption);
            //bar.setIcon(R.drawable.ic_power);
        }

        //es necesario recibir los parametros enviados de un Intent en Bundle
        Bundle extras = getIntent().getExtras();
        final int id = extras.getInt("id");
        String name = extras.getString("name");
        channel =  extras.getString("channel");
        final int photo = extras.getInt("photo");
        int state = extras.getInt("state");
        String about = extras.getString("about");
        final int watts = extras.getInt("watts");
        String wat = String.valueOf(watts);

        etnamedevice.setText(name);
        idd=id;
        wtts=watts;




       btncalcular.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                constructorDevices = new ConstructorDevices();
                if(fechade==0 || fechafin==0) {
                    AlertDialog.Builder messageConnection = new AlertDialog.Builder(Device_consumption.this);
                    messageConnection.setMessage(R.string.AlertDialog04)
                            .setCancelable(true)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    consumptionDevices = constructorDevices.obtenerconsumoporDevice(idd);
                                    calculate();
                                }
                            })
                            .setNegativeButton(R.string.not, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // habilitarBluetooth();

                                    //miprefInternet = getSharedPreferences("cInternet", Context.MODE_PRIVATE);
                                    //miprefBluetooth = getSharedPreferences("cBluetooth", Context.MODE_PRIVATE);
                                    // String cInternet = miprefInternet.getString("cInternet", "");
                                    // String cBluetooth = miprefBluetooth.getString("cBluetooth", "");
                                    //if(cInternet.equals("") && cBluetooth.equals("")) {

                                    /*Intent intent = new Intent(Device_consumption.this, Device_Lists.class);
                                    startActivity(intent);
                                    finish();*/
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
                    titulo.setTitle(R.string.alert002);
                    titulo.show();
                }else{
                    // Code here executes on main thread after user presses button
                    consumptionDevices = constructorDevices.obtenerconsumoDevice(fechade, fechafin,idd );
                    calculate();
                }
            }
        });

    }

    /*@Override
    public void onResume() {
        super.onResume();
        //mainActivity.habilitarBluetooth();

    }*/

    @Override
    public void onPause()
    {
        super.onPause();
        //mainActivity.habilitarBluetooth();
        fechade=0;
        fechafin=0;
    }



    //PARA LLAMAR A POPUP FECHA
    public void onManejadorEventoFecha(View v) {
        DialogFragment newFragment = new Calendario();// se enlaza a la clase Calendario para importar la funcion de calendario
        Bundle args = new Bundle();
        int vista = v.getId(); //.getId();
        args.putLong("vista",vista);
        newFragment.setArguments(args);
        newFragment.show(getFragmentManager(), "datePicker");

    }

    //Calcular el Consumo del Dispositivo
    public void calcularConsumo(View v){
        /*Bundle extras = getIntent().getExtras();
        String ett1 = extras.getString("ett1");
        String ett2 = extras.getString("ett2");*/
        constructorDevices = new ConstructorDevices();
        constructorDevices.obtenerconsumoDevice(fechade, fechafin,idd );

    }

    public void calculate(){

         co2= Double.parseDouble(preferences.getmiprefvalorCO(getApplicationContext()));
         pago= Double.parseDouble(preferences.getmiprefvalorenergy(getApplicationContext()));
        if(pago==0) {
            Toast.makeText(Device_consumption.this, R.string.toast008, Toast.LENGTH_SHORT).show();
        }else{
            if(co2==0){
                Toast.makeText(Device_consumption.this, R.string.toast009, Toast.LENGTH_SHORT).show();

            }else{
                for (int i = 0; i < consumptionDevices.size(); i++){


                    ConsumptionDevice consumptionDevice = consumptionDevices.get(i);

                    if(consumptionDevice.getStatus()!=0){
                        v1= consumptionDevice.getMillis();
                        //long v2=consumptionDevice.getMillis();
                    }else{
                        v2= consumptionDevice.getMillis();
                        vt = vt +(v2 - v1);
                    }

                }
                vt = vt/horas;
                vt=vt*wtts;
                co2= co2*vt;
                pago=pago*(vt/1000);
                DecimalFormat f = new DecimalFormat("##.0000");
                etallwatts.setText(String.valueOf(f.format(vt)));
                etallco2.setText(String.valueOf(f.format(co2))+R.string.kilograms);
                etallpay.setText("$ "+String.valueOf(f.format(pago)));
            }
        }
    }
}
