package com.omar_hidrogo_local.micasa;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.omar_hidrogo_local.micasa.Database.ConstructorDevices;
import com.omar_hidrogo_local.micasa.pojo.ConsumptionDevice;
import com.omar_hidrogo_local.micasa.pojo.Devices;

import java.util.ArrayList;
import java.util.List;

public class Device_consumption_log extends Fragment implements AdapterView.OnItemSelectedListener {

    //variable de lista Mascotas
    private ImageView imgconsumptiondevice;
    private TextView  tvdevice, /*etiddevice*/ tvlogdevice;
    private Button btnInfConsumo;
    private Spinner spinner;
    private ConstructorDevices constructorDevices;

    private ArrayList<ConsumptionDevice> consumptionDevices;
    private ArrayList<Devices> devices;

    private int valuiddevidesearch;
    private String valuinameevidesearch;
    int image;
    int status;
    String estdeviceactual;

    String year,month,day,hour,minute,second;


    private Integer[] imgid= {
            R.drawable.focoapagado,
            R.drawable.aireapagado,
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_device_consumption_log, container, false);


        imgconsumptiondevice = (ImageView) v.findViewById(R.id.imgconsumptiondevice);
        tvdevice = (TextView) v.findViewById(R.id.tvdevice);
        spinner = (Spinner) v.findViewById(R.id.spinnerdevice);
        tvlogdevice = (TextView) v.findViewById(R.id.tvlogdevice);
        btnInfConsumo = (Button) v.findViewById(R.id.btnInfConsumo);

        //btnInfConsumo.setOnClickListener((View.OnClickListener) this);

        constructorDevices = new ConstructorDevices();
        devices = constructorDevices.obtenerDatos();
        /*GridLayoutManager glm = new GridLayoutManager(getActivity(),1);
        rvconsumptiondevice.setLayoutManager(glm);*/
        //inicializarListaMascotas();

        btnInfConsumo.setOnClickListener(new  View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                String textspinner = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();


                    for(int i =0; i< devices.size(); i++){
                        Devices device = devices.get(i);
                        //String name = device.getNombre();
                        if(device.getNombre() == textspinner){
                            //name = device.getNombre();
                            valuinameevidesearch=device.getNombre();
                            valuiddevidesearch=device.getId();
                            image = device.getPhoto();
                            status = device.getState();
                        }else{
                            //valuinameevidesearch=device.getNombre();
                            //valuiddevidesearch=device.getId();
                        }


                    }


               consumptionDevices = constructorDevices.obtenerconsumoporDevice(valuiddevidesearch );
                tvdevice.setText(valuinameevidesearch);
                tvlogdevice.setText("");
               calculolog();

            }
        });

        List<String> value = new ArrayList<>();

        for (int i =0; i< devices.size(); i++){
            Devices device = devices.get(i);
            value.add(device.getNombre());
            //valuiddevidesearch=device.getId();
            //valuinameevidesearch=device.getNombre();
        }

        /*spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?>
                                               arg0,View arg1,int arg2,long arg3){
            }
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });*/

        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(MainActivity.getContext(), android.R.layout.simple_spinner_dropdown_item, value);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this);

        return v;

    }

    public void calculolog() {
        //devices = new ArrayList<>();
        consumptionDevices = constructorDevices.obtenerconsumoporDevice(valuiddevidesearch );
        tvdevice.setText(valuinameevidesearch);
        /*for(int i =0; i< devices.size(); i++){
            Devices device = devices.get(i);
            String name = device.getNombre();
            if(name == valuinameevidesearch){
                image = device.getPhoto();
                status = device.getState();
            }else{
                image = device.getPhoto();
                status = device.getState();
            }
        }*/

        switch (image) {
            case 0:
                imgconsumptiondevice.setImageResource(R.drawable.focoapagado);
                if (status == 0) {
                    imgconsumptiondevice.setImageResource(R.drawable.focoapagado);
                } else
                    imgconsumptiondevice.setImageResource(R.drawable.focoencendido);
                break;
            case 1:
                imgconsumptiondevice.setImageResource(R.drawable.aireapagado);
                if (status == 0) {
                    imgconsumptiondevice.setImageResource(R.drawable.aireapagado);
                } else
                    imgconsumptiondevice.setImageResource(R.drawable.aireencendido);
                break;
            default:
        }

        if(consumptionDevices.size()==0){
            tvlogdevice.setText(R.string.tvlogdevice);
            tvlogdevice.setTextSize(20);
        }else{
            tvlogdevice.setTextSize(14);
            for (int i = 0; i < consumptionDevices.size(); i++) {

                ConsumptionDevice consumptionDevice = consumptionDevices.get(i);
                String dateStr = String.valueOf(consumptionDevice.getTime());
                year = dateStr.substring(0,4);
                month = dateStr.substring(4,6);
                day = dateStr.substring(6,8);
                hour = dateStr.substring(8,10);
                minute = dateStr.substring(10,12);
                second = dateStr.substring(12,14);

            /*Calendar cal  = new GregorianCalendar();
            cal.set(Calendar.YEAR, (int) Double.parseDouble(dateStr.substring(0,3)));
            cal.set(Calendar.DAY_OF_YEAR, (int) Double.parseDouble(dateStr.substring(4)));
            Date parsedDate  = cal.getTime();*/

            /*SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
            Date date = fmt.parse(dateString);*/

                if(consumptionDevice.getStatus() == 0){
                    estdeviceactual = "off";
                }else{
                    estdeviceactual = "on";
                }

                String log = "# "+ consumptionDevice.getIdhistorial()+"  /  status: "+ estdeviceactual+"  /  date: "
                        +day+"/"+month+"/"+year+" "+hour+" hours "+minute+" min. "+second+" sec. "+"\n\n";

                tvlogdevice.append(log);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(parent.getContext(), (String) parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
