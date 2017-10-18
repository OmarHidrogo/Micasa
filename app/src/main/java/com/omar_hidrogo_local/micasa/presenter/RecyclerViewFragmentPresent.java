package com.omar_hidrogo_local.micasa.presenter;

import android.content.Context;

import com.omar_hidrogo_local.micasa.Devices_controller;
import com.omar_hidrogo_local.micasa.fragment.IRecyclerViewFragmentView;
import com.omar_hidrogo_local.micasa.pojo.Devices;

import java.util.ArrayList;

/**
 * Created by tmhidrooma on 18/10/2017.
 */

public class RecyclerViewFragmentPresent implements IRecyclerViewFragmentPresent {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private Devices_controller devices_controller;
    private ArrayList<Devices> devices;

    public RecyclerViewFragmentPresent(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context){
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerDevicesDataBase();
    }

    @Override
    public void obtenerDevicesDataBase() {
        devices_controller = new Devices_controller(context);
        devices = devices_controller.obtenerDatos();
        mostrarDevicesRV();
    }

    @Override
    public void mostrarDevicesRV() {

        //se inicializa el adaptador  con un lista de Dispositivos
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(mascotas));
        iRecyclerViewFragmentView.generarLinerLayoutVertical();

    }
}
