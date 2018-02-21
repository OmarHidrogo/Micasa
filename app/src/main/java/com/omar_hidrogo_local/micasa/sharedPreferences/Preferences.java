package com.omar_hidrogo_local.micasa.sharedPreferences;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by tmhidrooma on 12/02/2018.
 */

public class Preferences {

    //inicializar un preferencia compartida.

    public static String getmiprefInternet(Context context) {

        SharedPreferences miprefInternet = context.getSharedPreferences("cInternet", Context.MODE_PRIVATE);     // se inicializa preferencia donde cuardara la conexion  de la casa a controlar por Internet
        String cInternet = miprefInternet.getString("cInternet", "");
        return cInternet;
    }

    public static SharedPreferences getSharedPreferencesInternet(Context context) {

        SharedPreferences miprefInternet = context.getSharedPreferences("cInternet", Context.MODE_PRIVATE);     // se inicializa preferencia donde cuardara la conexion  de la casa a controlar por Internet
        return miprefInternet;
    }

    public static String getmiprefBluetooth(Context context) {

        SharedPreferences miprefBluetooth = context.getSharedPreferences("cBluetooth", Context.MODE_PRIVATE);   // se inicializa preferencia donde cuardara la conexion  de la casa a controlar por Bluetooth
        String cBluetooth = miprefBluetooth.getString("cBluetooth", "");
        return cBluetooth;
    }

    public static SharedPreferences getSharedPreferencesmiprefBluetooth(Context context) {

        SharedPreferences miprefBluetooth = context.getSharedPreferences("cBluetooth", Context.MODE_PRIVATE);   // se inicializa preferencia donde cuardara la conexion  de la casa a controlar por Bluetooth
        return miprefBluetooth;
    }

    public static String getmiprefIdb(Context context) {

        SharedPreferences miprefIdb = context.getSharedPreferences("db", Context.MODE_PRIVATE);     // se inicializa preferencia donde cuardara la conexion  de la casa a controlar por Internet
        String db = miprefIdb.getString("db", ""); // se inicializa vacio
        return db;
    }

    public static SharedPreferences getSharedPreferencesmiprefIdb(Context context) {

        SharedPreferences miprefIdb = context.getSharedPreferences("db", Context.MODE_PRIVATE);     // se inicializa preferencia donde cuardara la conexion  de la casa a controlar por Internet
        return miprefIdb;
    }

    public static int getmiprefConexion(Context context) {

        SharedPreferences miprefConexion = context.getSharedPreferences("mconex", Context.MODE_PRIVATE);
        int mconex = miprefConexion.getInt("mconex", 0);
        return mconex;
    }

    public static SharedPreferences getSharedPreferencesmiprefConexion(Context context) {

        SharedPreferences miprefConexion = context.getSharedPreferences("mconex", Context.MODE_PRIVATE);
        return miprefConexion;
    }

    public static String getmiprefvalorenergy(Context context) {

        SharedPreferences miprefvalorenergy = context.getSharedPreferences("venergy", Context.MODE_PRIVATE);
        String venergy = miprefvalorenergy.getString("venergy", "0");
        return venergy;
    }

    public static SharedPreferences getSharedPreferencesmiprefvalorenergy(Context context) {

        SharedPreferences miprefvenergy = context.getSharedPreferences("venergy", Context.MODE_PRIVATE);
        return miprefvenergy;
    }

    public static String getmiprefvalorCO(Context context) {

        SharedPreferences miprefvalorCO = context.getSharedPreferences("vco", Context.MODE_PRIVATE);
        String vco = miprefvalorCO.getString("vco", "0");
        return vco;
    }

    public static SharedPreferences getSharedPreferencesvalorCO(Context context) {

        SharedPreferences miprefSharedPreferencesvalorCO = context.getSharedPreferences("vco", Context.MODE_PRIVATE);
        return miprefSharedPreferencesvalorCO;
    }


}