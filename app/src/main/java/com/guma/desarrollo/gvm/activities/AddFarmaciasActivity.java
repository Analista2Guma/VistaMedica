package com.guma.desarrollo.gvm.activities;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
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
import com.guma.desarrollo.gvm.DATABASE.SQLiteHelper;
import com.guma.desarrollo.gvm.LIB.Calendario;
import com.guma.desarrollo.gvm.LIB.Clock;
import com.guma.desarrollo.gvm.MODEL.Farmacias_model;
import com.guma.desarrollo.gvm.MODEL.Llaves_model;
import com.guma.desarrollo.gvm.POJO.Farmacias;
import com.guma.desarrollo.gvm.POJO.Llaves;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.TASK.TaskDeleteFarmacias;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddFarmaciasActivity extends AppCompatActivity {
    private static final String CERO = "0";
    private static final String BARRA = "/";
    private static final String TAG = "AddFarmacias:";
    public final Calendar c = Calendar.getInstance();
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);
    String user ,IDFarmacias,mnDelete;
    boolean Accion = false;
    private SharedPreferences preferences;
    TextView etPlannedDate;
    TextView tvNombre_Farmacia,tvNombre_propietario,tvDireccion,tvTef_Farmacia,tvCel_duenna;

    EditText tvHorario_Atencion,tvReponsable_compra,tvCel_Resp_Compra,tvCantidad_dependiente;
    EditText tvPotencial_mensual,tv_Dias,tvResponsables_Vencidos,tvResponsables_canjes,tvResponsable_mostrador;
    CheckBox tv_CheckBox01,tv_CheckBox02,tv_CheckBox03,tv_CheckBox04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_farmacias);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){ getSupportActionBar().setDisplayHomeAsUpEnabled(true); }

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        user =preferences.getString("Ruta","");

        etPlannedDate = findViewById(R.id.item_frm_FechaAniversario);

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
        tv_CheckBox04 = findViewById(R.id.id_CheckBox04);



        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Accion = true;
            IDFarmacias = bundle.getString("UID");
            mnDelete = bundle.getString("Accion","");
            List<Farmacias> Row = Farmacias_model.get(ManagerURI.getDirDb(),this,IDFarmacias);

            etPlannedDate.setText(Row.get(0).getmFAN().toString());

            tvNombre_Farmacia.setText(Row.get(0).getmNFR().toString());
            tvNombre_propietario.setText(Row.get(0).getmNPR().toString());
            tvDireccion.setText(Row.get(0).getmDIR().toString());

            tvTef_Farmacia.setText(Row.get(0).getmTFR().toString());
            tvCel_duenna.setText(Row.get(0).getmTFP().toString());
            tvHorario_Atencion.setText(Row.get(0).getmHAT().toString());

            tvReponsable_compra.setText(Row.get(0).getmRCP().toString());
            tvCel_Resp_Compra.setText(Row.get(0).getmTRC().toString());
            tvCantidad_dependiente.setText(Row.get(0).getmCDP().toString());
            tvPotencial_mensual.setText(Row.get(0).getmPCP().toString());

            tv_Dias.setText(Row.get(0).getmDPF().toString());
            tvResponsables_Vencidos.setText(Row.get(0).getmRVC().toString());
            tvResponsables_canjes.setText(Row.get(0).getmRCJ().toString());
            tvResponsable_mostrador.setText(Row.get(0).getmNDM().toString());

            if(Row.get(0).getmPPP()==1){tv_CheckBox01.setChecked(true);}
            if(Row.get(0).getmEBD()==1){tv_CheckBox02.setChecked(true);}
            if(Row.get(0).getmPIP()==1){tv_CheckBox03.setChecked(true);}
            if(Row.get(0).getmCCO()==1){tv_CheckBox04.setChecked(true);}



        }



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
                .load(R.drawable.bg2)
                .centerCrop()
                .into(image);


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
                if (Accion){
                    Update_Farmacia();
                }else{

                    if(tvNombre_Farmacia.getText().toString().isEmpty()){
                        tvNombre_Farmacia.requestFocus();
                        tvNombre_Farmacia.setError("This field can not be blank");
                    }else {
                        if(tvNombre_propietario.getText().toString().isEmpty()){
                            tvNombre_propietario.requestFocus();
                            tvNombre_propietario.setError("This field can not be blank");
                        }else{
                            if(tvDireccion.getText().toString().isEmpty()){
                                tvDireccion.requestFocus();
                                tvDireccion.setError("This field can not be blank");
                            }else{
                                if(tvTef_Farmacia.getText().toString().isEmpty()){
                                    tvTef_Farmacia.requestFocus();
                                    tvTef_Farmacia.setError("This field can not be blank");
                                }else{
                                    Save_Farmacia();
                                }
                            }
                        }
                    }


                }
                return true;
            case R.id.action_delete:
                new AlertDialog.Builder(this)
                        .setMessage("¿Seguro que decea borrar el registro?")
                        .setCancelable(false)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //new TaskDeleteFarmacias(AddFarmaciasActivity.this).execute(IDFarmacias);
                                Farmacias_model.Delete(ManagerURI.getDirDb(),AddFarmaciasActivity.this,IDFarmacias);
                                finish();
                            }
                        })
                        .setNegativeButton("NO", null)
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void Update_Farmacia() {


        Integer sCheckBox01 = ((tv_CheckBox01.isChecked()) ? 1 : 0);
        Integer sCheckBox02 = ((tv_CheckBox02.isChecked()) ? 1 : 0);
        Integer sCheckBox03 = ((tv_CheckBox03.isChecked()) ? 1 : 0);
        Integer sCheckBox04 = ((tv_CheckBox04.isChecked()) ? 1 : 0);
        ArrayList<Farmacias> aListFarmacias = new ArrayList<>();
        Farmacias tmp = new Farmacias();
        tmp.setmUID(IDFarmacias);
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
        tmp.setmCCO(sCheckBox04);

        aListFarmacias.add(tmp);


        Farmacias_model.Update(this,aListFarmacias);
        //SQLiteHelper.ExecuteSQL(ManagerURI.getDirDb(),this, "UPDATE Farmacias SET Estado = 0 WHERE idFarmacia='" + IDFarmacias + "'");




        new AlertDialog.Builder(this).setTitle("Notificación").setMessage("Guardado con exito").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).show();



    }

    private void Save_Farmacia() {
        Integer cntFarmacias=0;

        for (Llaves ll: Llaves_model.get(ManagerURI.getDirDb(),this)) {
            cntFarmacias = Integer.valueOf(ll.getmFar());
        }

        cntFarmacias++;

        String COD = user.concat("-F").concat(String.valueOf(cntFarmacias));

        Llaves_model.updtID(ManagerURI.getDirDb(),this,cntFarmacias,user,"FARMACIAS");

        Integer sCheckBox01 = ((tv_CheckBox01.isChecked()) ? 1 :    0);
        Integer sCheckBox02 = ((tv_CheckBox02.isChecked()) ? 1 : 0);
        Integer sCheckBox03 = ((tv_CheckBox03.isChecked()) ? 1 : 0);
        Integer sCheckBox04 = ((tv_CheckBox04.isChecked()) ? 1 : 0);

        ArrayList<Farmacias> aListFarmacias = new ArrayList<>();
        Farmacias tmp = new Farmacias();

        tmp.setmUID(COD);
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
        tmp.setmCCO(sCheckBox04);
        tmp.setRuta(user);
        aListFarmacias.add(tmp);


        Farmacias_model.Save(this,aListFarmacias,"New");
       // SQLiteHelper.ExecuteSQL(ManagerURI.getDirDb(),this, "UPDATE Farmacias SET Estado = 0 WHERE idFarmacia='" + COD + "'");



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

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mn_add_farmacias_medicos, menu);
        MenuItem shareItem = menu.findItem(R.id.action_delete);
        if (mnDelete ==null){shareItem.setVisible(false);}
        return true;
    }


}
