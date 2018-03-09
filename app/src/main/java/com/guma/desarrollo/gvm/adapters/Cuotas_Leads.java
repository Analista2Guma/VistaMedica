package com.guma.desarrollo.gvm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.guma.desarrollo.gvm.POJO.Cuotas;
import com.guma.desarrollo.gvm.POJO.MvstCLA;
import com.guma.desarrollo.gvm.R;

import java.util.List;

/**
 * Created by maryan.espinoza on 07/03/2017.
 */

public class Cuotas_Leads extends ArrayAdapter<Cuotas> {
    public Cuotas_Leads(Context context, List<Cuotas> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         if (null == convertView) {
             convertView = inflater.inflate(R.layout.list_item,parent,false);
         }

        if (position % 2 == 0) {
            convertView.setBackgroundResource(R.drawable.artists_list_backgroundcolor);
        } else {
            convertView.setBackgroundResource(R.drawable.artists_list_background_alternate);
        }

        TextView it1 = convertView.findViewById(R.id.id_itm1);
        TextView it2 = convertView.findViewById(R.id.id_itm2);
        TextView it3 = convertView.findViewById(R.id.id_itm3);
        TextView it4 = convertView.findViewById(R.id.id_itm4);
        TextView it5 = convertView.findViewById(R.id.id_itm5);

        Cuotas lead = getItem(position);
        Integer Cantidad = Integer.valueOf(lead.getmCant());
        Integer Vendido = Integer.valueOf(lead.getmCnAc());
        Integer Pendiente = Cantidad - Vendido;

        it1.setText(lead.getmDesc());
        it2.setText(lead.getmArti());
        it3.setText("Cuota: " + String.valueOf(Cantidad));
        it4.setText("Vendido: " + String.valueOf(Vendido));

        it5.setText("Pendiente: " + String.valueOf(Pendiente));
        if (Pendiente>=0){
            it5.setTextColor(getContext().getResources().getColor(R.color.color_NOk));
        }else {
            it5.setTextColor(getContext().getResources().getColor(R.color.color_ok));
        }


        return convertView;
    }
}
