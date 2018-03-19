package com.guma.desarrollo.gvm.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import com.guma.desarrollo.gvm.MODEL.Clientes_model;
import com.guma.desarrollo.gvm.POJO.Cliente;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.adapters.ClientesAdapter;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListasMedicosFarmaciasActivity extends AppCompatActivity {
    Bundle bundle;
    String Str;
    private List<Cliente> oClientes;
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
        oClientes = Clientes_model.get(ManagerURI.getDirDb(),this);
        ClientesAdapter articulosAdapter = new ClientesAdapter(oClientes, getBaseContext(), this);
        recyclerViewClientes.setAdapter(articulosAdapter);

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
    public boolean onOptionsItemSelected(MenuItem item)    {
        if (item.getItemId() == 16908332){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_busqueda, menu);
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
        ArrayList<Cliente> newList = new ArrayList<>();
        if (query.isEmpty()){
            for(Cliente oC:oClientes){
                newList.add(oC);
            }
        }else{
            //ArrayList<Articulo> newList = new ArrayList<>();
            for(Cliente oC:oClientes){
                if (oC.getmNam().toLowerCase().contains(query)){
                    newList.add(oC);
                }
            }
        }
        recyclerViewClientes.setAdapter( new ClientesAdapter(newList, getBaseContext(), this));
    }

}
