package com.omar_hidrogo_local.micasa.pojo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tmhidrooma on 16/10/2017.
 */

public class DataBase extends SQLiteOpenHelper {

    //se declara variable tipo Context
    private Context context;

    //se crea el constructor
    public BaseDatos(Context context) {

        //SE CREA BASE DE DATOS
        super(context, ConstanteDataBase.DATABASE_NAME, null, ConstanteDataBase.DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        //SE CREA QUERY PARA CREAR LA TABLA MASCOTAS
        String queryCrearTablaMascotas = "CREATE TABLE "+ConstanteDataBase.TABLE_DEVICES + "("+
                ConstanteDataBase.TABLE_DEVICES_ID        + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ConstanteDataBase.TABLE_DEVICES_NAME    + " TEXT, "+
                ConstanteDataBase.TABLE_DEVICES_CHANNEL_ID      + " INTEGER"+
                ")";

        db.execSQL(queryCrearTablaMascotas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST "+ ConstanteDataBase.TABLE_DEVICES);
        onCreate(db);

    }
}
