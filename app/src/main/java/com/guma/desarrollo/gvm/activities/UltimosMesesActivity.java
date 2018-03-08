package com.guma.desarrollo.gvm.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.guma.desarrollo.gvm.MODEL.Mvstcla_model;
import com.guma.desarrollo.gvm.MODEL.MvtsArticulos_model;
import com.guma.desarrollo.gvm.MODEL.MvtsCliente_model;
import com.guma.desarrollo.gvm.MODEL.vst_3m_cla_model;
import com.guma.desarrollo.gvm.MODEL.vts_3m_Cliente_model;
import com.guma.desarrollo.gvm.MODEL.vts_m3_Articulos_model;
import com.guma.desarrollo.gvm.POJO.MvstCLA;
import com.guma.desarrollo.gvm.POJO.MvtsArticulos;
import com.guma.desarrollo.gvm.POJO.MvtsCliente;
import com.guma.desarrollo.gvm.POJO.vts_3m_Articulos;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.adapters.MvstCLA_Leads;
import com.guma.desarrollo.gvm.adapters.MvtsCliente_Leads;
import com.guma.desarrollo.gvm.adapters.vstArticulos_Leads;
import com.guma.desarrollo.gvm.adapters.vst_3m_Articulos_Leads;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.List;

public class UltimosMesesActivity extends AppCompatActivity {
    private static final String TAG = "UltimosMesesActivity";
    private List<vts_3m_Articulos> oArticulos_vendidos;
    private List<MvtsArticulos> oVentas_Metas;
    private List<MvtsCliente> oVentas_Clientes;
    private List<MvstCLA> oMvstCLA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultimos_meses);


        oVentas_Metas = MvtsArticulos_model.get_ventas_metas(ManagerURI.getDirDb(), this);
        oArticulos_vendidos = vts_m3_Articulos_model.get_ventas(ManagerURI.getDirDb(), this,"");
        oVentas_Clientes = vts_3m_Cliente_model.get(ManagerURI.getDirDb(), this);

        oMvstCLA = vst_3m_cla_model.get(ManagerURI.getDirDb(), this,"");

        TextView txtVentas     = findViewById(R.id.idVenta3M);
        ListView lst_tab1      = findViewById(R.id.m3_lst1);
        ListView lst_tab2      = findViewById(R.id.m3_lst2);
        ListView lst_tab3      = findViewById(R.id.m3_lst3);

        TabHost tabs = findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("TB1");
        spec.setContent(R.id.m3_tab1);
        spec.setIndicator("Vts. por Articulos",null);
        tabs.addTab(spec);

        spec=tabs.newTabSpec("TB2");
        spec.setContent(R.id.m3_tab2);
        spec.setIndicator("Vts POR CLIENTE ",null);
        tabs.addTab(spec);

        spec=tabs.newTabSpec("TB3");
        spec.setContent(R.id.m3_tab3);
        spec.setIndicator("Art. FACTURADO",null);
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

        if (oMvstCLA.size()==0){oMvstCLA.add(new MvstCLA("","","","","","","","",""));}
        if (oVentas_Clientes.size()==0){oVentas_Clientes.add(new MvtsCliente("","","","","",0));}
        if (oArticulos_vendidos.size()==0){oArticulos_vendidos.add(new vts_3m_Articulos("","","","","","",0));}
        if (oVentas_Metas.size()!=0){txtVentas.setText("C$ " + oVentas_Metas.get(0).getmV3m());}
        lst_tab1.setAdapter(new vst_3m_Articulos_Leads(this, oArticulos_vendidos));
        lst_tab2.setAdapter(new MvtsCliente_Leads(this, oVentas_Clientes));
        lst_tab3.setAdapter(new MvstCLA_Leads(this, oMvstCLA));





    }
}
