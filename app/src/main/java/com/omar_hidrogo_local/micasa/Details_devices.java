package com.omar_hidrogo_local.micasa;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.omar_hidrogo_local.micasa.Database.ConstructorDevices;

public class Details_devices extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    //declaracion de objetos en activity
    private Toolbar toolbar;  //barra superior aplicacion
    private Button btnupdate;
    private Button btndelete;
    private EditText editText, etabout;
    private Spinner spiner;
    private Context context;
    private com.omar_hidrogo_local.micasa.Database.ConstructorDevices constructorDevices;
    private Devices_controller devices_controller;
    private ListView listView;
    Activity activity;

    private int Slecteditem;
    private String Selecteditem;

    private String imagenes[]=new String[]{"Foco","Aire Acondicionado"};

    private Integer[] imgid= {
            R.drawable.focoapagado,
            R.drawable.aireapagado,
    };

    private  String channel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_devices);

        //Se enlaza a las propiedades de cada objeto del activity
        toolbar =(Toolbar) findViewById(R.id.toolbarbar);
        btnupdate = (Button) findViewById(R.id.btnupdate);
        btndelete = (Button) findViewById(R.id.btndelete);
        spiner = (Spinner) findViewById(R.id.spinner);
        editText = (EditText) findViewById(R.id.etnamedevice);
        etabout = (EditText) findViewById(R.id.etabout);
        listView = (ListView) findViewById(R.id.list_item);

        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        //quitar el titulo por defecto al actionbar
        if(toolbar != null){
            bar.setDisplayHomeAsUpEnabled(true);//poner boton de regresar en la parte superior
            bar.setDisplayShowTitleEnabled(false);
        }

        //es necesario recibir los parametros enviados de un Intent en Bundle
        Bundle extras = getIntent().getExtras();
        final int id = extras.getInt("id");
        String name = extras.getString("name");
        channel =  extras.getString("channel");
        final int photo = extras.getInt("photo");
        int state = extras.getInt("state");
        String about = extras.getString("about");

        editText.setText(name);
        etabout.setText(about);


        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.channel_Arduino, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner.setAdapter(adapter);
        spiner.setOnItemSelectedListener(this);


        list_image adap=new list_image(this,imagenes,imgid);
        ListView lista = (ListView) findViewById(R.id.list_item);
        lista.setAdapter(adap);
        lista.setItemChecked(photo,true);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Slecteditem = position;
                //Slecteditem= Integer.parseInt(String.valueOf(position));
                Selecteditem= imagenes[+position];
                Toast.makeText(getApplicationContext(), Selecteditem, Toast.LENGTH_SHORT).show();
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder messageConnection = new AlertDialog.Builder(Details_devices.this);
                setTitle("Atencion!");
                messageConnection.setMessage("Estas seguro de modificar la informacion del Dispositivo?")
                        .setCancelable(false)
                        .setPositiveButton("si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                /*DataBase admin = new DataBase(Devices_controller.this, ConstanteDataBase.TABLE_DEVICES, null, ConstanteDataBase.DATABASE_VERSION);
                                SQLiteDatabase db =admin.getWritableDatabase();*/
                                String nombredevice = editText.getText().toString();
                                String channeldevice = spiner.getItemAtPosition(spiner.getSelectedItemPosition()).toString();
                                int image = Slecteditem;
                                String about = etabout.getText().toString();
                                constructorDevices = new ConstructorDevices();
                                //context = Devices_controller.this;
                                constructorDevices.updateDevices(id,nombredevice, channeldevice, image, about);
                                /*ContentValues registro = new ContentValues();
                                registro.put(ConstanteDataBase.TABLE_DEVICES_NAME, nombredevice);
                                registro.put(ConstanteDataBase.TABLE_DEVICES_CHANNEL_ID, channeldevice);
                                db.insert(ConstanteDataBase.TABLE_DEVICES, null, registro);
                                db.close();*/
                                //startActivity(intent);
                                editText.setText("");
                                etabout.setText("");
                                //startActivity(intent);
                                Toast.makeText(Details_devices.this, "Se Modifico el Dispositivo",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Details_devices.this, Splash_screen.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cancelar();
                            }
                        });
                AlertDialog titulo = messageConnection.create();
                titulo.setTitle("Alerta!");
                titulo.show();

            }
        });



        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder messageConnection = new AlertDialog.Builder(Details_devices.this);
                setTitle("Atencion!");
                messageConnection.setMessage("Estas seguro de eliminar el Dispositivo")
                        .setCancelable(false)
                        .setPositiveButton("si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                /*DataBase admin = new DataBase(Devices_controller.this, ConstanteDataBase.TABLE_DEVICES, null, ConstanteDataBase.DATABASE_VERSION);
                                SQLiteDatabase db =admin.getWritableDatabase();
                                String nombredevice = editText.getText().toString();
                                String channeldevice = spiner.getItemAtPosition(spiner.getSelectedItemPosition()).toString();
                                int image = Slecteditem;
                                String about = etabout.getText().toString();*/
                                constructorDevices = new ConstructorDevices();
                                //context = Devices_controller.this;
                                constructorDevices.deleteDevices(id);
                                /*ContentValues registro = new ContentValues();
                                registro.put(ConstanteDataBase.TABLE_DEVICES_NAME, nombredevice);
                                registro.put(ConstanteDataBase.TABLE_DEVICES_CHANNEL_ID, channeldevice);
                                db.insert(ConstanteDataBase.TABLE_DEVICES, null, registro);
                                db.close();*/
                                //startActivity(intent);
                                editText.setText("");
                                etabout.setText("");
                                //startActivity(intent);
                                Toast.makeText(Details_devices.this, "Has eliminado el dispositivo",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Details_devices.this, Splash_screen.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cancelar();
                            }
                        });
                AlertDialog titulo = messageConnection.create();
                titulo.setTitle("Alerta!");
                titulo.show();
            }
        });

        list_image ada=new list_image(this,imagenes,imgid);
        lista = (ListView) findViewById(R.id.list_item);
        lista.setAdapter(ada);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Slecteditem = position;
                //Slecteditem= Integer.parseInt(String.valueOf(position));
                Selecteditem= imagenes[+position];
                Toast.makeText(getApplicationContext(), Selecteditem, Toast.LENGTH_SHORT).show();
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

    public void cancelar() {
        finish();
    }
}
