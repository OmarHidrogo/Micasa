package com.omar_hidrogo_local.micasa.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omar_hidrogo_local.micasa.MainActivity;
import com.omar_hidrogo_local.micasa.adaptador.DevicesAdapter;
import com.omar_hidrogo_local.micasa.pojo.Devices;
import com.omar_hidrogo_local.micasa.presenter.IRecyclerViewFragmentPresent;
import com.omar_hidrogo_local.micasa.R;
import com.omar_hidrogo_local.micasa.presenter.RecyclerViewFragmentPresent;

import java.util.ArrayList;

/**
 * Created by tmhidrooma on 18/10/2017.
 */

public class Fragment_RecyclerView extends Fragment implements IRecyclerViewFragmentView{

    //Lista de Devices
    private ArrayList<Devices> devices;

    //variable de lista Devices
    private RecyclerView listaDevices;

    //Presentador
    private IRecyclerViewFragmentPresent present;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        //Asociar el fragment al recycler view
        View v = inflater.inflate(R.layout.activity_fragment_recycler_view, container, false);

        //enlazar la variable de tipo RecyclerView al id del layout
        listaDevices = (RecyclerView) v.findViewById(R.id.rvDevices);
        present = new RecyclerViewFragmentPresent(this, getContext());


        return  v;
    }


    @Override
    public void generarLinerLayoutVertical() {

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaDevices.setLayoutManager(llm);

    }

    @Override
    public DevicesAdapter crearAdaptador(ArrayList<Devices> devices) {

        DevicesAdapter adaptador = new DevicesAdapter(devices, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(DevicesAdapter adaptador) {
        listaDevices.setAdapter(adaptador);

    }
}
