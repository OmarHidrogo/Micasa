package com.omar_hidrogo_local.micasa.adaptador;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.annotation.DrawableRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.omar_hidrogo_local.micasa.Device_Lists;
import com.omar_hidrogo_local.micasa.MainActivity;
import com.omar_hidrogo_local.micasa.Splash_screen;
import com.omar_hidrogo_local.micasa.pojo.Devices;
import com.omar_hidrogo_local.micasa.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    private BluetoothSocket btSocket = null;
    private StringBuilder recDataString = new StringBuilder();

    private ConnectedThread mConnectedThread;
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static String address = null;

    ArrayList<Devices> devices;
    private Activity activity;
    Context context;


    private ProgressDialog progress;




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
                SharedPreferences miprefBluetooth = MainActivity.getContext().getSharedPreferences("cBluetooth", Context.MODE_PRIVATE);   // se inicializa preferencia donde cuardara la conexion  de la casa a controlar por Bluetooth
                address = miprefBluetooth.getString("cBluetooth", "");
                BluetoothDevice device = btAdapter.getRemoteDevice(address);
                try {
                    btSocket = createBluetoothSocket(device);
                } catch (IOException e) {
                    //Toast.makeText(this, "La creacción del Socket fallo", Toast.LENGTH_LONG).show();
                }
                // Establish the Bluetooth socket connection.
                try
                {
                    btSocket.connect();
                } catch (IOException e) {
                    try
                    {
                        btSocket.close();
                    } catch (IOException e2)
                    {
                        //insert code to deal with this
                    }
                }
                mConnectedThread = new ConnectedThread(btSocket);
                mConnectedThread.start();

                //I send a character when resuming.beginning transmission to check device is connected
                //If it is not an exception will be thrown in the write method and finish() will be called
               // mConnectedThread.write("x");
                mConnectedThread.write("1");    // Send "1" via Bluetooth

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
                SharedPreferences miprefBluetooth = MainActivity.getContext().getSharedPreferences("cBluetooth", Context.MODE_PRIVATE);   // se inicializa preferencia donde cuardara la conexion  de la casa a controlar por Bluetooth
                address = miprefBluetooth.getString("cBluetooth", "");
                btAdapter = BluetoothAdapter.getDefaultAdapter();       // get Bluetooth adapter
                BluetoothDevice device = btAdapter.getRemoteDevice(address);
                try {
                    btSocket = createBluetoothSocket(device);
                } catch (IOException e) {
                    //Toast.makeText(this, "La creacción del Socket fallo", Toast.LENGTH_LONG).show();
                }
                // Establish the Bluetooth socket connection.
                try
                {
                    btSocket.connect();
                } catch (IOException e) {
                    try
                    {
                        btSocket.close();
                    } catch (IOException e2)
                    {
                        //insert code to deal with this
                    }
                }
                mConnectedThread = new ConnectedThread(btSocket);
                mConnectedThread.start();

                //I send a character when resuming.beginning transmission to check device is connected
                //If it is not an exception will be thrown in the write method and finish() will be called
                // mConnectedThread.write("x");
                mConnectedThread.write("2");    // Send "0" via Bluetooth

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

    //cLASE DE CONNECT THREAD
    private class ConnectedThread extends Thread{
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        private ConnectedThread (BluetoothSocket socket){
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                //Create I/O streams for connection
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

       /*public void run() {
            byte[] buffer = new byte[256];
            int bytes;

            // Keep looping to listen for received messages
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);         //read bytes from input buffer
                    String readMessage = new String(buffer, 0, bytes);
                    // Send the obtained bytes to the UI Activity via handler
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }*/

        //write method
        public void write(String input) {
            byte[] msgBuffer = input.getBytes();           //converts entered String into bytes
            try {
                mmOutStream.write(msgBuffer);                //write bytes over BT connection via outstream
            } catch (IOException e) {
                //if you cannot write, close the application
                Toast.makeText(Splash_screen.getContext(), "La Conexión fallo", Toast.LENGTH_LONG).show();
            }
        }
    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {

        return  device.createRfcommSocketToServiceRecord(BTMODULEUUID);
        //creates secure outgoing connecetion with BT device using UUID
    }




}
