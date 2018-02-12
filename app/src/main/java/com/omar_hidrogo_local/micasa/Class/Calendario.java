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
            //SimpleDateFormat dateFormatformat = new SimpleDateFormat("MM-dd");

            //SimpleDateFormat dateFormatformat1 = new SimpleDateFormat("yyyy");
            //Date datetime1 = new Date(year);
            //String formattedDateString1 = dateFormatformat.format(datetime1);
            //year= Integer.parseInt(formattedDateString1);
            //Date datetime = new Date(year,month,day,00,00,00) ;
            //String formattedDateString = dateFormatformat.format(datetime);
            //int mounth = (month<10?("0"+month):(month));
            //String  Dates = year+"-"+formattedDateString+" 00:00:00";
            //fechade=Dates;
            //intent.putExtra("ett1", formattedDateString);
           // getActivity().startActivity(intent);

            //SimpleDateFormat dateFormatyear = new SimpleDateFormat("yyyy");
            //int formattedDateyear = Integer.parseInt(dateFormatyear.format(datetime));

            //SimpleDateFormat dateFormatmonth = new SimpleDateFormat("MM");
            //int formattedDatemonth = Integer.parseInt(dateFormatmonth.format(datetime));

            //SimpleDateFormat dateFormatday = new SimpleDateFormat("dd");
            //int formattedDateday = day;

            month=month+1;
            int formattedDatehour = 00;
            int formattedDateminute = 00;
            int formattedDatesecond = 01;
            double extra = (100.0 * year) + month - 190002.5;
            double datejuliano = (367.0 * year) -
                    (Math.floor(7.0 * (year + Math.floor((month + 9.0) / 12.0)) / 4.0)) +
                    Math.floor((275.0 * month) / 9.0) +
                    day + ((formattedDatehour + ((formattedDateminute + (formattedDatesecond / 60.0)) / 60.0)) / 24.0) +
                    1721013.5 - ((0.5 * extra) / Math.abs(extra)) + 0.5;

            fechade= datejuliano;

        }else {
            //BreakIterator et_fechast = null;
            ettime2.setText(String.valueOf(day+" / "+mes+" / "+year));
            //Intent intent = new Intent(getActivity(),Device_consumption.class);
            //SimpleDateFormat dateFormatformat = new SimpleDateFormat("MM-dd");
            //SimpleDateFormat dateFormatformat1 = new SimpleDateFormat("yyyy");
            //Date datetime1 = new Date(year);
            //String formattedDateString1 = dateFormatformat.format(datetime1);
            //year= Integer.parseInt(formattedDateString1);
            Date datetime = new Date(year,month,day,23,59,59) ;
            //String formattedDateString = dateFormatformat.format(datetime);
            //String Dates = year+ "-" +(month<10?("0"+month):(month))+"-"+(day<10?("0"+day):(day));//+" "+"00:00:00";
            //String  Dates = year+"-"+formattedDateString+" 23:59:59";
            //fechafin=Dates;
            //intent.putExtra("ett2", formattedDateString);
           // getActivity().startActivity(intent);

            month=month+1;
            int formattedDatehour = 23;
            int formattedDateminute = 59;
            int formattedDatesecond = 59;
            double extra = (100.0 * year) + month - 190002.5;

            double datejuliano = (367.0 * year) -
                    (Math.floor(7.0 * (year + Math.floor((month + 9.0) / 12.0)) / 4.0)) +
                    Math.floor((275.0 * month) / 9.0) +
                    day + ((formattedDatehour + ((formattedDateminute + (formattedDatesecond / 60.0)) / 60.0)) / 24.0) +
                    1721013.5 - ((0.5 * extra) / Math.abs(extra)) + 0.5;

            fechafin= datejuliano;
        }

    }



}
