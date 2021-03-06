package com.guma.desarrollo.gvm.TASK;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;


import com.guma.desarrollo.gvm.API.Servicio;
import com.guma.desarrollo.gvm.MODEL.Usuario_model;
import com.guma.desarrollo.gvm.POJO.Usuario;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_Login;
import com.guma.desarrollo.gvm.activities.DashboardActivity;
import com.guma.desarrollo.gvm.services.Class_retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by maryan.espinoza on 08/03/2017.
 */


public class tskLogin extends AsyncTask<List<Usuario>,Integer,Void> {

    public ProgressDialog pdialog;
    Context cnxt;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private static final String TAG = "tskLogin";

    public tskLogin(Context cnxt) {
        this.cnxt = cnxt;
        preferences = PreferenceManager.getDefaultSharedPreferences(cnxt);
        editor = preferences.edit();
    }
    @Override
    protected void onPreExecute() {
        pdialog = ProgressDialog.show(cnxt, "","Iniciando...", true);
        super.onPreExecute();
    }
    @Override
    protected Void doInBackground(List<Usuario>... arrays) {

        String pmUser = "",pmPass="";

        for (List<Usuario> u:arrays){
            pmUser = u.get(0).getmUser();
            pmPass = u.get(0).getmPass();
        }


        Class_retrofit.Objfit().create(Servicio.class).get_Login(pmUser,pmPass).enqueue(new Callback<Respuesta_Login>() {
            @Override
            public void onResponse(Call<Respuesta_Login> call, Response<Respuesta_Login> response) {
                if(response.isSuccessful()){
                    if (response.body().getResults().get(0).getmUser().equals("")){
                        Alerta();
                    }else {
                        editor.putBoolean("isLogin", true);
                        editor.putString("IDVM", response.body().getResults().get(0).getmUser());
                        editor.putString("Ruta", response.body().getResults().get(0).getmRutas());
                        editor.putString("NombreVisitador", response.body().getResults().get(0).getmNamv());
                        editor.apply();
                        Usuario_model.Save(cnxt,response.body().getResults());
                        cnxt.startActivity(new Intent(cnxt,DashboardActivity.class));
                        ((Activity)(cnxt)).finish();
                    }
                    pdialog.dismiss();

                }else{
                    pdialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<Respuesta_Login> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                pdialog.dismiss();
            }

        });
        return null;
    }
    @Override
    protected void onPostExecute(Void s) {
        super.onPostExecute(s);
    }
    private void Alerta() {
        new AlertDialog.Builder(cnxt).setTitle("Alerta").setMessage("Usuario no encontrado").setCancelable(false).setPositiveButton("OK",null).show();
    }

}
