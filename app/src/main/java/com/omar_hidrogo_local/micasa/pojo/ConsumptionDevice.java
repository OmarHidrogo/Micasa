package com.omar_hidrogo_local.micasa.pojo;

/**
 * Created by tmhidrooma on 02/02/2018.
 */

public class ConsumptionDevice {

    //DECLARA VARIABLES
    private int idhistorial;
    private int iddevice;
    private int status;
    private long time;
    private long millis;

    public ConsumptionDevice(int iddevice, int status, long time, long millis) {
        this.iddevice = iddevice;
        this.status = status;
        this.time = time;
        this.millis = millis;
    }

    public ConsumptionDevice() {

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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getMillis() {
        return millis;
    }

    public void setMillis(long millis) {
        this.millis = millis;
    }
}
