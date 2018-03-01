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

import com.guma.desarrollo.gvm.MODEL.Articulos_model;
import com.guma.desarrollo.gvm.POJO.Cliente;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.adapters.ArticulosAdapter;
import com.guma.desarrollo.gvm.POJO.Articulo;
import com.guma.desarrollo.gvm.adapters.ClientesAdapter;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ArticulosActivity extends AppCompatActivity {
    private static final String TAG = "ArticulosActivity";
    private List<Articulo> oArticulo;
    MenuInflater inflater;
    SearchManager searchManager;
    SearchView searchView;
    ArticulosAdapter articulosAdapter;
    RecyclerView recyclerViewArticulos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulos);
        recyclerViewArticulos = findViewById(R.id.articulosRecyclerView);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getBaseContext());
        linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewArticulos.setLayoutManager(linearLayout);
        oArticulo = Articulos_model.get(ManagerURI.getDirDb(),this);
        articulosAdapter = new ArticulosAdapter(oArticulo, getBaseContext(), this);
        recyclerViewArticulos.setAdapter(articulosAdapter);
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
        ArrayList<Articulo> newList = new ArrayList<>();
        if (query.isEmpty()){
            for(Articulo oC:oArticulo){
                newList.add(oC);
            }
        }else{
            //ArrayList<Articulo> newList = new ArrayList<>();
            for(Articulo oC:oArticulo){
                if (oC.getmNam().toLowerCase().contains(query)){
                    newList.add(oC);
                }
            }
        }
        recyclerViewArticulos.setAdapter( new ArticulosAdapter(newList, getBaseContext(), this));
    }

}
