package com.guma.desarrollo.gvm.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;


import com.guma.desarrollo.gvm.MODEL.HstItemFacturado_model;
import com.guma.desarrollo.gvm.MODEL.vst_3m_cla_model;
import com.guma.desarrollo.gvm.POJO.HstItemFacturados;
import com.guma.desarrollo.gvm.POJO.MvstCLA;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.adapters.HstItemFacturado_Leads;
import com.guma.desarrollo.gvm.adapters.MvstCLA_Leads;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.List;

public class ClienteDetalleActivity extends AppCompatActivity {
    private List<MvstCLA> oMvstCLA;
    private List<HstItemFacturados> oArticulos_vendidos;

    int Puntos;
    String Nombre,Codigo,Direc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_detalle);
        TextView textViewNombre = findViewById(R.id.detalleClienteNombre);
        TextView textViewCodigo = findViewById(R.id.detalleClienteCodigo);
        TextView textViewDir = findViewById(R.id.clDir);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Nombre = bundle.getString("nombre");
            Codigo = bundle.getString("codigo");
            Direc = bundle.getString("Dire");

            textViewNombre.setText(Nombre);
            textViewCodigo.setText(Codigo);
            textViewDir.setText(Direc);
        }

        ListView lst_tab1      = findViewById(R.id.m3_lst_item_facturado);
        ListView lst_tab3      = findViewById(R.id.m3_lst_historico);

        oArticulos_vendidos = HstItemFacturado_model.get(ManagerURI.getDirDb(), this,bundle.getString("codigo"));
        oMvstCLA = vst_3m_cla_model.get(ManagerURI.getDirDb(), this,bundle.getString("codigo"));




        lst_tab3.setAdapter(new MvstCLA_Leads(this, oMvstCLA));
        lst_tab1.setAdapter(new HstItemFacturado_Leads(this, oArticulos_vendidos));


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mn_historial, menu);        
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)    {
        int id = item.getItemId();
        switch (id) {
            case 16908332:
                finish();
                return true;
            case R.id.action_gps:
                Intent intent = new Intent(this, DetalleContactoActivity.class);
                intent.putExtra("UID", Codigo);
                intent.putExtra("Tipo", "CL");
                intent.putExtra("peNombre", Nombre);
                intent.putExtra("peDireccion", Direc);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
