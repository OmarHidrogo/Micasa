package com.omar_hidrogo_local.micasa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.omar_hidrogo_local.micasa.Database.*;
import com.omar_hidrogo_local.micasa.sharedPreferences.Preferences;

import java.text.DecimalFormat;

public class Datos_proveedor extends AppCompatActivity {

    //declaracion de objetos en activity
    private Toolbar toolbar;  //barra superior aplicacion
    private EditText etprice, etco2;
    private Button btnsaveconnection;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_proveedor);


        //Se enlaza a las propiedades de cada objeto del activity

        toolbar =(Toolbar) findViewById(R.id.action_bar_configuracion_parametros);
        etprice = (EditText) findViewById(R.id.etprice);
        etco2 = (EditText) findViewById(R.id.etco2);
        btnsaveconnection = (Button) findViewById(R.id.btnsaveconnection);


        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        //quitar el titulo por defecto al actionbar
        if(toolbar != null){
            bar.setDisplayHomeAsUpEnabled(true);//poner boton de regresar en la parte superior
            bar.setDisplayShowTitleEnabled(false);

        }

        etprice.setText (preferences.getmiprefvalorenergy(getApplicationContext()));
        etco2.setText (preferences.getmiprefvalorCO(getApplicationContext()));

        btnsaveconnection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //editar preferencia
                SharedPreferences.Editor edi = preferences.getSharedPreferencesmiprefvalorenergy(getApplicationContext()).edit(); // se Extrae preferencia de costo precio energia de la clase Preferences
                SharedPreferences.Editor edi1 = preferences.getSharedPreferencesvalorCO(getApplicationContext()).edit(); //se Extrae preferencia de valor CO2 de la clase Preferences

                //guardar el valor de EditText
                String valorenergy = etprice.getText().toString();
                String valorCO = etco2.getText().toString();

                edi.putString("venergy", valorenergy);//se guarda la direccion IP
                edi1.putString("vco", valorCO);// Se guarda el valor de la conexion

                edi.commit(); // Se guarda en la preferencia
                edi1.commit(); // Se guarda en la preferencia

                Toast.makeText(Datos_proveedor.this, R.string.toast012, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Datos_proveedor.this, Splash_screen.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
