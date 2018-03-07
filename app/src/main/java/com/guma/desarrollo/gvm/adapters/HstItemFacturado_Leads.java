package com.guma.desarrollo.gvm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.guma.desarrollo.gvm.POJO.HstItemFacturados;
import com.guma.desarrollo.gvm.POJO.MvstCLA;
import com.guma.desarrollo.gvm.R;

import java.util.List;

/**
 * Created by maryan.espinoza on 07/03/2017.
 */

public class HstItemFacturado_Leads extends ArrayAdapter<HstItemFacturados> {
    public HstItemFacturado_Leads(Context context, List<HstItemFacturados> objects) {
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

        HstItemFacturados lead = getItem(position);

        it1.setText(lead.getmDes());
        it2.setText(lead.getmArt());
        it3.setText("Cantidad: " + lead.getmCan());
        it4.setText("C$: " + lead.getmVnt());

        return convertView;
    }
}
