package com.guma.desarrollo.gvm.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.guma.desarrollo.gvm.MODEL.Lotes_model;
import com.guma.desarrollo.gvm.POJO.Lotes;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.adapters.LotesAdapter;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.List;

public class DetalleArticulosActivity extends AppCompatActivity {
    RecyclerView rviewArticulos;
    List<Lotes> oLotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_articulos);
        if (getSupportActionBar() != null){ getSupportActionBar().setDisplayHomeAsUpEnabled(true); }
        Bundle bundle = getIntent().getExtras();
        setTitle(bundle.getString("Name_articulo").toUpperCase());
        rviewArticulos = findViewById(R.id.rvArticulos);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getBaseContext());
        linearLayout.setOrientation(LinearLayoutManager.VERTICAL);

        rviewArticulos.setLayoutManager(linearLayout);
        TextView txt = findViewById(R.id.txtReglas);
        txt.setText(bundle.getString("Art_Reglas"));
        oLotes = Lotes_model.get(ManagerURI.getDirDb(), this,bundle.getString("Cod_articulo"));
        rviewArticulos.setAdapter(new LotesAdapter(oLotes, getBaseContext(), this));

    }
    public boolean onOptionsItemSelected(MenuItem item)    {
        if (item.getItemId() == 16908332){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
