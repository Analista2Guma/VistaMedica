package com.guma.desarrollo.gvm.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.guma.desarrollo.gvm.LIB.Clock;
import com.guma.desarrollo.gvm.MODEL.Farmacias_model;
import com.guma.desarrollo.gvm.MODEL.LogActividades_model;
import com.guma.desarrollo.gvm.POJO.DetalleLog;
import com.guma.desarrollo.gvm.POJO.Farmacias;
import com.guma.desarrollo.gvm.POJO.Log_Actividades;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.adapters.Actividad_Adapter;
import com.guma.desarrollo.gvm.adapters.Farmacias_Adapter;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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


        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
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
                finish();
            }
        });

        getPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetalleContactoActivity.this, LocationActivity.class);
                intent.putExtra("UID", IDFarmacias);
                startActivity(intent);
                finish();


               /* new AlertDialog.Builder(DetalleContactoActivity.this)
                        .setMessage("¿Seguro que decea marcar la visita?")
                        .setCancelable(false)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                recyclerViewClientes.setAdapter( new Actividad_Adapter(new ArrayList<Log_Actividades>(), getBaseContext(), DetalleContactoActivity.this));
                                ArrayList<Log_Actividades> alog = new ArrayList<>();
                                Log_Actividades tmp = new Log_Actividades();
                                tmp.setmUID(IDFarmacias);
                                tmp.setmRuta(user);
                                tmp.setmFecha(System.currentTimeMillis());
                                alog.add(tmp);
                                LogActividades_model.Save(DetalleContactoActivity.this,alog);

                                Llenar();
                                //finish();
                            }
                        })
                        .setNegativeButton("NO", null)
                        .show();*/
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

        String json = new Gson().toJson(LogActividades_model.get_detalle(ManagerURI.getDirDb(),this));
        //Log.d("Llenar:", "Llenar: " + json);

        Actividad_Adapter ActividadAdapter = new Actividad_Adapter(oActividad, getBaseContext(), this);
        recyclerViewClientes.setAdapter(ActividadAdapter);
    }


}
