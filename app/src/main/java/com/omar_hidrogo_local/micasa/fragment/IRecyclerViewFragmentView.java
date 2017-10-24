package com.omar_hidrogo_local.micasa.fragment;

import com.omar_hidrogo_local.micasa.adaptador.DevicesAdapter;
import com.omar_hidrogo_local.micasa.pojo.Devices;

import java.util.ArrayList;

/**
 * Created by tmhidrooma on 18/10/2017.
 */

public interface IRecyclerViewFragmentView {

    //metodo para generar el liner layout  de forma vertical
    public void generarLinerLayoutVertical();

    //metodo para devolver mascotas Adaptador  genera un  adaptador
    public DevicesAdapter crearAdaptador(ArrayList<Devices> devices);

    //inicializa el adaptador con el recyclerview
    public  void inicializarAdaptadorRV(DevicesAdapter adaptador);
}
