package com.guma.desarrollo.gvm.activities;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.guma.desarrollo.gvm.MODEL.MvtsArticulos_model;
import com.guma.desarrollo.gvm.MODEL.MvtsCliente_model;
import com.guma.desarrollo.gvm.POJO.MvtsArticulos;
import com.guma.desarrollo.gvm.POJO.MvtsCliente;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.adapters.vstArticulos_Leads;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.List;

public class MesActualActivity extends AppCompatActivity {
    private static final String TAG = "MesActualActivity";
    private List<MvtsArticulos> oArticulos_vendidos,oVentas_Metas;
    private List<MvtsCliente> oVentas_Clientes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_actual);

        oArticulos_vendidos = MvtsArticulos_model.get_ventas(ManagerURI.getDirDb(), this);
        oVentas_Metas = MvtsArticulos_model.get_ventas_metas(ManagerURI.getDirDb(), this);
        oVentas_Clientes = MvtsCliente_model.get(ManagerURI.getDirDb(), this);


        TextView txtMetas      = findViewById(R.id.idMeta);
        TextView txtVentas     = findViewById(R.id.idVenta);
        ListView lstArticulo   = findViewById(R.id.lst_Articulos);
        TabHost tabs = findViewById(android.R.id.tabhost);
        tabs.setup();


        txtMetas.setText(oVentas_Metas.get(0).getmMeta());
        txtVentas.setText(oVentas_Metas.get(0).getMventa());



        TabHost.TabSpec spec = tabs.newTabSpec("TB1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Vts. por Articulos",null);
        tabs.addTab(spec);

        spec=tabs.newTabSpec("TB2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("VENTAS POR CLIENTE ",null);
        tabs.addTab(spec);

        spec=tabs.newTabSpec("TB3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("FACTURADO",null);
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

        lstArticulo.setAdapter(new vstArticulos_Leads(this, oArticulos_vendidos));




    }
}
