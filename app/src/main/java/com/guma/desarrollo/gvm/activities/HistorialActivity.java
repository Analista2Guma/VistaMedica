package com.guma.desarrollo.gvm.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import com.guma.desarrollo.gvm.MODEL.HstItemFacturado_model;
import com.guma.desarrollo.gvm.MODEL.vst_3m_cla_model;
import com.guma.desarrollo.gvm.POJO.Articulo;
import com.guma.desarrollo.gvm.POJO.MvstCLA;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.adapters.HistoricoAdapter;
import com.guma.desarrollo.gvm.adapters.HistoricoArticuloAdapter;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.List;

public class HistorialActivity extends AppCompatActivity {
    private List<MvstCLA> oMvstCLA;
    private List<Articulo> oArticulo;


    RecyclerView recyclerViewArticulos;
    List<MvstCLA> oArticulos_vendidos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        Bundle bundle = getIntent().getExtras();
        recyclerViewArticulos = findViewById(R.id.articulosRecyclerView);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getBaseContext());
        linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
        if (getSupportActionBar() != null){ getSupportActionBar().setDisplayHomeAsUpEnabled(true); }
        recyclerViewArticulos.setLayoutManager(linearLayout);

        if (bundle.getString("View").equals("CL")){
            //oArticulo = Articulos_model.get(ManagerURI.getDirDb(),this);
            setTitle("HISTORIAL DEL CLIENTE");
            oMvstCLA = vst_3m_cla_model.get(ManagerURI.getDirDb(), this,bundle.getString("COD"));
            recyclerViewArticulos.setAdapter(new HistoricoAdapter(oMvstCLA, getBaseContext(), this));
        }else{
            setTitle("HISTORIAL ARTICULO");
            oArticulos_vendidos = HstItemFacturado_model.get_articulos(ManagerURI.getDirDb(), this,bundle.getString("COD"));
            recyclerViewArticulos.setAdapter(new HistoricoArticuloAdapter(oArticulos_vendidos, getBaseContext(), this));
        }
    }
    public boolean onOptionsItemSelected(MenuItem item)    {
        if (item.getItemId() == 16908332){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
