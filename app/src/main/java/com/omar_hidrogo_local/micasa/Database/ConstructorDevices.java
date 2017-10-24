package com.omar_hidrogo_local.micasa.Database;

import android.app.Activity;
import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.omar_hidrogo_local.micasa.Devices_controller;
import com.omar_hidrogo_local.micasa.MainActivity;
import com.omar_hidrogo_local.micasa.R;
import com.omar_hidrogo_local.micasa.pojo.Devices;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tmhidrooma on 19/10/2017.
 */

public class ConstructorDevices extends Application {
  /*  private static ConstructorDevices instance;

    public ConstructorDevices(){
        instance = this;
    }

    public static Context getContext(){
     return  instance;
    }*/
    private DataBase admin;
    private Activity activity;




    /*public ConstructorDevices(Context context) {
        this.context = context;
    }*/

    public void insertarDevices (String nombredevice, String channeldevice){
        admin = new DataBase(MainActivity.getContext(), ConstanteDataBase.TABLE_DEVICES, null, ConstanteDataBase.DATABASE_VERSION);
        SQLiteDatabase db =admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put(ConstanteDataBase.TABLE_DEVICES_NAME, nombredevice);
        registro.put(ConstanteDataBase.TABLE_DEVICES_CHANNEL_ID, channeldevice);
        db.insert(ConstanteDataBase.TABLE_DEVICES, null, registro);
        db.close();

    }

    /*public ArrayList<Devices>obtenerDatos(){

        DataBase db = new DataBase(MainActivity.getContext(), ConstanteDataBase.TABLE_DEVICES, null, ConstanteDataBase.DATABASE_VERSION);
        SQLiteDatabase adb =db.getWritableDatabase();
        return  db.obtenerTodasLasDevices();
    }*/

    public ArrayList<Devices> obtenerDatos(){
        ArrayList<Devices> devices = new ArrayList<>();
        DataBase db = new DataBase(MainActivity.getContext(), ConstanteDataBase.TABLE_DEVICES, null, ConstanteDataBase.DATABASE_VERSION);

        //SE DECLARA LA QUERY DE SELECCIONAR TODA LA BASE DE DATOS
        String query = " SELECT * FROM "+ ConstanteDataBase.TABLE_DEVICES;
        SQLiteDatabase adb = db.getWritableDatabase();
        Cursor registros = adb.rawQuery(query, null);

        while (registros.moveToNext()){
            Devices deviceActual = new Devices();
            //INDICE DE LA COLUMNA  DE LA TABLA
            deviceActual.setId(registros.getInt(0));
            deviceActual.setNombre(registros.getString(1));
            deviceActual.setChannel(registros.getString(2));

           /* String queryLikes ="SELECT COUNT("+ConstanteDataBase.TABLE_LIKES_MASCOTAS_NUMERO_LIKES
                    +") as likes "+" FROM "+ ConstanteDataBase.TABLE_LIKES_MASCOTAS +
                    " WHERE "+ ConstanteDataBase.TABLE_LIKES_MASCOTAS_ID_MASCOTAS +
                    " = "+ deviceActual.getId();

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if(registrosLikes.moveToNext()){
                mascotaActual.setLikes(registrosLikes.getInt(0));
            }else{
                mascotaActual.setLikes(0);
            }*/

            devices.add(deviceActual);
        }
        db.close();
        return  devices;
    }
}
