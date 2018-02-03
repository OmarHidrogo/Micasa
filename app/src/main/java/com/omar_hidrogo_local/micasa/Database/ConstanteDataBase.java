package com.omar_hidrogo_local.micasa.Database;

/**
 * Created by tmhidrooma on 16/10/2017.
 */

public class ConstanteDataBase {

    //Constantes que se utilizaran para el uso de querys en la base de datos

    public static final String DATABASE_NAME = "devices";
    public static final int DATABASE_VERSION = 1;


    public static final String TABLE_DEVICES = "devices";
    public static final String TABLE_DEVICES_ID = "id";
    public static final String TABLE_DEVICES_NAME = "nombre";
    public static final String TABLE_DEVICES_CHANNEL_ID = "channel";
    public static final String TABLE_DEVICES_PHOTO ="photo";
    public static final String TABLE_DEVICES_STATE ="state";
    public static final String TABLE_DEVICES_ABOUT = "about";
    public static final String TABLE_DEVICES_WATTS = "watts";


    public static final String TABLE_ENERGY = "energy";
    public static final String TABLE_ENERGY_ID = "idenergy";
    public static final String TABLE_ENERGY_DEVICE_ID = "id";
    public static final String TABLE_ENERGY_WATTS = "watts";

    public static final String TABLE_HISTORY = "history";
    public static final String TABLE_HISTORY_ID ="idhistory";
    public static final String TABLE_HISTORY_DEVICE_ID = "id";
    public static final String TABLE_HISTORY_DEVICE_STATE = "state";
    public static final String TABLE_HISTORY_TIME = "time";
    public static final String TABLE_HISTORY_TIME_MILIS ="millis";
}
