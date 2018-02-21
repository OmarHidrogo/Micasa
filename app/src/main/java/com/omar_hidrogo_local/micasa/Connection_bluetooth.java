package com.omar_hidrogo_local.micasa;

import android.content.Context;
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

import com.omar_hidrogo_local.micasa.sharedPreferences.Preferences;

public class Connection_bluetooth extends AppCompatActivity {

    private Button btnsavedevice;
    private EditText etDeviceBluetooth;
    private Toolbar toolbar;  //barra superior aplicacion
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_bluetooth);

        //entrar a las propiedades de la barra superios aplicacion
        toolbar = (Toolbar) findViewById(R.id.toolbarbar);

        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        //quitar el titulo por defecto al actionbar
        if (toolbar != null) {
            bar.setDisplayHomeAsUpEnabled(true);//poner boton de regresar en la parte superior
            bar.setDisplayShowTitleEnabled(false);
            //getSupportActionBar().setTitle("Editar Conexion Bluetooth");
        }

            btnsavedevice = (Button) findViewById(R.id.btnSaveDeviceBluetooth);


            btnsavedevice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //crear Share preference
                    //editar preferencia
                    SharedPreferences.Editor edi = preferences.getSharedPreferencesmiprefBluetooth(getApplicationContext()).edit(); // se Extrae preferencia de conexion Bluettoth de la clase Preferences
                    SharedPreferences.Editor edi1 = preferences.getSharedPreferencesmiprefConexion(getApplicationContext()).edit(); //se Extrae preferencia de conexion de la clase Preferences

                    //SharedPreferences miprefBluetooth = getSharedPreferences("cBluetooth", Context.MODE_PRIVATE);
                    //editar preferencia
                    //SharedPreferences.Editor editor = miprefBluetooth.edit();

                    //vincular edit text donde se agragara conexion a controlar
                    etDeviceBluetooth = (EditText) findViewById(R.id.etDeviceBluetooth);
                    String devicebluetooth = etDeviceBluetooth.getText().toString();

                    edi.putString("cBluetooth", devicebluetooth);//guardar la direccion MAC del bluettoth
                    edi1.putInt("cBluetooth", 1);//guardar la direccion MAC del bluettoth
                    edi.commit();
                    edi1.commit();

                    Toast.makeText(Connection_bluetooth.this, R.string.toast014, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Connection_bluetooth.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });


    }
}
