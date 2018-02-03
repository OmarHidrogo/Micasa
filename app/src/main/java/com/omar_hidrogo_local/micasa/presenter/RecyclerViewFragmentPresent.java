package com.omar_hidrogo_local.micasa.presenter;

import android.content.Context;

import com.omar_hidrogo_local.micasa.Database.ConstructorDevices;
import com.omar_hidrogo_local.micasa.fragment.IRecyclerViewFragmentView;
import com.omar_hidrogo_local.micasa.pojo.Devices;

import java.util.ArrayList;

/**
 * Created by tmhidrooma on 18/10/2017.
 */

public class RecyclerViewFragmentPresent implements IRecyclerViewFragmentPresent {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorDevices constructorDevices;
    private ArrayList<Devices> devices;

    public RecyclerViewFragmentPresent(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context){
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerDevicesDataBase();
    }

    @Override
    public void obtenerDevicesDataBase() {
        constructorDevices = new ConstructorDevices();
        devices = constructorDevices.obtenerDatos();
        mostrarDevicesRV();
    }

    @Override
    public void mostrarDevicesRV() {

        //se inicializa el adaptador  con un lista de Dispositivos
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(devices));
        iRecyclerViewFragmentView.generarLinerLayoutVertical();

    }
}
