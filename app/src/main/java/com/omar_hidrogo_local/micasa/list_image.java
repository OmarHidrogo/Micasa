package com.omar_hidrogo_local.micasa;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by tmhidrooma on 24/10/2017.
 */

public class list_image extends ArrayAdapter<String> {

    private final Activity context;
    private final String [] itemname;
    private final Integer[] integers;

    public list_image(Activity context, String[] itemname, Integer[] integers){
        super(context, R.layout.list_image, itemname);

        this.context=context;
        this.itemname=itemname;
        this.integers=integers;

    }

    public View getView(int posicion, View view, ViewGroup parent){

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_image, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.texto_principal);
        TextView txtDescripcion = (TextView) rowView.findViewById(R.id.texto_secundario);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);


        txtTitle.setText(itemname[posicion]);
        imageView.setImageResource(integers[posicion]);
        txtDescripcion.setText("Description "+itemname[posicion]);

        return rowView;
    }

}
