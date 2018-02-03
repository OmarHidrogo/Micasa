package com.omar_hidrogo_local.micasa.pojo;

/**
 * Created by tmhidrooma on 02/02/2018.
 */

public class ConsumoDevice {

    //DECLARA VARIABLES
    private int idhistorial;
    private int iddevice;
    private int status;
    private int time;
    private long millis;

    public ConsumoDevice(int iddevice, int status, int time, long millis) {
        this.iddevice = iddevice;
        this.status = status;
        this.time = time;
        this.millis = millis;
    }

    public ConsumoDevice() {

    }

    public int getIdhistorial() {
        return idhistorial;
    }

    public void setIdhistorial(int idhistorial) {
        this.idhistorial = idhistorial;
    }

    public int getIddevice() {
        return iddevice;
    }

    public void setIddevice(int iddevice) {
        this.iddevice = iddevice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public long getMillis() {
        return millis;
    }

    public void setMillis(long millis) {
        this.millis = millis;
    }
}
