package com.guma.desarrollo.gvm.activities;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import com.guma.desarrollo.gvm.MODEL.Clientes_model;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.adapters.ClientesAdapter;
import com.guma.desarrollo.gvm.POJO.Cliente;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ClientesActivity extends AppCompatActivity{
    private List<Cliente> oClientes;
    private static final String TAG = "ClienteActivity";
    MenuInflater inflater;
    SearchManager searchManager;
    SearchView searchView;
    RecyclerView recyclerViewClientes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        recyclerViewClientes = findViewById(R.id.clientesRecyclerView);
        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getBaseContext());
        linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewClientes.setLayoutManager(linearLayout);
        TextView txt = findViewById(R.id.lblItem);
        oClientes = Clientes_model.get(ManagerURI.getDirDb(),this);
        if (oClientes.size()!=0){
            txt.setVisibility(TextView.INVISIBLE);

        }
        ClientesAdapter articulosAdapter = new ClientesAdapter(oClientes, getBaseContext(), this);
        recyclerViewClientes.setAdapter(articulosAdapter);
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
