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
import com.guma.desarrollo.gvm.adapters.ArticulosAdapter;
import com.guma.desarrollo.gvm.POJO.Articulo;

import java.util.ArrayList;

public class ArticulosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulos);

        RecyclerView recyclerViewArticulos = findViewById(R.id.articulosRecyclerView);

        LinearLayoutManager linearLayout = new LinearLayoutManager(getBaseContext());
        linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewArticulos.setLayoutManager(linearLayout);

        ArticulosAdapter articulosAdapter = new ArticulosAdapter(pruebaArticulos(), getBaseContext(), this);
        recyclerViewArticulos.setAdapter(articulosAdapter);

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

    public ArrayList<Articulo> pruebaArticulos() {
        ArrayList<Articulo> articulos = new ArrayList<>();
        articulos.add(new Articulo("Articulo 1", "000001", 50));
        articulos.add(new Articulo("Articulo 2", "000002", 50));
        articulos.add(new Articulo("Articulo 3", "000003", 50));
        articulos.add(new Articulo("Articulo 4", "000004", 50));
        return articulos;
    }
}
