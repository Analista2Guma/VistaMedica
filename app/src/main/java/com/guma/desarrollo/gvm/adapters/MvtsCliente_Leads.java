package com.guma.desarrollo.gvm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.guma.desarrollo.gvm.POJO.MvtsArticulos;
import com.guma.desarrollo.gvm.POJO.MvtsCliente;
import com.guma.desarrollo.gvm.R;

import java.util.List;

/**
 * Created by maryan.espinoza on 07/03/2017.
 */

public class MvtsCliente_Leads extends ArrayAdapter<MvtsCliente> {
    public MvtsCliente_Leads(Context context, List<MvtsCliente> objects) {
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


        MvtsCliente lead = getItem(position);

        it1.setText(lead.getmNam());
        it2.setText(lead.getmCcl());
        it3.setText("Veces: " + String.valueOf(lead.getmHts()));
        it4.setText("C$: " + lead.getmVnt());

        return convertView;
    }
}
