package com.omar_hidrogo_local.micasa.Database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.omar_hidrogo_local.micasa.Devices_controller;
import com.omar_hidrogo_local.micasa.pojo.Devices;

import java.util.ArrayList;

/**
 * Created by tmhidrooma on 16/10/2017.
 */


public class DataBase extends SQLiteOpenHelper {

    private Context context;
    private DataBase dbadmin;


    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        //SE CREA QUERY PARA CREAR LA TABLA DISPOSITIVOS
        String queryCrearTablaDevices = "CREATE TABLE "+ConstanteDataBase.TABLE_DEVICES + "("+
                ConstanteDataBase.TABLE_DEVICES_ID          + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ConstanteDataBase.TABLE_DEVICES_NAME        + " TEXT, "+
                ConstanteDataBase.TABLE_DEVICES_CHANNEL_ID  + " TEXT, "+
                ConstanteDataBase.TABLE_DEVICES_PHOTO       + " INTEGER, "+
                ConstanteDataBase.TABLE_DEVICES_STATE       + " INTEGER, "+
                ConstanteDataBase.TABLE_DEVICES_WATTS       + " INTEGER, "+
                ConstanteDataBase.TABLE_DEVICES_ABOUT       + " TEXT "+
                ")";

        db.execSQL(queryCrearTablaDevices);


        //SE CREA QUERY PARA CREAR TABLA HISTORIAL DE ESTADO DEL DISPOSITIVO
        String queryCrearTablaHistorial = "CREATE TABLE "+ConstanteDataBase.TABLE_HISTORY+"("+
                ConstanteDataBase.TABLE_HISTORY_ID              + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ConstanteDataBase.TABLE_HISTORY_DEVICE_ID       + " INTEGER, "+
                ConstanteDataBase.TABLE_HISTORY_DEVICE_STATE    + " INTEGER, "+
                ConstanteDataBase.TABLE_HISTORY_TIME            + " INTEGER, "+
                ConstanteDataBase.TABLE_HISTORY_TIME_MILIS      + " INTEGER "+
                ")";

        db.execSQL(queryCrearTablaHistorial);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Devices> obtenerTodasLasDevices(DataBase admin){
        ArrayList<Devices> devices = new ArrayList<>();
        dbadmin = admin;
        //SE DECLARA LA QUERY DE SELECCIONAR TODA LA BASE DE DATOS
        String query = " SELECT * FROM "+ ConstanteDataBase.TABLE_DEVICES;
        SQLiteDatabase db = dbadmin.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Devices deviceActual = new Devices();
            //INDICE DE LA COLUMNA  DE LA TABLA
            deviceActual.setId(registros.getInt(0));
            deviceActual.setNombre(registros.getString(1));
            deviceActual.setChannel(registros.getString(2));

            devices.add(deviceActual);
        }
        db.close();
        return  devices;
    }

}
