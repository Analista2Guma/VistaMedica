package com.guma.desarrollo.gvm.activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.guma.desarrollo.gvm.MODEL.LogActividades_model;
import com.guma.desarrollo.gvm.POJO.Log_Actividades;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.adapters.Actividad_Adapter;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.List;

public class DetalleContactoActivity extends AppCompatActivity {
    String user ,IDFarmacias,Nombre="N/A",Direccion="N/A";
    TextView txtName,txtDir;
    RecyclerView recyclerViewClientes;
    private List<Log_Actividades> oActividad;
    ImageView getPlaces;
    String Type="";
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){ getSupportActionBar().setDisplayHomeAsUpEnabled(true); }
        setTitle("INFORMACION DEL CONTACTO");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            IDFarmacias = bundle.getString("UID");
            Type = bundle.getString("Tipo");
            Nombre = bundle.getString("peNombre");
            Direccion = bundle.getString("peDireccion");

        }
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        user =preferences.getString("Ruta","");

        recyclerViewClientes = findViewById(R.id.clientesRecyclerView);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getBaseContext());
        linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewClientes.setLayoutManager(linearLayout);

        txtName = findViewById(R.id.txNombre);
        txtDir = findViewById(R.id.txDirec);
        getPlaces = findViewById(R.id.imgPlace);

        txtName.setText(Nombre);
        txtDir.setText(Direccion);
        FloatingActionButton fab = findViewById(R.id.fab);

        if (Type.equals("CL")){
            fab.hide();
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Type.equals("F")){
                    Intent intent = new Intent(DetalleContactoActivity.this, AddFarmaciasActivity.class);
                    intent.putExtra("Accion", "Editar");
                    intent.putExtra("UID", IDFarmacias);
                    startActivity(intent);
                }else if (Type.equals("M")){
                    Intent intent = new Intent(DetalleContactoActivity.this, AddMedicosActivity.class);
                    intent.putExtra("Accion", "Editar");
                    intent.putExtra("UID", IDFarmacias);
                    startActivity(intent);
                }else{
                    Toast.makeText(DetalleContactoActivity.this, "Algo Salio mal", Toast.LENGTH_SHORT).show();
                }
                //finish();
            }
        });

        getPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetalleContactoActivity.this, LocationActivity.class);
                intent.putExtra("UID", IDFarmacias);
                intent.putExtra("Accion", "");
                intent.putExtra("nmCLiente", Nombre);
                startActivity(intent);
                //finish();
            }
        });

        Llenar();




    }
    public boolean onOptionsItemSelected(MenuItem item)    {
        int id = item.getItemId();
        switch (id) {
            case 16908332:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void Llenar() {
        oActividad = LogActividades_model.get(ManagerURI.getDirDb(),this, IDFarmacias);
        Actividad_Adapter ActividadAdapter = new Actividad_Adapter(oActividad, getBaseContext(), this);
        recyclerViewClientes.setAdapter(ActividadAdapter);
    }


}
