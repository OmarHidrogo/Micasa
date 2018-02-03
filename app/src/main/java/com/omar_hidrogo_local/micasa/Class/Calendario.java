package com.omar_hidrogo_local.micasa.Class;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.omar_hidrogo_local.micasa.Details_devices;
import com.omar_hidrogo_local.micasa.Device_consumption;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.omar_hidrogo_local.micasa.Device_consumption.ettime1;
import static com.omar_hidrogo_local.micasa.Device_consumption.ettime2;
import static com.omar_hidrogo_local.micasa.Device_consumption.fechade;
import static com.omar_hidrogo_local.micasa.Device_consumption.fechafin;

/**
 * Created by tmhidrooma on 02/02/2018.
 */

public class Calendario extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    String t1, t2;

    public Dialog onCreateDialog(Bundle savedInstanceState){
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        //SimpleDateFormat dateFormatformat1 = new SimpleDateFormat("yyyy");
        int year = c.get(Calendar.YEAR);
        //Date datetime = new Date(year);
        //String formattedDateString = dateFormatformat.format(datetime);
       // year= Integer.parseInt(formattedDateString);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        long vista = getArguments().getLong("vista");

        String mes;
        switch (month){
            case 0:
                mes= "Enero";
                break;
            case 1:
                mes= "Febrero";
                break;
            case 2:
                mes= "Marzo";
                break;
            case 3:
                mes= "Abril";
                break;
            case 4:
                mes= "Mayo";
                break;
            case 5:
                mes= "Junio";
                break;
            case 6:
                mes= "Julio";
                break;
            case 7:
                mes= "Agosto";
                break;
            case 8:
                mes= "Septiembre";
                break;
            case 9:
                mes= "Octubre";
                break;
            case 10:
                mes= "Noviembre";
                break;
            default: mes="Diciembre";
        }

        if (vista == 2131230751){
            //BreakIterator et_fechIni = null;
            ettime1.setText(String.valueOf(day+" / "+mes+" / "+year));
            //Intent intent = new Intent(getActivity(),Device_consumption.class);
            SimpleDateFormat dateFormatformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //SimpleDateFormat dateFormatformat1 = new SimpleDateFormat("yyyy");
            //Date datetime1 = new Date(year);
            //String formattedDateString1 = dateFormatformat.format(datetime1);
            //year= Integer.parseInt(formattedDateString1);
            Date datetime = new Date(year,month,day,00,00,00) ;
            String formattedDateString = dateFormatformat.format(datetime);
            String Dates = year+ "-" +(month<10?("0"+month):(month))+"-"+(day<10?("0"+day):(day))+" "+"00:00:00";
            fechade=Dates;
            //intent.putExtra("ett1", formattedDateString);
           // getActivity().startActivity(intent);
        }else {
            //BreakIterator et_fechast = null;
            ettime2.setText(String.valueOf(day+" / "+mes+" / "+year));
            //Intent intent = new Intent(getActivity(),Device_consumption.class);
            SimpleDateFormat dateFormatformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //SimpleDateFormat dateFormatformat1 = new SimpleDateFormat("yyyy");
            //Date datetime1 = new Date(year);
            //String formattedDateString1 = dateFormatformat.format(datetime1);
            //year= Integer.parseInt(formattedDateString1);
            Date datetime = new Date(year,month,day,00,00,00) ;
            String formattedDateString = dateFormatformat.format(datetime);
            String Dates = year+ "-" +(month<10?("0"+month):(month))+"-"+(day<10?("0"+day):(day))+" "+"00:00:00";
            fechafin=Dates;
            //intent.putExtra("ett2", formattedDateString);
           // getActivity().startActivity(intent);
        }

    }
}
