package com.guma.desarrollo.gvm.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import com.bumptech.glide.Glide;
import com.guma.desarrollo.gvm.R;

public class AddFarmaciasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_farmacias);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){ getSupportActionBar().setDisplayHomeAsUpEnabled(true); }

        TabHost tabs = findViewById(android.R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec spec = tabs.newTabSpec("TB1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("INFORMACION GENERAL",null);
        tabs.addTab(spec);

        spec=tabs.newTabSpec("TB2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("MAS DATOS",null);
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

        CollapsingToolbarLayout collapser = findViewById(R.id.collapser);
        collapser.setTitle(" ");
        ImageView image =  findViewById(R.id.image_paralax);
        Glide.with(this)
                .load(R.drawable.bg)
                .centerCrop()
                .into(image);
    }
    public boolean onOptionsItemSelected(MenuItem item)    {
        int id = item.getItemId();
        switch (id) {
            case 16908332:
                finish();
                return true;
            case R.id.action_settings:
                showSnackBar("Se abren los ajustes");
                return true;
            case R.id.action_add:
                showSnackBar("Añadir a contactos");
                return true;
            case R.id.action_favorite:
                showSnackBar("Añadir a favoritos");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mn_add_farmacias_medicos, menu);
        return true;
    }
    private void showSnackBar(String msg) {
        Snackbar
                .make(findViewById(R.id.coordinator), msg, Snackbar.LENGTH_LONG)
                .show();
    }

}
