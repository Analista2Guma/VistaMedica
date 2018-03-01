package com.guma.desarrollo.gvm.activities;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.adapters.ClientesAdapter;
import com.guma.desarrollo.gvm.POJO.Cliente;

import java.util.ArrayList;

public class ClientesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);


        RecyclerView recyclerViewClientes = findViewById(R.id.clientesRecyclerView);

        LinearLayoutManager linearLayout = new LinearLayoutManager(getBaseContext());
        linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewClientes.setLayoutManager(linearLayout);

        ClientesAdapter clientesAdapter = new ClientesAdapter(pruebaClientes(), getBaseContext(), this);
        recyclerViewClientes.setAdapter(clientesAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.menu_busqueda, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    public ArrayList<Cliente> pruebaClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("Cliente 1", "000001"));
        clientes.add(new Cliente("Cliente 2", "000002"));
        clientes.add(new Cliente("Cliente 3", "000003"));
        clientes.add(new Cliente("Cliente 4", "000004"));
        clientes.add(new Cliente("Cliente 5", "000005"));
        return clientes;
    }
}
