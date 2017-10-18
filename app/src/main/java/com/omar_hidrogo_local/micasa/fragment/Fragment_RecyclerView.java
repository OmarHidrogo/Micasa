package com.omar_hidrogo_local.micasa.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.omar_hidrogo_local.micasa.pojo.Devices;

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
}
