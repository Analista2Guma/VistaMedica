package com.guma.desarrollo.gvm.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.TASK.TaskDownload;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private CardView dashboardCurrentMonth;
    private CardView dashboardLastMonths;
    private CardView dashboardClientes;
    private CardView dashboardArticulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        dashboardCurrentMonth =  findViewById(R.id.dashboardCurrentMonth);
        dashboardLastMonths = findViewById(R.id.dashboardLastMonths);
        dashboardClientes = findViewById(R.id.dashboardClientes);
        dashboardArticulos = findViewById(R.id.dashboardArticulos);
        dashboardCurrentMonth.setOnClickListener(this);
        dashboardLastMonths.setOnClickListener(this);
        dashboardClientes.setOnClickListener(this);
        dashboardArticulos.setOnClickListener(this);
        new TaskDownload(this).execute(0);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.drawerMenuItem1) {

        } else if (id == R.id.drawerCerrarSession) {
            new AlertDialog.Builder(this)
                    .setMessage("Seguro que decea Cerrar Sessión?")
                    .setCancelable(false)
                    .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(getBaseContext(), "SESSIÓN CERRADA", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("NO", null)
                    .show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.dashboardArticulos) {
            startActivity(new Intent(this, ArticulosActivity.class));
        } else if(id == R.id.dashboardClientes) {
            startActivity(new Intent(this, ClientesActivity.class));
        } else if(id == R.id.dashboardCurrentMonth) {
            startActivity(new Intent(this, MesActualActivity.class));
        } else if(id == R.id.dashboardLastMonths) {
            startActivity(new Intent(this, UltimosMesesActivity.class));
        }
    }
}
