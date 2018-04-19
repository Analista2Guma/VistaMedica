package com.guma.desarrollo.gvm.activities;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CollapsingToolbarLayout;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.guma.desarrollo.gvm.LIB.Clock;
import com.guma.desarrollo.gvm.MODEL.Especialidades_model;
import com.guma.desarrollo.gvm.MODEL.Llaves_model;
import com.guma.desarrollo.gvm.MODEL.Medicos_model;
import com.guma.desarrollo.gvm.POJO.Especialidades;
import com.guma.desarrollo.gvm.POJO.Llaves;
import com.guma.desarrollo.gvm.POJO.Medicos;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.TASK.TaskDeleteMedicos;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddMedicosActivity extends AppCompatActivity {
    private SharedPreferences preferences;

    List<Especialidades> lst;

    String user ,IDMedico,mnDelete;
    boolean Accion = false;
    private static final String TAG = "AddMedicosActivity";
    EditText et01,et02,et03,et04,et05,et06,et07,et08,et09,et10;
    EditText et11,et12,et13,et14,et16,et17,et18,et19,et20;
    EditText et21,et22,et23,et24,et25,et26,et27,et28,et29,et30;
    String[] myarray;
    CheckBox et15;
    Spinner spnn1;
    CheckBox tv_CheckBox01;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){ getSupportActionBar().setDisplayHomeAsUpEnabled(true); }
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        user =preferences.getString("Ruta","");

        spnn1 = findViewById(R.id.spnn1);


        ArrayAdapter<CharSequence> adptr1 = ArrayAdapter.createFromResource(this,R.array.Especialidades, android.R.layout.simple_spinner_item);
        adptr1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        int i=0;
        lst = Especialidades_model.get(ManagerURI.getDirDb(),this);
        myarray = new String[lst.size()];
        for (Especialidades lt : lst){
            myarray[i] = lt.getmName().toUpperCase();
            i++;
        }
        ArrayAdapter<String> adapter =new ArrayAdapter<>(AddMedicosActivity.this,android.R.layout.simple_spinner_item, myarray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnn1.setAdapter(adapter);

        tv_CheckBox01 = findViewById(R.id.Idet15);

        et01 = findViewById(R.id.Idet01);
        et02 = findViewById(R.id.Idet02);
        et03 = findViewById(R.id.Idet03);
        et04 = findViewById(R.id.Idet04);
        et05 = findViewById(R.id.Idet05);

        et06 = findViewById(R.id.Idet06);
        et07 = findViewById(R.id.Idet07);
        et08 = findViewById(R.id.Idet08);
        et09 = findViewById(R.id.Idet09);
        et10 = findViewById(R.id.Idet10);

        et11 = findViewById(R.id.Idet11);
        et12 = findViewById(R.id.Idet12);
        et13 = findViewById(R.id.Idet13);
        et14 = findViewById(R.id.Idet14);
        et15 = findViewById(R.id.Idet15);

        et16 = findViewById(R.id.Idet16);
        et17 = findViewById(R.id.Idet17);
        et18 = findViewById(R.id.Idet18);
        et19 = findViewById(R.id.Idet19);
        et20 = findViewById(R.id.Idet20);

        et21 = findViewById(R.id.Idet21);
        et22 = findViewById(R.id.Idet22);
        et23 = findViewById(R.id.Idet23);
        et24 = findViewById(R.id.Idet24);
        et25 = findViewById(R.id.Idet25);

        et26 = findViewById(R.id.Idet26);

        et27 = findViewById(R.id.Idet27);
        et28 = findViewById(R.id.Idet28);
        et29 = findViewById(R.id.Idet29);
        et30 = findViewById(R.id.Idet30);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Accion = true;
            IDMedico = bundle.getString("UID");
            mnDelete = bundle.getString("Accion","");

            List<Medicos> Row = Medicos_model.get(ManagerURI.getDirDb(),this,IDMedico);
            et01.setText(Row.get(0).getM01().toString());
            et02.setText(Row.get(0).getM02().toString());
            et03.setText(Row.get(0).getM03().toString());
            et04.setText(Row.get(0).getM04().toString());
            et05.setText(Row.get(0).getM05().toString());
            et06.setText(Row.get(0).getM06().toString());
            et07.setText(Row.get(0).getM07().toString());
            et08.setText(Row.get(0).getM08().toString());
            et09.setText(Row.get(0).getM09().toString());
            et10.setText(Row.get(0).getM010().toString());

            et11.setText(Row.get(0).getM011().toString());
            et12.setText(Row.get(0).getM012().toString());
            et13.setText(Row.get(0).getM013().toString());
            et14.setText(Row.get(0).getM014().toString());

            et16.setText(Row.get(0).getM016().toString());
            et17.setText(Row.get(0).getM017().toString());
            et18.setText(Row.get(0).getM018().toString());
            et19.setText(Row.get(0).getM019().toString());
            et20.setText(Row.get(0).getM020().toString());

            et21.setText(Row.get(0).getM21().toString());
            et22.setText(Row.get(0).getM22().toString());
            et23.setText(Row.get(0).getM23().toString());
            et24.setText(Row.get(0).getM24().toString());
            et25.setText(Row.get(0).getM25().toString());
            et26.setText(Row.get(0).getM26().toString());
            et27.setText(Row.get(0).getM27().toString());
            et28.setText(Row.get(0).getM28().toString());
            et29.setText(Row.get(0).getM29().toString());
            et30.setText(Row.get(0).getM30().toString());

            if(Row.get(0).getM31()==1){tv_CheckBox01.setChecked(true);}

            spnn1.setSelection(getItemindexOf(Row.get(0).getM32()));

        }
        CollapsingToolbarLayout collapser = findViewById(R.id.collapser);
        collapser.setTitle(" ");
        ImageView image =  findViewById(R.id.image_paralax);

        Glide.with(this)
                .load(R.drawable.bg2)
                .centerCrop()
                .into(image);
        et02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateMedico();
            }
        });
        et25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateAsistente();
            }
        });
    }
    public Integer getItemIndexById(String id) {
        for (Especialidades item : lst) {
            if (item.getmName().toUpperCase().contains(id)){
                return Integer.valueOf(item.getmUID());
            }
        }
        return 0;
    }
    public int getItemindexOf(Integer id) {
        for (Especialidades item : lst) {
            if(item.getmUID().toString().equals(id.toString())){
                return lst.indexOf(item);
            }
        }
        return 0;
    }
    private void DateMedico(){

        Calendar c = Calendar.getInstance();
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final int mesActual = month + 1;
                String diaFormateado = (dayOfMonth < 10)? "0" + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10)? "0" + String.valueOf(mesActual):String.valueOf(mesActual);

                et02.setText( diaFormateado + "/" + mesFormateado + "/" + year);
            }
        },c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

        recogerFecha.show();


    }
    private void DateAsistente(){

        Calendar c = Calendar.getInstance();
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final int mesActual = month + 1;
                String diaFormateado = (dayOfMonth < 10)? "0" + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10)? "0" + String.valueOf(mesActual):String.valueOf(mesActual);

                et25.setText( diaFormateado + "/" + mesFormateado + "/" + year);
            }
        },c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

        recogerFecha.show();




    }
    public boolean onOptionsItemSelected(MenuItem item)    {
        int id = item.getItemId();
        switch (id) {
            case 16908332:
                finish();
                return true;
            case R.id.action_add:
                if (Accion){
                    Update_Medico();
                }else{
                    if(et01.getText().toString().isEmpty()){
                        et01.requestFocus();
                        et01.setError("Este campo no puede estar en blanco");
                    }else {
                        if(et03.getText().toString().isEmpty()){
                            et03.requestFocus();
                            et03.setError("Este campo no puede estar en blanco");
                        }else{
                            if(et04.getText().toString().isEmpty()){
                                et04.requestFocus();
                                et04.setError("Este campo no puede estar en blanco");
                            }else{
                                Save_Medico();
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
                                new TaskDeleteMedicos(AddMedicosActivity.this).execute(IDMedico);
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

    private void Save_Medico() {


        String COD = user.concat("-M").concat(Clock.getIDs());
        ArrayList<Medicos> aLista = new ArrayList<>();
        Medicos mdc = new Medicos();

        Integer sCheckBox01 = ((tv_CheckBox01.isChecked()) ? 1 : 0);
        Integer Select = getItemIndexById(spnn1.getSelectedItem().toString());

        mdc.setmUID(COD);
        mdc.setM01(((TextUtils.isEmpty(et01.getText().toString())) ? "" : et01.getText().toString()));
        mdc.setM02(((TextUtils.isEmpty(et02.getText().toString())) ? "" : et02.getText().toString()));
        mdc.setM03(((TextUtils.isEmpty(et03.getText().toString())) ? "" : et03.getText().toString()));
        mdc.setM04(((TextUtils.isEmpty(et04.getText().toString())) ? "" : et04.getText().toString()));
        mdc.setM05(((TextUtils.isEmpty(et05.getText().toString())) ? "" : et05.getText().toString()));

        mdc.setM06(((TextUtils.isEmpty(et06.getText().toString())) ? "" : et06.getText().toString()));
        mdc.setM07(((TextUtils.isEmpty(et07.getText().toString())) ? "" : et07.getText().toString()));
        mdc.setM08(((TextUtils.isEmpty(et08.getText().toString())) ? "" : et08.getText().toString()));
        mdc.setM09(((TextUtils.isEmpty(et09.getText().toString())) ? "" : et09.getText().toString()));


        mdc.setM010(((TextUtils.isEmpty(et10.getText().toString())) ? "" : et10.getText().toString()));
        mdc.setM011(((TextUtils.isEmpty(et11.getText().toString())) ? "" : et11.getText().toString()));
        mdc.setM012(((TextUtils.isEmpty(et12.getText().toString())) ? "" : et12.getText().toString()));
        mdc.setM013(((TextUtils.isEmpty(et13.getText().toString())) ? "" : et13.getText().toString()));
        mdc.setM014(((TextUtils.isEmpty(et14.getText().toString())) ? "" : et14.getText().toString()));
        mdc.setM016(((TextUtils.isEmpty(et16.getText().toString())) ? "" : et16.getText().toString()));
        mdc.setM017(((TextUtils.isEmpty(et17.getText().toString())) ? "" : et17.getText().toString()));
        mdc.setM018(((TextUtils.isEmpty(et18.getText().toString())) ? "" : et18.getText().toString()));
        mdc.setM019(((TextUtils.isEmpty(et19.getText().toString())) ? "" : et19.getText().toString()));
        mdc.setM020(((TextUtils.isEmpty(et20.getText().toString())) ? "" : et20.getText().toString()));
        mdc.setM21(((TextUtils.isEmpty(et21.getText().toString())) ? "" : et21.getText().toString()));
        mdc.setM22(((TextUtils.isEmpty(et22.getText().toString())) ? "" : et22.getText().toString()));
        mdc.setM23(((TextUtils.isEmpty(et23.getText().toString())) ? "" : et23.getText().toString()));
        mdc.setM24(((TextUtils.isEmpty(et24.getText().toString())) ? "" : et24.getText().toString()));
        mdc.setM25(((TextUtils.isEmpty(et25.getText().toString())) ? "" : et25.getText().toString()));
        mdc.setM26(((TextUtils.isEmpty(et26.getText().toString())) ? "" : et26.getText().toString()));
        mdc.setM27(((TextUtils.isEmpty(et27.getText().toString())) ? "" : et27.getText().toString()));
        mdc.setM28(((TextUtils.isEmpty(et28.getText().toString())) ? "" : et28.getText().toString()));
        mdc.setM29(((TextUtils.isEmpty(et29.getText().toString())) ? "" : et29.getText().toString()));
        mdc.setM30(((TextUtils.isEmpty(et30.getText().toString())) ? "" : et30.getText().toString()));



        mdc.setM31(sCheckBox01);
        mdc.setM32(Select);
        mdc.setmRuta(user);

        aLista.add(mdc);
        Medicos_model.Save(this,aLista,"New");

        new AlertDialog.Builder(this).setTitle("Notificación").setMessage("Guardado con exito").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).show();
    }

    private void Update_Medico() {
        ArrayList<Medicos> aLista = new ArrayList<>();
        Medicos mdc = new Medicos();

        Integer sCheckBox01 = ((tv_CheckBox01.isChecked()) ? 1 : 0);
        Integer Select = getItemIndexById(spnn1.getSelectedItem().toString());

        mdc.setmUID(IDMedico);
        mdc.setM01(et01.getText().toString());
        mdc.setM02(et02.getText().toString());
        mdc.setM03(et03.getText().toString());
        mdc.setM04(et04.getText().toString());
        mdc.setM05(et05.getText().toString());

        mdc.setM06(et06.getText().toString());
        mdc.setM07(et07.getText().toString());
        mdc.setM08(et08.getText().toString());
        mdc.setM09(et09.getText().toString());
        mdc.setM010(et10.getText().toString());

        mdc.setM011(et11.getText().toString());
        mdc.setM012(et12.getText().toString());
        mdc.setM013(et13.getText().toString());
        mdc.setM014(et14.getText().toString());


        mdc.setM016(et16.getText().toString());
        mdc.setM017(et17.getText().toString());
        mdc.setM018(et18.getText().toString());
        mdc.setM019(et19.getText().toString());
        mdc.setM020(et20.getText().toString());

        mdc.setM21(et21.getText().toString());
        mdc.setM22(et22.getText().toString());
        mdc.setM23(et23.getText().toString());
        mdc.setM24(et24.getText().toString());
        mdc.setM25(et25.getText().toString());

        mdc.setM26(et26.getText().toString());
        mdc.setM27(et27.getText().toString());
        mdc.setM28(et28.getText().toString());
        mdc.setM29(et29.getText().toString());
        mdc.setM30(et30.getText().toString());
        mdc.setM31(sCheckBox01);
        mdc.setM32(Select);


        aLista.add(mdc);
        Medicos_model.Update(this,aLista);
        new AlertDialog.Builder(this).setTitle("Notificación").setMessage("Guardado con exito").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).show();
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
