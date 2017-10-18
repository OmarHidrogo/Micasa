package com.omar_hidrogo_local.micasa.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.omar_hidrogo_local.micasa.pojo.Devices;

import java.util.ArrayList;

/**
 * Created by tmhidrooma on 16/10/2017.
 */


public class DataBase extends SQLiteOpenHelper {
    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //SE CREA QUERY PARA CREAR LA TABLA MASCOTAS
        String queryCrearTablaDevices = "CREATE TABLE "+ConstanteDataBase.TABLE_DEVICES + "("+
                ConstanteDataBase.TABLE_DEVICES_ID        + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ConstanteDataBase.TABLE_DEVICES_NAME    + " TEXT, "+
                ConstanteDataBase.TABLE_DEVICES_CHANNEL_ID      + " INTEGER"+
                ")";

        db.execSQL(queryCrearTablaDevices);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Devices> obtenerTodasLasDevices(){
        ArrayList<Devices> devices = new ArrayList<>();

        //SE DECLARA LA QUERY DE SELECCIONAR TODA LA BASE DE DATOS
        String query = " SELECT * FROM "+ ConstanteDataBase.TABLE_DEVICES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

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



   /*//se declara variable tipo Context
    private Context context;

    //se crea el constructor
    public DataBase(Context context) {

        //SE CREA BASE DE DATOS
        super(context, ConstanteDataBase.DATABASE_NAME, null, ConstanteDataBase.DATABASE_VERSION);
        this.context = context;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        //SE CREA QUERY PARA CREAR LA TABLA MASCOTAS
        String queryCrearTablaDevices = "CREATE TABLE "+ConstanteDataBase.TABLE_DEVICES + "("+
                ConstanteDataBase.TABLE_DEVICES_ID        + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ConstanteDataBase.TABLE_DEVICES_NAME    + " TEXT, "+
                ConstanteDataBase.TABLE_DEVICES_CHANNEL_ID      + " INTEGER"+
                ")";

        db.execSQL(queryCrearTablaDevices);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST "+ ConstanteDataBase.TABLE_DEVICES);
        onCreate(db);

    }


    public void insertardevices(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor numT = db.rawQuery("SELECT * FROM "+ConstanteDataBase.TABLE_DEVICES, null);
        //int n = numT.getCount();
        //if(n<= 15){
            db.insert(ConstanteDataBase.TABLE_DEVICES, null, contentValues);
            db.close();


    }*/

}
