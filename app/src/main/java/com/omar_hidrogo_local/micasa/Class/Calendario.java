package com.omar_hidrogo_local.micasa.Class;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.omar_hidrogo_local.micasa.Device_consumption.ettime1;
import static com.omar_hidrogo_local.micasa.Device_consumption.ettime2;
import static com.omar_hidrogo_local.micasa.Device_consumption.fechade;
import static com.omar_hidrogo_local.micasa.Device_consumption.fechafin;

/**
 * Created by tmhidrooma on 02/02/2018.
 */

public class Calendario extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    String months, day, hour, minutes, seconds;
    int hourOfDay,minute, second;



    public Dialog onCreateDialog(Bundle savedInstanceState){
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        //SimpleDateFormat dateFormatformat1 = new SimpleDateFormat("yyyy");
        int year = c.get(Calendar.YEAR);

        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        hourOfDay   = c.get(Calendar.HOUR_OF_DAY);
        minute     = c.get(Calendar.MINUTE);
        second     = c.get(Calendar.SECOND);
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

        if (vista == 2131230753){
            //BreakIterator et_fechIni = null;
            ettime1.setText(String.valueOf(day+" / "+mes+" / "+year));

            month=month+1;
            int formattedDatehour = 00;
            int formattedDateminute = 00;
            int formattedDatesecond = 01;

            Calendar calendar = new GregorianCalendar();
            calendar.getInstance();

            StringBuilder reg = new StringBuilder();

            if(month<10){
                months = "0"+month;
            }else{
                months = String.valueOf(month);}
            if(day<10){
                this.day = "0"+day;
            }else{
                this.day = String.valueOf(day);}
            if(formattedDatehour<10) {
                hour = "0" + formattedDatehour;
            }else{
                hour = String.valueOf(formattedDatehour);}
            if(formattedDateminute<10){
                minutes = "0"+formattedDateminute;
            }else{
                minutes = String.valueOf(formattedDateminute);}
            if(formattedDatesecond<10){
                seconds = "0"+formattedDatesecond;
            }else{
                seconds = String.valueOf(formattedDatesecond);}

            reg.append(year);
            reg.append(months);
            reg.append(this.day);
            reg.append(hour);
            reg.append(minutes);
            reg.append(seconds);

            long regis = Long.parseLong((reg.toString()));

            fechade= regis;

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


            StringBuilder reg = new StringBuilder();

            if(month<10){
                months = "0"+month;
            }else{
                months = String.valueOf(month);}
            if(day<10){
                this.day = "0"+day;
            }else{
                this.day = String.valueOf(day);}
            if(formattedDatehour<10) {
                hour = "0" + formattedDatehour;
            }else{
                hour = String.valueOf(formattedDatehour);}
            if(formattedDateminute<10){
                minutes = "0"+formattedDateminute;
            }else{
                minutes = String.valueOf(formattedDateminute);}
            if(formattedDatesecond<10){
                seconds = "0"+formattedDatesecond;
            }else{
                seconds = String.valueOf(formattedDatesecond);}

            reg.append(year);
            reg.append(months);
            reg.append(this.day);
            reg.append(hour);
            reg.append(minutes);
            reg.append(seconds);

            long regis = Long.parseLong((reg.toString()));

            fechafin= regis;
        }

    }



}
