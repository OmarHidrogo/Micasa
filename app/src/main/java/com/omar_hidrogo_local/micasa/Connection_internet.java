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

public class Connection_internet extends AppCompatActivity {

    private Button btnSaveDeviceInternet;
    private EditText etDeviceInternet;
    private Toolbar toolbar;  //barra superior aplicacion
    public MainActivity mainActivity;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_internet);

        //entrar a las propiedades de la barra superios aplicacion
        toolbar = (Toolbar) findViewById(R.id.toolbarbar);

        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        //quitar el titulo por defecto al actionbar
        if (toolbar != null) {
            bar.setDisplayHomeAsUpEnabled(true);//poner boton de regresar en la parte superior
            bar.setDisplayShowTitleEnabled(false);
            getSupportActionBar().setTitle("Editar Conexion IP");
        }

        btnSaveDeviceInternet = (Button) findViewById(R.id.btnSaveDeviceInternet);

            btnSaveDeviceInternet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //editar preferencia
                    SharedPreferences.Editor edi = preferences.getSharedPreferencesInternet(getApplicationContext()).edit(); // se Extrae preferencia de conexion Internet de la clase Preferences
                    SharedPreferences.Editor edi1 = preferences.getSharedPreferencesmiprefConexion(getApplicationContext()).edit(); //se Extrae preferencia de conexion de la clase Preferences

                    //vincular edit text donde se agragara la direccion IP del equipo de control
                    etDeviceInternet = (EditText) findViewById(R.id.etDeviceInternet);
                    //guardar el valor de EditText
                    String deviceInternet = etDeviceInternet.getText().toString();
                    edi.putString("cInternet", deviceInternet);//se guarda la direccion IP
                    edi1.putInt("mconex", 2);// Se guarda el valor de la conexion
                    //editor1.putInt("mconex", 2);
                    edi.commit(); // Se guarda en la preferencia
                    edi1.commit(); // Se guarda en la preferencia

                    Toast.makeText(Connection_internet.this, R.string.toast013, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Connection_internet.this, Splash_screen.class);
                    startActivity(intent);
                    finish();
                }
            });


    }
}