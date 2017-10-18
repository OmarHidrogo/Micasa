package com.omar_hidrogo_local.micasa.pojo;

/**
 * Created by tmhidrooma on 17/10/2017.
 */

public class Devices {
    //Se declara las variables del dispositivo
    private int id;
    private String nombre;
    private String channel;


    //Se declara constructor de las variables
    public Devices(String nombre, String channel) {
        this.nombre = nombre;
        this.channel = channel;
    }

    //Constructor vacio
    public Devices(){

    }

    //Se generan los getter y setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
