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

public class Connection_internet extends AppCompatActivity {

    private Button btnSaveDeviceInternet;
    private EditText etDeviceInternet;
    private Toolbar toolbar;  //barra superior aplicacion
    public MainActivity mainActivity;

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

            btnSaveDeviceInternet = (Button) findViewById(R.id.btnSaveDeviceInternet);

            btnSaveDeviceInternet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //crear Share preference
                    SharedPreferences miprefInternet = getSharedPreferences("cInternet", Context.MODE_PRIVATE);
                    SharedPreferences miprefConexion = getSharedPreferences("mconex", Context.MODE_PRIVATE);
                    //editar preferencia
                    SharedPreferences.Editor editor = miprefInternet.edit();
                    SharedPreferences.Editor editor1 = miprefConexion.edit();
                    //vincular edit text donde se agragara conexion a controlar
                    etDeviceInternet = (EditText) findViewById(R.id.etDeviceInternet);
                    //guardar conexion en la preferencia
                    String deviceInternet = etDeviceInternet.getText().toString();
                    editor.putString("cInternet", deviceInternet);
                    editor1.putString("mconex", "1");
                    editor.commit();
                    editor1.commit();

                    Toast.makeText(Connection_internet.this, "El dispositivo IP fue guardado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Connection_internet.this, Splash_screen.class);
                    startActivity(intent);
                    finish();
                }
            });
        }


    }
}