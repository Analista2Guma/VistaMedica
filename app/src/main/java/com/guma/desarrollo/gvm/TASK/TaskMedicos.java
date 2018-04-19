package com.guma.desarrollo.gvm.TASK;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.guma.desarrollo.gvm.API.Servicio;

import com.guma.desarrollo.gvm.MODEL.Llaves_model;
import com.guma.desarrollo.gvm.MODEL.Medicos_model;
import com.guma.desarrollo.gvm.POJO.Llaves;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_Medicos;
import com.guma.desarrollo.gvm.services.Class_retrofit;
import com.guma.desarrollo.gvm.services.ManagerURI;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by maryan.espinoza on 08/03/2017.
 */

public class TaskMedicos extends AsyncTask<Integer,Integer,String> {
    public ProgressDialog pdialog;
    Context cnxt;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    String user;

    private static final String TAG = "TaskMedicos";
    public TaskMedicos(Context cnxt) {
        this.cnxt = cnxt;
        preferences = PreferenceManager.getDefaultSharedPreferences(cnxt);
        editor = preferences.edit();
        user =preferences.getString("Ruta","");
    }

    @Override
    protected void onPreExecute() {
        pdialog = ProgressDialog.show(cnxt, "","Iniciando...", true);
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Integer... params) {
        String json = new Gson().toJson(Medicos_model.get(ManagerURI.getDirDb(),cnxt,""));
        Log.d("TaskFarmacias", "doInBackground: " + json);
        Class_retrofit.Objfit().create(Servicio.class).get_Medicos(user,json).enqueue(new Callback<Respuesta_Medicos>() {
            @Override
            public void onResponse(Call<Respuesta_Medicos> call, Response<Respuesta_Medicos> response) {
                Log.d(TAG, "onResponse: ");
                if(response.isSuccessful()){
                    Log.d(TAG, "isSuccessful: ");
                    Respuesta_Medicos respuesta = response.body();
                    if (respuesta.getResults().get(0).getmUID().equals("0")){
                    }else{
                        Medicos_model.Save(cnxt,respuesta.getResults(),"All");
                        Alerta();
                    }

                    pdialog.dismiss();
                }else{
                    Log.d(TAG, "NoisSuccessful: ");
                    pdialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Respuesta_Medicos> call, Throwable t) {
                Log.d(TAG, "noResponse: "+ t.getMessage());
                pdialog.dismiss();
            }
        });



        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {super.onProgressUpdate(values);}
    private void Alerta() {
        new AlertDialog.Builder(cnxt).setTitle("RECIBIDO").setMessage("Informacion Recibida...").setCancelable(false).setPositiveButton("OK",null).show();
    }
}