package com.guma.desarrollo.gvm.activities;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.guma.desarrollo.gvm.MODEL.Cuotas_model;
import com.guma.desarrollo.gvm.MODEL.Mvstcla_model;
import com.guma.desarrollo.gvm.MODEL.MvtsArticulos_model;
import com.guma.desarrollo.gvm.MODEL.MvtsCliente_model;
import com.guma.desarrollo.gvm.POJO.Cuotas;
import com.guma.desarrollo.gvm.POJO.MvstCLA;
import com.guma.desarrollo.gvm.POJO.MvtsArticulos;
import com.guma.desarrollo.gvm.POJO.MvtsCliente;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.adapters.Cuotas_Leads;
import com.guma.desarrollo.gvm.adapters.MvstCLA_Leads;
import com.guma.desarrollo.gvm.adapters.MvtsCliente_Leads;
import com.guma.desarrollo.gvm.adapters.vstArticulos_Leads;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.List;

public class MesActualActivity extends AppCompatActivity {
    private static final String TAG = "MesActualActivityLOG";
    private List<MvtsArticulos> oArticulos_vendidos,oVentas_Metas;
    private List<MvtsCliente> oVentas_Clientes;
    private List<MvstCLA> oMvstCLA;
    private List<Cuotas> oCuotas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_actual);

        oArticulos_vendidos = MvtsArticulos_model.get_ventas(ManagerURI.getDirDb(), this);
        oVentas_Metas = MvtsArticulos_model.get_ventas_metas(ManagerURI.getDirDb(), this);
        oVentas_Clientes = MvtsCliente_model.get(ManagerURI.getDirDb(), this);
        oMvstCLA = Mvstcla_model.get(ManagerURI.getDirDb(), this);
        oCuotas = Cuotas_model.get(ManagerURI.getDirDb(),this);


        TextView txtMetas      = findViewById(R.id.idMeta);
        TextView txtVentas     = findViewById(R.id.idVenta);
        ListView lst_tab1      = findViewById(R.id.lst_Articulos);
        ListView lst_tab2      = findViewById(R.id.lstClientes);
        ListView lst_tab3      = findViewById(R.id.lst3);
        ListView lst_tab4      = findViewById(R.id.lst4);

        TabHost tabs = findViewById(android.R.id.tabhost);
        tabs.setup();

        if (oVentas_Metas.size()!=0){
            txtMetas.setText(oVentas_Metas.get(0).getmMeta());
            txtVentas.setText(oVentas_Metas.get(0).getMventa());
        }


        TabHost.TabSpec spec = tabs.newTabSpec("TB1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Vts. por Articulos",null);
        tabs.addTab(spec);

        spec=tabs.newTabSpec("TB2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Vts POR CLIENTE ",null);
        tabs.addTab(spec);

        spec=tabs.newTabSpec("TB3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Art. FACTURADO",null);
        tabs.addTab(spec);

        spec=tabs.newTabSpec("TB4");
        spec.setContent(R.id.tab4);
        spec.setIndicator("Cuotas por Articulo",null);
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

        if (oArticulos_vendidos.size()==0){oArticulos_vendidos.add(new MvtsArticulos("","","","","","","","","","",0));}
        if (oVentas_Clientes.size()==0){oVentas_Clientes.add(new MvtsCliente("","","","","",0));}
        if (oMvstCLA.size()==0){oMvstCLA.add(new MvstCLA("","","","","","","","",""));}
        if (oCuotas.size()==0){oCuotas.add(new Cuotas("","","",""));}



        lst_tab1.setAdapter(new vstArticulos_Leads(this, oArticulos_vendidos));
        lst_tab2.setAdapter(new MvtsCliente_Leads(this, oVentas_Clientes));
        lst_tab3.setAdapter(new MvstCLA_Leads(this, oMvstCLA));
        lst_tab4.setAdapter(new Cuotas_Leads(this, oCuotas));







    }
}
