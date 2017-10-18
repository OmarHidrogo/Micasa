package com.omar_hidrogo_local.micasa.adaptador;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omar_hidrogo_local.micasa.pojo.Devices;

import java.util.ArrayList;

/**
 * Created by tmhidrooma on 18/10/2017.
 */

public class DevicesAdapter extends RecyclerView.Adapter<DevicesAdapter.DevicesViewHolder> {

    ArrayList<Devices> devices;
    Activity activity;

    public DevicesAdapter(ArrayList<Devices> devices, Activity activity){
        this.devices =devices;
        this.activity=activity;
    }

    //inflar el layout y pasa a view hlder para que el obtenga los datos
    @Override
    public DevicesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Ligar el Layout cardview_mascotas al Adaptador
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);

        //Se envia el View al constructor  MascotasViewHolder
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DevicesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DevicesViewHolder extends RecyclerView.ViewHolder {
    }
}
