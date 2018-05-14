package com.guma.desarrollo.gvm.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.guma.desarrollo.gvm.DATABASE.SQLiteHelper;
import com.guma.desarrollo.gvm.MODEL.LogActividades_model;
import com.guma.desarrollo.gvm.POJO.Articulo;
import com.guma.desarrollo.gvm.POJO.Log_Actividades;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.TASK.TaskDeleteMedicos;
import com.guma.desarrollo.gvm.adapters.Actividad_Adapter;
import com.guma.desarrollo.gvm.adapters.Actividad_Historial_Adapter;
import com.guma.desarrollo.gvm.adapters.ArticulosAdapter;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ReporteHistorialActivity extends AppCompatActivity {
    List<Log_Actividades> oActividad;
    RecyclerView rvReporteVisitas;
    SearchView searchView;
    SearchManager searchManager;
    MenuInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_historial);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){ getSupportActionBar().setDisplayHomeAsUpEnabled(true); }
        setTitle("HISTORIAL DE VISITAS");

        rvReporteVisitas = findViewById(R.id.rvListadeVisita);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        rvReporteVisitas.setLayoutManager(layoutManager);

        oActividad = LogActividades_model.get(ManagerURI.getDirDb(),this, "");
        TextView txt = findViewById(R.id.idlblReportes);

        Actividad_Historial_Adapter ActividadAdapter = new Actividad_Historial_Adapter(oActividad, getBaseContext(), this);
        rvReporteVisitas.setAdapter(ActividadAdapter);
        findViewById(R.id.fabDeleteReport).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (oActividad.size()>0){
                    String mesaje =(oActividad.size()==1) ? "¿Desea eliminar el registros?" : "¿Desea eliminar los "+ oActividad.size() +" Registros?";
                    new AlertDialog.Builder(ReporteHistorialActivity.this)
                            .setMessage(mesaje)
                            .setCancelable(false)
                            .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    SQLiteHelper.ExecuteSQL(ManagerURI.getDirDb(), ReporteHistorialActivity.this,"DELETE FROM log_actividades");
                                    SQLiteHelper.ExecuteSQL(ManagerURI.getDirDb(), ReporteHistorialActivity.this,"DELETE FROM log_actividades_detalle");
                                    Toast.makeText(ReporteHistorialActivity.this, "Registros Eliminados.", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            })
                            .setNegativeButton("NO", null)
                            .show();
                }else{
                    Toast.makeText(ReporteHistorialActivity.this, "Bandeja vacia.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        if (oActividad.size()!=0){
            txt.setVisibility(TextView.INVISIBLE);
        }



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_busqueda, menu);
        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterData(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterData(newText);
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                filterData("");
                return false;
            }
        });


        return true;
    }
    public void filterData(String query) {
        query = query.toLowerCase(Locale.getDefault());
        ArrayList<Log_Actividades> newList = new ArrayList<>();
        if (query.isEmpty()){
            for(Log_Actividades oC:oActividad){
                newList.add(oC);
            }
        }else{
            //ArrayList<Articulo> newList = new ArrayList<>();
            for(Log_Actividades oC:oActividad){
                if (oC.getName().toLowerCase().contains(query)){
                    newList.add(oC);
                }
            }
        }
        rvReporteVisitas.setAdapter( new Actividad_Historial_Adapter(newList, getBaseContext(), this));
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

}
