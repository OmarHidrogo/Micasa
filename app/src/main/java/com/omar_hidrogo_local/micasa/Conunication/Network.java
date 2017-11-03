package com.omar_hidrogo_local.micasa.Conunication;

import android.view.View;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by tmhidrooma on 27/10/2017.
 */

public class Network {

    //Prendemos LED
    public void sendOn(){
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://192.168.0.110/?L=1");
        //Esta es nuestra url donde mandaremos el parametro uno
        try {
            //List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            //Codigo anterior donde tenia el error, pero esto funciona si queremos hacer post
            //nameValuePairs.add(new BasicNameValuePair("L", "1"));
            //httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            httpclient.execute(httppost); // mandamos el parametro al sercidor
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }

    //Apagamos LED
    public void sendOff(View v){
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://192.168.0.110/?");
        //URL donde lo apagamos ya que no envia ninguna variable, nosotros podriamos ponerle un 0
        //pero en el codigo del arduino hay que cambiarlo
        try {
            //List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            //nameValuePairs.add(new BasicNameValuePair("L", "1"));
            // httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            httpclient.execute(httppost);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }
}
