package com.guma.desarrollo.gvm.activities;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.guma.desarrollo.gvm.LIB.Calendario;
import com.guma.desarrollo.gvm.MODEL.Farmacias_model;
import com.guma.desarrollo.gvm.POJO.Farmacias;
import com.guma.desarrollo.gvm.R;

import java.util.ArrayList;
import java.util.Calendar;

public class AddFarmaciasActivity extends AppCompatActivity {
    private static final String CERO = "0";
    private static final String BARRA = "/";
    public final Calendar c = Calendar.getInstance();
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    EditText etPlannedDate;
    EditText tvNombre_Farmacia,tvNombre_propietario,tvDireccion,tvTef_Farmacia,tvCel_duenna;
    EditText tvHorario_Atencion,tvReponsable_compra,tvCel_Resp_Compra,tvCantidad_dependiente;
    EditText tvPotencial_mensual,tv_Dias,tvResponsables_Vencidos,tvResponsables_canjes,tvResponsable_mostrador;
    CheckBox tv_CheckBox01,tv_CheckBox02,tv_CheckBox03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_farmacias);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){ getSupportActionBar().setDisplayHomeAsUpEnabled(true); }

        tvNombre_Farmacia = findViewById(R.id.item_frm_nameFarmacia);
        tvNombre_propietario = findViewById(R.id.item_frm_namePropietario);
        tvDireccion= findViewById(R.id.item_frm_Direccion);

        tvTef_Farmacia= findViewById(R.id.item_frm_TelfFarmacia);
        tvCel_duenna= findViewById(R.id.item_frm_Celularduenno);
        tvHorario_Atencion= findViewById(R.id.item_frm_HtsAtencion);
        tvReponsable_compra= findViewById(R.id.item_frm_Resp_Compra);
        tvCel_Resp_Compra= findViewById(R.id.item_frm_Cel_Resp_Compra);
        tvCantidad_dependiente= findViewById(R.id.item_frm_Cant_Dependiete);

        tvPotencial_mensual= findViewById(R.id.item_frm_potencial_mensual);
        tv_Dias= findViewById(R.id.item_frm_Dias);
        tvResponsables_Vencidos= findViewById(R.id.item_frm_Responsable_vencidos);
        tvResponsables_canjes= findViewById(R.id.item_frm_Responsables_canjes);
        tvResponsable_mostrador= findViewById(R.id.item_frm_dependientes_mostrador);

        tv_CheckBox01 = findViewById(R.id.id_CheckBox01);
        tv_CheckBox02 = findViewById(R.id.id_CheckBox02);
        tv_CheckBox03 = findViewById(R.id.id_CheckBox03);


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

        etPlannedDate = findViewById(R.id.item_frm_FechaAniversario);
        etPlannedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerFecha();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item)    {
        int id = item.getItemId();
        switch (id) {
            case 16908332:
                finish();
                return true;
            case R.id.action_settings:
                return true;
            case R.id.action_add:
                Save_Farmacia();
                return true;
            case R.id.action_favorite:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void Save_Farmacia() {

        Integer sCheckBox01 = ((tv_CheckBox01.isChecked()) ? 1 : 0);
        Integer sCheckBox02 = ((tv_CheckBox02.isChecked()) ? 1 : 0);
        Integer sCheckBox03 = ((tv_CheckBox03.isChecked()) ? 1 : 0);


        ArrayList<Farmacias> aListFarmacias = new ArrayList<>();
        Farmacias tmp = new Farmacias();

        tmp.setmUID("0");
        tmp.setmNFR(tvNombre_Farmacia.getText().toString());
        tmp.setmNPR(tvNombre_propietario.getText().toString());
        tmp.setmDIR(tvDireccion.getText().toString());
        tmp.setmFAN(etPlannedDate.getText().toString());
        tmp.setmTFR(tvTef_Farmacia.getText().toString());
        tmp.setmTFP(tvCel_duenna.getText().toString());
        tmp.setmHAT(tvHorario_Atencion.getText().toString());
        tmp.setmRCP(tvReponsable_compra.getText().toString());
        tmp.setmTRC(tvCel_Resp_Compra.getText().toString());
        tmp.setmCDP(tvCantidad_dependiente.getText().toString());
        tmp.setmPCP(tvPotencial_mensual.getText().toString());
        tmp.setmDPF(tv_Dias.getText().toString());
        tmp.setmRVC(tvResponsables_Vencidos.getText().toString());
        tmp.setmRCJ(tvResponsables_canjes.getText().toString());
        tmp.setmNDM(tvResponsable_mostrador.getText().toString());
        tmp.setmPPP(sCheckBox01);
        tmp.setmEBD(sCheckBox02);
        tmp.setmPIP(sCheckBox03);

        aListFarmacias.add(tmp);



        Farmacias_model.Save(this,aListFarmacias,"New");

        new AlertDialog.Builder(this).setTitle("Notificación").setMessage("Guardado con exito").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).show();


        
    }

    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final int mesActual = month + 1;
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                etPlannedDate.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
            }
        },anio, mes, dia);
        recogerFecha.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mn_add_farmacias_medicos, menu);
        return true;
    }


}
