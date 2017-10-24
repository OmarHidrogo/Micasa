package com.omar_hidrogo_local.micasa.adaptador;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.omar_hidrogo_local.micasa.pojo.Devices;
import com.omar_hidrogo_local.micasa.R;

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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_device, parent, false);

        //Se envia el View al constructor  MascotasViewHolder
        return new DevicesViewHolder(v);
    }

    //Se Asocia cada elemento de la lista con cada vista
    @Override
    public void onBindViewHolder(DevicesViewHolder devicesViewHolder, int position) {

        final  Devices device = devices.get(position);

        //devicesViewHolder.imgFoto.setImageResource(device.getFoto());
        devicesViewHolder.tvnombredevice.setText(device.getNombre());

        devicesViewHolder.btnencender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(activity," Has encendido el "+device.getNombre(),Toast.LENGTH_SHORT).show();
            }
        });

        devicesViewHolder.btnapagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity," Has apagado el "+device.getNombre(),Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    public class DevicesViewHolder extends RecyclerView.ViewHolder {

        //Declarar las variables de los views en este caso las imagenes de las mascotas y el nombre
        private ImageView imgFoto;
        private TextView tvnombredevice;
        private Button btnencender;
        private Button btnapagar;

        //Constructor heredado de RecyclerView.ViewHolder
        public DevicesViewHolder(View itemView) {
            super(itemView);

            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvnombredevice = (TextView) itemView.findViewById(R.id.tvNombreDevice);
            btnencender = (Button) itemView.findViewById(R.id.btnencender);
            btnapagar = (Button) itemView.findViewById(R.id.btnapagar);
        }
    }
}
