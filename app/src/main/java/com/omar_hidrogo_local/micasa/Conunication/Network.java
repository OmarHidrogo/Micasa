package com.omar_hidrogo_local.micasa.Conunication;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.omar_hidrogo_local.micasa.MainActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by tmhidrooma on 27/10/2017.
 */

public class Network {

    SharedPreferences miprefInternet = MainActivity.getContext().getSharedPreferences("cInternet", Context.MODE_PRIVATE);
    String cInternet = miprefInternet.getString("cInternet", "");
    //Prendemos LED
    public void sendOn(){
        HttpClient httpclient = new DefaultHttpClient();

        HttpPost httppost = new HttpPost("http://"+cInternet+"/?L=1");
        //Esta es nuestra url donde mandaremos el parametro uno
        try {
            //List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            //Codigo anterior donde tenia el error, pero esto funciona si queremos hacer post
            //nameValuePairs.add(new BasicNameValuePair("L", "1"));
            //httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response =  httpclient.execute(httppost); // mandamos el parametro al sercidor

            //Luego procesamos el contenido de la respuesta en formato InputStream Buffer y la paso a formato String
            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();

            //Respuesta en formato String
            String respsuesta = sb.toString();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }

    //Apagamos LED
    public void sendOff(View v){
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://"+cInternet+"/?");
        //URL donde lo apagamos ya que no envia ninguna variable, nosotros podriamos ponerle un 0
        //pero en el codigo del arduino hay que cambiarlo
        try {
            //List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            //nameValuePairs.add(new BasicNameValuePair("L", "1"));
            // httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response =  httpclient.execute(httppost); // mandamos el parametro al sercidor

            //Luego procesamos el contenido de la respuesta en formato InputStream Buffer y la paso a formato String
            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();

            //Respuesta en formato String
            String respsuesta = sb.toString();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }
}
