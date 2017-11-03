package com.omar_hidrogo_local.micasa.Conunication;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import com.omar_hidrogo_local.micasa.Connection_internet;
import com.omar_hidrogo_local.micasa.Device_Lists;
import com.omar_hidrogo_local.micasa.MainActivity;
import com.omar_hidrogo_local.micasa.Splash_screen;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by tmhidrooma on 27/10/2017.
 */

//cLASE DE CONNECT THREAD
public class ConnectedThread extends Thread {

    private static boolean fallo=false;


        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        Handler bluetoothIn;
        final int handlerState = 0;

    public ConnectedThread(BluetoothSocket socket){
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

       public void run() {
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
        }

        //write method
        public void write(String input) {
            byte[] msgBuffer = input.getBytes();           //converts entered String into bytes
            try {
                mmOutStream.write(msgBuffer);                //write bytes over BT connection via outstream
                fallo = false;
            } catch (IOException e) {
                //if you cannot write, close the application
                Toast.makeText(Splash_screen.getContext(), "La Conexión fallo, vincula correctamente tu dispositivo Bluetooth", Toast.LENGTH_LONG).show();
                fallo = true;
                Success();
            }

        }

        public static boolean Success(){

        return fallo;
    }


}
