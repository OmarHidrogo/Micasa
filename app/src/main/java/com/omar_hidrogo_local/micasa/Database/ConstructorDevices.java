package com.omar_hidrogo_local.micasa.Database;

import android.app.Activity;
import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.omar_hidrogo_local.micasa.Details_devices;
import com.omar_hidrogo_local.micasa.Devices_controller;
import com.omar_hidrogo_local.micasa.MainActivity;
import com.omar_hidrogo_local.micasa.R;
import com.omar_hidrogo_local.micasa.pojo.ConsumoDevice;
import com.omar_hidrogo_local.micasa.pojo.Devices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

import static com.omar_hidrogo_local.micasa.Device_consumption.etwattstotal;
import static com.omar_hidrogo_local.micasa.Device_consumption.etco2total;
import static com.omar_hidrogo_local.micasa.Device_consumption.etpagototal;
import static com.omar_hidrogo_local.micasa.Device_consumption.wtts;

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

    private double vt;
    private long v1;
    private long v2;

    //Calculo de consumo Electrico
    private long milisegundos=1000;
    private long minutos=milisegundos*60;
    private double horas=minutos*60;
    private double dias=horas*24;
    private double co2=0.000454;
    private double pago=0.62;



    /*public ConstructorDevices(Context context) {
        this.context = context;
    }*/

    public void insertarDevices (String nombredevice, String channeldevice, int image, int watts, String about){
        admin = new DataBase(MainActivity.getContext(), ConstanteDataBase.TABLE_DEVICES, null, ConstanteDataBase.DATABASE_VERSION);
        SQLiteDatabase db =admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put(ConstanteDataBase.TABLE_DEVICES_NAME, nombredevice);
        registro.put(ConstanteDataBase.TABLE_DEVICES_CHANNEL_ID, channeldevice);
        registro.put(ConstanteDataBase.TABLE_DEVICES_PHOTO, image);
        registro.put(ConstanteDataBase.TABLE_DEVICES_STATE, 0);
        registro.put(ConstanteDataBase.TABLE_DEVICES_WATTS, watts);
        registro.put(ConstanteDataBase.TABLE_DEVICES_ABOUT, about);
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
            deviceActual.setPhoto(registros.getInt(3));
            deviceActual.setState(registros.getInt(4));
            deviceActual.setWatts(registros.getInt(5));
            deviceActual.setAbout(registros.getString(6));

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

    public ArrayList<ConsumoDevice> obtenerconsumoDevice(double ett1, double ett2, int id) {
        ArrayList<ConsumoDevice> consumoDevices = new ArrayList<>();
        DataBase db = new DataBase(MainActivity.getContext(), ConstanteDataBase.TABLE_HISTORY, null, ConstanteDataBase.DATABASE_VERSION);

        //SE DECLARA QUERY
        String query = " SELECT * FROM "+ ConstanteDataBase.TABLE_HISTORY + " WHERE "+ ConstanteDataBase.TABLE_HISTORY_DEVICE_ID+ " = "+ id;
               //+" AND "*/+ConstanteDataBase.TABLE_HISTORY_TIME+" IN ( "+ett1+", "+ett2+" ) AND "+ConstanteDataBase.TABLE_HISTORY_DEVICE_ID+ " = "+ id;
        SQLiteDatabase hdb = db.getWritableDatabase();
        Cursor registros = hdb.rawQuery(query, null);

        while (registros.moveToNext()) {
            ConsumoDevice consumoDeviceactual = new ConsumoDevice();
            //INDICE DE LA COLUMNA  DE LA TABLA
            consumoDeviceactual.setIdhistorial(registros.getInt(0));
            consumoDeviceactual.setIddevice(registros.getInt(1));
            consumoDeviceactual.setStatus(registros.getInt(2));
            consumoDeviceactual.setTime(registros.getDouble(3));
            consumoDeviceactual.setMillis(registros.getLong(4));

            consumoDevices.add(consumoDeviceactual);
        }
        db.close();


        /*for (int i = 0; i <= consumoDevices.size(); i++){

            final ConsumoDevice consumoDevice = consumoDevices.get(i);
            if(consumoDevice.getStatus()!=0){
                v1=consumoDevice.getMillis();
                //long v2=consumoDevice.getMillis();
            }else{
                v2=consumoDevice.getMillis();
                    vt = vt +(v2 - v1);
            }

        }
        vt = vt/horas;
        vt=vt*wtts;
        co2=co2*vt;
        pago=pago*vt;
        etwattstotal.setText(String.valueOf(vt));
        etco2total.setText(String.valueOf(co2)+" Kilogramos");
        etpagototal.setText("$ "+String.valueOf(pago));*/
        return consumoDevices;
    }

    public void statusDevice(Devices device, int status, int image) {
        admin = new DataBase(MainActivity.getContext(), ConstanteDataBase.TABLE_DEVICES, null, ConstanteDataBase.DATABASE_VERSION);
        SQLiteDatabase db =admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        /*String query = "SELECT "+ConstanteDataBase.TABLE_DEVICES_PHOTO +" FROM " +ConstanteDataBase.TABLE_DEVICES+" WHERE "+
                ConstanteDataBase.TABLE_DEVICES_ID +" = "+device;*/
        registro.put(ConstanteDataBase.TABLE_DEVICES_STATE, status);
        registro.put(ConstanteDataBase.TABLE_DEVICES_PHOTO, image);
        db.update(ConstanteDataBase.TABLE_DEVICES,registro,ConstanteDataBase.TABLE_DEVICES_ID+ " = "+device.getId(),null);
        db.close();

    }


    public void updateDevices (int id,String nombredevice, String channeldevice, int image, int watts, String about){
        admin = new DataBase(MainActivity.getContext(), ConstanteDataBase.TABLE_DEVICES, null, ConstanteDataBase.DATABASE_VERSION);
        SQLiteDatabase db =admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put(ConstanteDataBase.TABLE_DEVICES_NAME, nombredevice);
        registro.put(ConstanteDataBase.TABLE_DEVICES_CHANNEL_ID, channeldevice);
        registro.put(ConstanteDataBase.TABLE_DEVICES_PHOTO, image);
        registro.put(ConstanteDataBase.TABLE_DEVICES_STATE, 0);
        registro.put(ConstanteDataBase.TABLE_DEVICES_WATTS, watts);
        registro.put(ConstanteDataBase.TABLE_DEVICES_ABOUT, about);
        db.update(ConstanteDataBase.TABLE_DEVICES, registro,ConstanteDataBase.TABLE_DEVICES_ID+" = "+id,null);
        db.close();
    }

    public void deleteDevices (int id){
        admin = new DataBase(MainActivity.getContext(), ConstanteDataBase.TABLE_DEVICES, null, ConstanteDataBase.DATABASE_VERSION);
        SQLiteDatabase db =admin.getWritableDatabase();

        db.delete(ConstanteDataBase.TABLE_DEVICES, ConstanteDataBase.TABLE_DEVICES_ID+" = "+id, null);
        db.close();

    }

    public void insertarHistorial(int iddevice, int state){
        admin = new DataBase(MainActivity.getContext(), ConstanteDataBase.TABLE_HISTORY, null, ConstanteDataBase.DATABASE_VERSION);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues registroConsumo = new ContentValues();
        //FECHA ACTUAL
        Date datetime = new Date();
        //fECHA JULIANO

        //SimpleDateFormat dateFormatyear = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //SE CAMBIA EL MODO DE LA FECHA PARA GUARDARLA EN LA BASE DE DATOS
        SimpleDateFormat dateFormatyear = new SimpleDateFormat("yyyy");
        int formattedDateyear = Integer.parseInt(dateFormatyear.format(datetime));

        SimpleDateFormat dateFormatmonth = new SimpleDateFormat("MM");
        int formattedDatemonth = Integer.parseInt(dateFormatmonth.format(datetime));

        SimpleDateFormat dateFormatday = new SimpleDateFormat("dd");
        int formattedDateday = Integer.parseInt(dateFormatday.format(datetime));

        SimpleDateFormat dateFormathour = new SimpleDateFormat("HH");
        int formattedDatehour = Integer.parseInt(dateFormathour.format(datetime));

        SimpleDateFormat dateFormatminute = new SimpleDateFormat("mm");
        int formattedDateminute = Integer.parseInt(dateFormatminute.format(datetime));

        SimpleDateFormat dateFormatsecond = new SimpleDateFormat("ss");
        int formattedDatesecond = Integer.parseInt(dateFormatsecond.format(datetime));

        double extra = (100.0 * formattedDateyear) + formattedDatemonth - 190002.5;

        double datejuliano = (367.0 * formattedDateyear) -
                (Math.floor(7.0 * (formattedDateyear + Math.floor((formattedDatemonth + 9.0) / 12.0)) / 4.0)) +
                Math.floor((275.0 * formattedDatemonth) / 9.0) +
                formattedDateday + ((formattedDatehour + ((formattedDateminute + (formattedDatesecond / 60.0)) / 60.0)) / 24.0) +
                1721013.5 - ((0.5 * extra) / Math.abs(extra)) + 0.5;




        long millis = datetime.getTime();
        registroConsumo.put(ConstanteDataBase.TABLE_HISTORY_DEVICE_ID, iddevice);
        registroConsumo.put(ConstanteDataBase.TABLE_HISTORY_DEVICE_STATE, state);
        registroConsumo.put(ConstanteDataBase.TABLE_HISTORY_TIME, datejuliano);
        registroConsumo.put(ConstanteDataBase.TABLE_HISTORY_TIME_MILIS, millis);

        db.insert(ConstanteDataBase.TABLE_HISTORY, null, registroConsumo);
        db.close();
    }
}
