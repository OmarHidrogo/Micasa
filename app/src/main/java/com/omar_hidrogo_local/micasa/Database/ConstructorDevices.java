package com.omar_hidrogo_local.micasa.Database;

import android.app.Activity;
import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.omar_hidrogo_local.micasa.MainActivity;
import com.omar_hidrogo_local.micasa.pojo.ConsumptionDevice;
import com.omar_hidrogo_local.micasa.pojo.Devices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by tmhidrooma on 19/10/2017.
 */

public class ConstructorDevices extends Application {

    private DataBase admin;
    private Activity activity;

    private double vt;
    private long v1;
    private long v2;


    private String mounth, day, hour, minute, second;


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


            devices.add(deviceActual);
        }
        db.close();
        return  devices;
    }

    public ArrayList<ConsumptionDevice> obtenerconsumoDevice(double ett1, double ett2, int id) {
        ArrayList<ConsumptionDevice> consumptionDevices = new ArrayList<>();
        DataBase db = new DataBase(MainActivity.getContext(), ConstanteDataBase.TABLE_HISTORY, null, ConstanteDataBase.DATABASE_VERSION);

        //SE DECLARA QUERY
        String query = " SELECT * FROM "+ ConstanteDataBase.TABLE_HISTORY + " WHERE "/*+ ConstanteDataBase.TABLE_HISTORY_DEVICE_ID+ " = "+ id;*/
               /*+" AND "*/+ConstanteDataBase.TABLE_HISTORY_TIME+" >="+ett1+" AND "+ConstanteDataBase.TABLE_HISTORY_TIME+" <="+ett2+" AND "+ConstanteDataBase.TABLE_HISTORY_DEVICE_ID+ " = "+ id;
        SQLiteDatabase hdb = db.getWritableDatabase();
        Cursor registros = hdb.rawQuery(query, null);

        while (registros.moveToNext()) {
            ConsumptionDevice consumptionDeviceactual = new ConsumptionDevice();
            //INDICE DE LA COLUMNA  DE LA TABLA
            consumptionDeviceactual.setIdhistorial(registros.getInt(0));
            consumptionDeviceactual.setIddevice(registros.getInt(1));
            consumptionDeviceactual.setStatus(registros.getInt(2));
            consumptionDeviceactual.setTime(registros.getLong(3));
            consumptionDeviceactual.setMillis(registros.getLong(4));

            consumptionDevices.add(consumptionDeviceactual);
        }
        db.close();

        return consumptionDevices;
    }

    public ArrayList<ConsumptionDevice> obtenerconsumoporDevice(int id) {
        ArrayList<ConsumptionDevice> consumptionDevices = new ArrayList<>();
        DataBase db = new DataBase(MainActivity.getContext(), ConstanteDataBase.TABLE_HISTORY, null, ConstanteDataBase.DATABASE_VERSION);

        //SE DECLARA QUERY
        String query = " SELECT * FROM "+ ConstanteDataBase.TABLE_HISTORY + " WHERE "+ ConstanteDataBase.TABLE_HISTORY_DEVICE_ID+ " = "+ id;
        SQLiteDatabase hdb = db.getWritableDatabase();
        Cursor registros = hdb.rawQuery(query, null);

        while (registros.moveToNext()) {
            ConsumptionDevice consumptionDeviceactual = new ConsumptionDevice();
            //INDICE DE LA COLUMNA  DE LA TABLA
            consumptionDeviceactual.setIdhistorial(registros.getInt(0));
            consumptionDeviceactual.setIddevice(registros.getInt(1));
            consumptionDeviceactual.setStatus(registros.getInt(2));
            consumptionDeviceactual.setTime(registros.getLong(3));
            consumptionDeviceactual.setMillis(registros.getLong(4));

            consumptionDevices.add(consumptionDeviceactual);
        }
        db.close();


        return consumptionDevices;
    }

    public void statusDevice(Devices device, int status, int image) {
        admin = new DataBase(MainActivity.getContext(), ConstanteDataBase.TABLE_DEVICES, null, ConstanteDataBase.DATABASE_VERSION);
        SQLiteDatabase db =admin.getWritableDatabase();
        ContentValues registro = new ContentValues();

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


        StringBuilder reg = new StringBuilder();

        if(formattedDatemonth<10){
            mounth = "0"+formattedDatemonth;
        }else{
            mounth = String.valueOf(formattedDatemonth);}
        if(formattedDateday<10){
            day = "0"+formattedDateday;
        }else{
            day = String.valueOf(formattedDateday);}
        if(formattedDatehour<10) {
            hour = "0" + formattedDatehour;
        }else{
            hour = String.valueOf(formattedDatehour);}
        if(formattedDateminute<10){
            minute = "0"+formattedDateminute;
        }else{
            minute = String.valueOf(formattedDateminute);}
        if(formattedDatesecond<10){
            second = "0"+formattedDatesecond;
        }else{
            second = String.valueOf(formattedDatesecond);}

        reg.append(formattedDateyear);
        reg.append(mounth);
        reg.append(day);
        reg.append(hour);
        reg.append(minute);
        reg.append(second);

        long regis = Long.parseLong((reg.toString()));


        long millis = datetime.getTime();
        registroConsumo.put(ConstanteDataBase.TABLE_HISTORY_DEVICE_ID, iddevice);
        registroConsumo.put(ConstanteDataBase.TABLE_HISTORY_DEVICE_STATE, state);
        registroConsumo.put(ConstanteDataBase.TABLE_HISTORY_TIME, regis);
        registroConsumo.put(ConstanteDataBase.TABLE_HISTORY_TIME_MILIS, millis);

        db.insert(ConstanteDataBase.TABLE_HISTORY, null, registroConsumo);
        db.close();
    }
}
