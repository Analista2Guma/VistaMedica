package com.guma.desarrollo.gvm.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.guma.desarrollo.gvm.MODEL.Farmacias_model;
import com.guma.desarrollo.gvm.MODEL.Medicos_model;
import com.guma.desarrollo.gvm.POJO.Farmacias;
import com.guma.desarrollo.gvm.POJO.Medicos;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.TASK.TaskFarmacias;
import com.guma.desarrollo.gvm.TASK.TaskMedicos;
import com.guma.desarrollo.gvm.adapters.Farmacias_Adapter;
import com.guma.desarrollo.gvm.adapters.Medicos_Adapter;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListasMedicosFarmaciasActivity extends AppCompatActivity {
    Bundle bundle;
    String Str;
    private List<Farmacias> oFarmacias;
    private List<Medicos> oMedicos;
    private static final String TAG = "ClienteActivity";
    MenuInflater inflater;
    SearchManager searchManager;
    SearchView searchView;
    RecyclerView recyclerViewClientes;

    int CountRow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listas_medicos_farmacias);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bundle = getIntent().getExtras();
        if (getSupportActionBar() != null){ getSupportActionBar().setDisplayHomeAsUpEnabled(true); }
        Str = bundle.getString("Activity_list").toUpperCase();

        recyclerViewClientes = findViewById(R.id.clientesRecyclerView);
        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getBaseContext());
        linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewClientes.setLayoutManager(linearLayout);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Str.equals("F")){
                    startActivity(new Intent(ListasMedicosFarmaciasActivity.this, AddFarmaciasActivity.class));
                }else if (Str.equals("M")){
                    startActivity(new Intent(ListasMedicosFarmaciasActivity.this, AddMedicosActivity.class));
                }else{
                    Toast.makeText(ListasMedicosFarmaciasActivity.this, "Algo Salio mal", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

        if (Str.equals("F")){
            setTitle("LISTA DE FARMACIAS");
            LlenarFarmacias();
        }else if (Str.equals("M")){
            setTitle("LISTA DE MEDICOS");
            LlenarMedicos();
        }else{
            Toast.makeText(ListasMedicosFarmaciasActivity.this, "Algo Salio mal", Toast.LENGTH_SHORT).show();
        }

    }

    private void LlenarFarmacias() {
        oFarmacias = Farmacias_model.get(ManagerURI.getDirDb(),this,"");
        recyclerViewClientes.setAdapter(new Farmacias_Adapter(oFarmacias, getBaseContext(), this));
        CountRow = oFarmacias.size();
    }
    private void LlenarMedicos() {
        oMedicos = Medicos_model.get(ManagerURI.getDirDb(),this,"");
        recyclerViewClientes.setAdapter(new Medicos_Adapter(oMedicos, getBaseContext(), this));
        CountRow = oMedicos.size();
    }
    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();



    }
    public boolean onOptionsItemSelected(MenuItem item)    {
        int id = item.getItemId();
        switch (id) {
            case 16908332:
                finish();
                return true;
            case R.id.action_sync:
                if (ManagerURI.isOnlinea(this)){
                    if (Str.equals("F")){
                        recyclerViewClientes.setAdapter( new Farmacias_Adapter(new ArrayList<Farmacias>(), getBaseContext(), this));
                        new TaskFarmacias(this).execute(0);
                        LlenarFarmacias();
                    }else if (Str.equals("M")){
                        recyclerViewClientes.setAdapter( new Medicos_Adapter(new ArrayList<Medicos>(), getBaseContext(), this));
                        new TaskMedicos(this).execute(0);
                        LlenarMedicos();
                    }else{
                        Toast.makeText(ListasMedicosFarmaciasActivity.this, "Algo Salio mal", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    new AlertDialog.Builder(this)
                            .setTitle("Alerta")
                            .setMessage("No se obtuvo conexión con el servidor.")
                            .setCancelable(false)
                            .setPositiveButton("OK",null)
                            .show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_busqueda_farmacias_medicos
                , menu);
        MenuItem shareItem = menu.findItem(R.id.action_sync);
       // if (CountRow ==0){shareItem.setVisible(false);}

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
        if (Str.equals("F")){
            ArrayList<Farmacias> newList = new ArrayList<>();
            if (query.isEmpty()){

                for(Farmacias oC:oFarmacias){
                    newList.add(oC);
                }
            }else{
                for(Farmacias oC:oFarmacias){
                    if (oC.getmNFR().toLowerCase().contains(query)){
                        newList.add(oC);
                    }
                }
            }
            recyclerViewClientes.setAdapter( new Farmacias_Adapter(newList, getBaseContext(), this));
        }else if (Str.equals("M")){
            ArrayList<Medicos> newList = new ArrayList<>();
            if (query.isEmpty()){

                for(Medicos oC:oMedicos){
                    newList.add(oC);
                }
            }else{
                for(Medicos oC:oMedicos){
                    if (oC.getM01().toLowerCase().contains(query)){
                        newList.add(oC);
                    }
                }
            }
            recyclerViewClientes.setAdapter( new Medicos_Adapter(newList, getBaseContext(), this));
        }else{
            Toast.makeText(ListasMedicosFarmaciasActivity.this, "Algo Salio mal", Toast.LENGTH_SHORT).show();
        }


    }

}
