package com.omar_hidrogo_local.micasa.pojo;

/**
 * Created by tmhidrooma on 17/10/2017.
 */

public class Devices {
    //Se declara las variables del dispositivo
    private int id;
    private String nombre;
    private String channel;
    private int photo;
    private int state;
    private String about;


    //Se declara constructor de las variables
    public Devices(String nombre, String channel, int photo, int state, String about) {
        this.nombre = nombre;
        this.channel = channel;
        this.photo = photo;
        this.state = state;
        this.about = about;
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

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
