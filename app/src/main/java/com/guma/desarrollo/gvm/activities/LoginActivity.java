package com.guma.desarrollo.gvm.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.guma.desarrollo.gvm.DATABASE.SQLiteHelper;
import com.guma.desarrollo.gvm.MODEL.Usuario_model;
import com.guma.desarrollo.gvm.POJO.Usuario;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.TASK.tskLogin;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private boolean checked;
    List<Usuario> isUsuario;
    Usuario us = new Usuario();
    private static final String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        checked = preferences.getBoolean("isLogin", false);

        final TextView txtUsuario = findViewById(R.id.ediTextUsuario);
        final TextView txtPassword = findViewById(R.id.ediTextPassword);
        final List<Usuario> lista = new ArrayList<>();

        try {
            new SQLiteHelper(ManagerURI.getDirDb(),this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lista.clear();

                if (TextUtils.isEmpty(txtUsuario.getText())){
                    Toast.makeText(LoginActivity.this, "Campos Requeridos", Toast.LENGTH_SHORT).show();
                }else{
                    if (TextUtils.isEmpty(txtPassword.getText())){
                        Toast.makeText(LoginActivity.this, "Campos Requeridos", Toast.LENGTH_SHORT).show();
                    }else{
                        lista.add(new Usuario(txtUsuario.getText().toString(),txtPassword.getText().toString(),"","",""));
                        isUsuario = Usuario_model.get(ManagerURI.getDirDb(),LoginActivity.this,lista);
                        if (isUsuario.size()>0){
                            editor.putBoolean("isLogin", !checked);
                            editor.putString("IDVM", isUsuario.get(0).getmUser());
                            editor.putString("Ruta", isUsuario.get(0).getmRutas());
                            editor.putString("NombreVisitador", isUsuario.get(0).getmNamv());
                            editor.apply();
                            startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
                            finish();
                        }else{
                            new tskLogin(LoginActivity.this).execute(lista);
                        }
                    }
                }
            }
        });
        if (checked){
            startActivity(new Intent(this,DashboardActivity.class));
            finish();
        }

    }
}
