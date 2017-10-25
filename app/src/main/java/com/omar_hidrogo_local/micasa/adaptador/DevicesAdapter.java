package com.omar_hidrogo_local.micasa.adaptador;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.omar_hidrogo_local.micasa.Database.ConstructorDevices;
import com.omar_hidrogo_local.micasa.Details_devices;
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
    public void onBindViewHolder(final DevicesViewHolder devicesViewHolder, int position) {

        final  Devices device = devices.get(position);

        //devicesViewHolder.imgFoto.setImageResource(device.getFoto());
        devicesViewHolder.tvnombredevice.setText(device.getNombre());
        devicesViewHolder.tvchannel.setText("Canal # "+device.getChannel());
        final int image = device.getPhoto();
        final int status = device.getState();
        switch (image){
            case 0:
                devicesViewHolder.imgFoto.setImageResource(R.drawable.focoapagado);
                if(status==0){
                    devicesViewHolder.imgFoto.setImageResource(R.drawable.focoapagado);
                    }else
                        devicesViewHolder.imgFoto.setImageResource(R.drawable.focoencendido);
                break;
            case 1:
                devicesViewHolder.imgFoto.setImageResource(R.drawable.aireapagado);
                if(status==0){
                    devicesViewHolder.imgFoto.setImageResource(R.drawable.aireapagado);
                    }else
                        devicesViewHolder.imgFoto.setImageResource(R.drawable.aireencendido);
                break;
            default:
        }

        devicesViewHolder.btnencender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(activity," Has encendido el "+device.getNombre(),Toast.LENGTH_SHORT).show();
                ConstructorDevices constructorDevices = new ConstructorDevices();
                int status =1;
                constructorDevices.statusDevice(device, status, image);
                if(image==0){
                    devicesViewHolder.imgFoto.setImageResource(R.drawable.focoencendido);
                }else
                    devicesViewHolder.imgFoto.setImageResource(R.drawable.aireencendido);

            }
        });

        devicesViewHolder.btnapagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity," Has apagado el "+device.getNombre(),Toast.LENGTH_SHORT).show();
                ConstructorDevices constructorDevices = new ConstructorDevices();
                int status =0;
                constructorDevices.statusDevice(device, status, image);
                if(image==0){
                    devicesViewHolder.imgFoto.setImageResource(R.drawable.focoapagado);
                }else
                    devicesViewHolder.imgFoto.setImageResource(R.drawable.aireapagado);

            }
        });

        devicesViewHolder.btnInf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Details_devices.class);
                intent.putExtra("id", device.getId());
                intent.putExtra("name", device.getNombre());
                intent.putExtra("channel", device.getChannel());
                intent.putExtra("photo", device.getPhoto());
                intent.putExtra("state", device.getState());
                intent.putExtra("about", device.getAbout());
                activity.startActivity(intent);
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
        private Button btnInf;
        private TextView tvchannel;

        //Constructor heredado de RecyclerView.ViewHolder
        public DevicesViewHolder(View itemView) {
            super(itemView);

            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvnombredevice = (TextView) itemView.findViewById(R.id.tvNombreDevice);
            btnencender = (Button) itemView.findViewById(R.id.btnencender);
            btnapagar = (Button) itemView.findViewById(R.id.btnapagar);
            btnInf = (Button) itemView.findViewById(R.id.btnInf);
            tvchannel = (TextView) itemView.findViewById(R.id.tvchannel);
        }
    }
}
