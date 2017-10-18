package com.omar_hidrogo_local.micasa;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.omar_hidrogo_local.micasa.Database.ConstanteDataBase;
import com.omar_hidrogo_local.micasa.Database.DataBase;
import com.omar_hidrogo_local.micasa.pojo.Devices;

import java.util.ArrayList;

public class Devices_controller extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //declaracion de objetos en activity
    private Toolbar toolbar;  //barra superior aplicacion
    private Button btnsaveconnection;
    private EditText editText;
    private Spinner spiner;
    private Context context;

    public Devices_controller(Context context){
        this.context = context;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices_controller);

        //Se enlaza a las propiedades de cada objeto del activity
        toolbar =(Toolbar) findViewById(R.id.toolbarbar);
        btnsaveconnection = (Button) findViewById(R.id.btnsaveconnection);
        spiner = (Spinner) findViewById(R.id.spinner);
        editText = (EditText) findViewById(R.id.etnamedevice);

        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        //quitar el titulo por defecto al actionbar
        if(toolbar != null){
            bar.setDisplayHomeAsUpEnabled(true);//poner boton de regresar en la parte superior
            bar.setDisplayShowTitleEnabled(false);
        }


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.channel_Arduino, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner.setAdapter(adapter);
        spiner.setOnItemSelectedListener(this);

        btnsaveconnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBase admin = new DataBase(Devices_controller.this, ConstanteDataBase.TABLE_DEVICES, null, ConstanteDataBase.DATABASE_VERSION);
                SQLiteDatabase db =admin.getWritableDatabase();
                String nombredevice = editText.getText().toString();
                String channeldevice = spiner.getItemAtPosition(spiner.getSelectedItemPosition()).toString();
                ContentValues registro = new ContentValues();
                registro.put(ConstanteDataBase.TABLE_DEVICES_NAME, nombredevice);
                registro.put(ConstanteDataBase.TABLE_DEVICES_CHANNEL_ID, channeldevice);
                db.insert(ConstanteDataBase.TABLE_DEVICES, null, registro);
                db.close();
                //startActivity(intent);
                editText.setText("");
                //startActivity(intent);
                Toast.makeText(Devices_controller.this, "Se Guardo nuevo dispositivo",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(parent.getContext(), (String) parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public ArrayList<Devices> obtenerDatos(){
        DataBase db = new DataBase(Devices_controller.this, ConstanteDataBase.TABLE_DEVICES, null, ConstanteDataBase.DATABASE_VERSION);
        return db.obtenerTodasLasDevices();
    }


}
