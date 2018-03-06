package com.guma.desarrollo.gvm.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.TASK.TaskDownload;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private CardView dashboardCurrentMonth;
    private CardView dashboardLastMonths;
    private CardView dashboardClientes;
    private CardView dashboardArticulos;
    private SharedPreferences preferences;
    boolean checked;
    private SharedPreferences.Editor editor;
    String user;
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
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);

        checked = preferences.getBoolean("pref",false);
        user = preferences.getString("Ruta","");


        final TextView txt_last_update = findViewById(R.id.txtLastUpdate);
        final TextView txt_name_user= headerView.findViewById(R.id.drw_name_user);
        final TextView txt_name_ruta= headerView.findViewById(R.id.drw_name_ruta);





        txt_last_update.setText("Actualizado hasta:" + preferences.getString("lstDownload","00/00/0000"));
        txt_name_user.setText("MARYAN ADAN ESPINOZA");
        txt_name_ruta.setText(user);


        dashboardCurrentMonth =  findViewById(R.id.dashboardCurrentMonth);
        dashboardLastMonths = findViewById(R.id.dashboardLastMonths);
        dashboardClientes = findViewById(R.id.dashboardClientes);
        dashboardArticulos = findViewById(R.id.dashboardArticulos);
        dashboardCurrentMonth.setOnClickListener(this);
        dashboardLastMonths.setOnClickListener(this);
        dashboardClientes.setOnClickListener(this);
        dashboardArticulos.setOnClickListener(this);
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TaskDownload(DashboardActivity.this).execute(0);
                txt_last_update.setText(preferences.getString("lstDownload","00/00/0000"));

            }
        });

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
        int id = item.getItemId();

        if (id == R.id.drawerMenuItem1) {

        } else if (id == R.id.drawerCerrarSession) {
            new AlertDialog.Builder(this)
                    .setMessage("Seguro que decea Cerrar Sessión?")
                    .setCancelable(false)
                    .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Log.d("", "onClick: " + preferences.getBoolean("pref",false));
                            checked = false;
                            editor.putBoolean("isLogin", false);
                            editor.apply();
                            //Toast.makeText(getBaseContext(), "SESSIÓN CERRADA", Toast.LENGTH_SHORT).show();
                            finish();
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
