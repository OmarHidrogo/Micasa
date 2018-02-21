package com.omar_hidrogo_local.micasa.adaptador;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.omar_hidrogo_local.micasa.Conunication.Bluettoth;
import com.omar_hidrogo_local.micasa.Conunication.ConnectedThread;
import com.omar_hidrogo_local.micasa.Database.ConstructorDevices;
import com.omar_hidrogo_local.micasa.Details_devices;
import com.omar_hidrogo_local.micasa.Device_consumption;
import com.omar_hidrogo_local.micasa.MainActivity;
import com.omar_hidrogo_local.micasa.Splash_screen;
import com.omar_hidrogo_local.micasa.pojo.Devices;
import com.omar_hidrogo_local.micasa.R;
import com.omar_hidrogo_local.micasa.sharedPreferences.Preferences;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Handler;

/**
 * Created by tmhidrooma on 18/10/2017.
 */

public class DevicesAdapter extends RecyclerView.Adapter<DevicesAdapter.DevicesViewHolder> {

   //Bluetooth
    Handler bluetoothIn;
    final int handlerState = 0;
    private BluetoothAdapter btAdapter = null;
   // private BluetoothSocket btSocket;
    private StringBuilder recDataString = new StringBuilder();

    private ConnectedThread mConnectedThread;
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static String address = null;

    ArrayList<Devices> devices;
    private Activity activity;

    Context context;

    private MainActivity mainActivity;
    private Preferences preferences;
    int conex = preferences.getmiprefConexion(MainActivity.getContext());



    //  private ProgressDialog progress;
    private Splash_screen splash_screen;

//    private int v1 =0;



    //Conexion RED
    public static  final String TAG = "Inicio";
    private String web_service = "http://ip arduino/arduino/13";


    public DevicesAdapter(ArrayList<Devices> devices, Activity activity){
        this.devices =devices;
        this.activity=activity;
    }

    /*public DevicesAdapter(BluetoothSocket btSocket) {
        this.btSocket = btSocket;
    }*/

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
                if(conex == 1){
                    int d = Integer.parseInt(device.getChannel());
                    int v2= d+63;
                    char asciiv = (char) v2;
                    String vascii= String.valueOf((asciiv));
                    Bluettoth bluettoth = new Bluettoth();
                    bluettoth.cbt(vascii);
                    //bluettoth.conexion("1");*/
                    if(ConnectedThread.Success()==false) {
                        Toast.makeText(activity, " Has encendido el " + device.getNombre(), Toast.LENGTH_SHORT).show();
                        ConstructorDevices constructorDevices = new ConstructorDevices();
                        int status = 1;
                        int iddevice = device.getId();
                        constructorDevices.statusDevice(device, status, image);
                        constructorDevices.insertarHistorial(iddevice, status);
                        if (image == 0) {
                            devicesViewHolder.imgFoto.setImageResource(R.drawable.focoencendido);
                        } else
                        { devicesViewHolder.imgFoto.setImageResource(R.drawable.aireencendido);}

                    }
                    else{

                    }
                }else{
                    Toast.makeText(activity, " Has encendido el " + device.getNombre(), Toast.LENGTH_SHORT).show();
                    ConstructorDevices constructorDevices = new ConstructorDevices();
                    int status = 1;
                    int iddevice = device.getId();
                    constructorDevices.statusDevice(device, status, image);
                    constructorDevices.insertarHistorial(iddevice, status);
                    if (image == 0) {
                        devicesViewHolder.imgFoto.setImageResource(R.drawable.focoencendido);
                    } else
                    { devicesViewHolder.imgFoto.setImageResource(R.drawable.aireencendido);}

                }

            }
        });

        devicesViewHolder.btnapagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(conex == 1){
                int d = Integer.parseInt(device.getChannel());
                int v2= d+75;
                char asciiv = (char) v2;
                String vascii= String.valueOf((asciiv));
                Bluettoth bluettoth = new Bluettoth();
                bluettoth.cbt(vascii);

                ConstructorDevices constructorDevices = new ConstructorDevices();
                if(ConnectedThread.Success()==false){
                    Toast.makeText(activity," Has apagado el "+device.getNombre(),Toast.LENGTH_SHORT).show();
                    int status =0;
                    int iddevice = device.getId();
                    constructorDevices.statusDevice(device, status, image);
                    constructorDevices.insertarHistorial(iddevice, status);
                    if(image==0){
                        devicesViewHolder.imgFoto.setImageResource(R.drawable.focoapagado);
                    }else
                    {devicesViewHolder.imgFoto.setImageResource(R.drawable.aireapagado);}

                }
                else{

                }

                }else{
                    ConstructorDevices constructorDevices = new ConstructorDevices();
                    Toast.makeText(activity," Has apagado el "+device.getNombre(),Toast.LENGTH_SHORT).show();
                    int status =0;
                    int iddevice = device.getId();
                    constructorDevices.statusDevice(device, status, image);
                    constructorDevices.insertarHistorial(iddevice, status);
                    if(image==0){
                        devicesViewHolder.imgFoto.setImageResource(R.drawable.focoapagado);
                    }else
                    {devicesViewHolder.imgFoto.setImageResource(R.drawable.aireapagado);}
                }



            }
        });

        //ENVIO DE DATOS DEL DISPOSITIVO CREADO PARA SER EDITADO O ELIMINADO
        devicesViewHolder.btnInf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Details_devices.class);
                intent.putExtra("id", device.getId());
                intent.putExtra("name", device.getNombre());
                intent.putExtra("channel", device.getChannel());
                intent.putExtra("photo", device.getPhoto());
                intent.putExtra("state", device.getState());
                intent.putExtra("watts", device.getWatts());
                intent.putExtra("about", device.getAbout());
                activity.startActivity(intent);
            }
        });

        //ENVIO DE INFORMCACION DEL DISPOSITIVO A DEVICE_CONSUMPTION
        devicesViewHolder.btnInfConsumo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Device_consumption.class);
                intent.putExtra("id", device.getId());
                intent.putExtra("name", device.getNombre());
                intent.putExtra("channel", device.getChannel());
                intent.putExtra("photo", device.getPhoto());
                intent.putExtra("state", device.getState());
                intent.putExtra("about", device.getAbout());
                intent.putExtra("watts", device.getWatts());
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
        private Button btnInfConsumo;
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
            btnInfConsumo = (Button) itemView.findViewById(R.id.btnInfConsumo);

        }
    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {

        return  device.createRfcommSocketToServiceRecord(BTMODULEUUID);
        //creates secure outgoing connecetion with BT device using UUID
    }






}
