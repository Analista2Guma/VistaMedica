package com.guma.desarrollo.gvm.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.guma.desarrollo.gvm.activities.HistorialActivity;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.POJO.MvtsArticulos;

import java.util.List;

/**
 * Created by maryan.espinoza on 07/03/2017.
 */

public class vstArticulos_Leads extends ArrayAdapter<MvtsArticulos>{
    private Context context;
    public vstArticulos_Leads(Context context, List<MvtsArticulos> objects) {
        super(context, 0, objects);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         if (null == convertView) {
             convertView = inflater.inflate(R.layout.list_item,parent,false);
         }

        if (position % 2 == 0) {
            convertView.setBackgroundResource(R.drawable.artists_list_backgroundcolor);
        } else {
            convertView.setBackgroundResource(R.drawable.artists_list_background_alternate);
        }
        final MvtsArticulos lead = getItem(position);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HistorialActivity.class);
                intent.putExtra("COD", lead.getmArt());
                intent.putExtra("View","AR");
                context.startActivity(intent);
            }
        });



        TextView it1 = convertView.findViewById(R.id.id_itm1);
        TextView it2 = convertView.findViewById(R.id.id_itm2);
        TextView it3 = convertView.findViewById(R.id.id_itm3);
        TextView it4 = convertView.findViewById(R.id.id_itm4);
        TextView it5 = convertView.findViewById(R.id.id_itm5);



        it1.setText(lead.getmDic());
        it2.setText(lead.getmArt());
        it3.setText("Cantidad: " + lead.getmCnt());
        it4.setText("C$: " + lead.getmVnt());
        it5.setText("Veces: " + String.valueOf(lead.getmHts()));



        return convertView;
    }
}
