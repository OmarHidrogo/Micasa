package com.omar_hidrogo_local.micasa.fragment;

import java.util.ArrayList;

/**
 * Created by tmhidrooma on 18/10/2017.
 */

public interface IRecyclerViewFragmentView {

    //metodo para generar el liner layout  de forma vertical
    public void generarLinerLayoutVertical();

    //metodo para devolver mascotas Adaptador  genera un  adaptador
    public  crearAdaptador(ArrayList<Mascota> mascotas);

    //inicializa el adaptador con el recyclerview
    public  void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
