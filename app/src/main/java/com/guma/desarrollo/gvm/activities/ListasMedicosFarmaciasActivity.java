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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.guma.desarrollo.gvm.MODEL.Clientes_model;
import com.guma.desarrollo.gvm.MODEL.Farmacias_model;
import com.guma.desarrollo.gvm.POJO.Cliente;
import com.guma.desarrollo.gvm.POJO.Farmacias;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.TASK.TaskDownload;
import com.guma.desarrollo.gvm.TASK.TaskFarmacias;
import com.guma.desarrollo.gvm.adapters.ClientesAdapter;
import com.guma.desarrollo.gvm.adapters.Farmacias_Adapter;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListasMedicosFarmaciasActivity extends AppCompatActivity {
    Bundle bundle;
    String Str;
    private List<Farmacias> oClientes;
    private static final String TAG = "ClienteActivity";
    MenuInflater inflater;
    SearchManager searchManager;
    SearchView searchView;
    RecyclerView recyclerViewClientes;
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



        Llenar();

        setTitle(Str);
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
            }
        });
    }

    private void Llenar() {
        oClientes = Farmacias_model.get(ManagerURI.getDirDb(),this);
        Farmacias_Adapter articulosAdapter = new Farmacias_Adapter(oClientes, getBaseContext(), this);
        recyclerViewClientes.setAdapter(articulosAdapter);
    }

    public boolean onOptionsItemSelected(MenuItem item)    {
        int id = item.getItemId();
        switch (id) {
            case 16908332:
                finish();
                return true;
            case R.id.action_sync:
                if (ManagerURI.isOnlinea(this)){

                    recyclerViewClientes.setAdapter( new Farmacias_Adapter(new ArrayList<Farmacias>(), getBaseContext(), this));
                    new TaskFarmacias(this).execute(0);
                    Llenar();

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
        inflater.inflate(R.menu.menu_busqueda_farmacias_medicos, menu);
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
        ArrayList<Farmacias> newList = new ArrayList<>();
        if (query.isEmpty()){
            for(Farmacias oC:oClientes){
                newList.add(oC);
            }
        }else{
            //ArrayList<Articulo> newList = new ArrayList<>();
            for(Farmacias oC:oClientes){
                if (oC.getmNFR().toLowerCase().contains(query)){
                    newList.add(oC);
                }
            }
        }
        recyclerViewClientes.setAdapter( new Farmacias_Adapter(newList, getBaseContext(), this));
    }

}
