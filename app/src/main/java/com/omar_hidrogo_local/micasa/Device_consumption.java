package com.omar_hidrogo_local.micasa;

import android.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.omar_hidrogo_local.micasa.Class.Calendario;
import com.omar_hidrogo_local.micasa.Database.ConstructorDevices;

public class Device_consumption extends AppCompatActivity {

    //declaracion de objetos en activity
    private Toolbar toolbar;  //barra superior aplicacion
    private Button btntime1, btntime2, btncalcular;
    public static EditText ettime1;
    public static EditText ettime2;
    private TextView etnamedevice;
    private  String channel;
    private int idd;
    public static EditText etwattstotal, etco2total, etpagototal;
    public static int wtts;
    public static String fechade, fechafin;

    private com.omar_hidrogo_local.micasa.Database.ConstructorDevices constructorDevices;

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


        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        //quitar el titulo por defecto al actionbar
        if(toolbar != null) {
            bar.setDisplayHomeAsUpEnabled(true);//poner boton de regresar en la parte superior
            bar.setDisplayShowTitleEnabled(true);//Desabilitar
            bar.setTitle(R.string.device_consumption);
            bar.setIcon(R.drawable.ic_power);
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
        //newFragment.show(getFragmentManager(), "datePicker");

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
}
