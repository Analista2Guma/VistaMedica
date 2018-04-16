package com.guma.desarrollo.gvm.TASK;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.google.gson.Gson;
import com.guma.desarrollo.gvm.API.Servicio;
import com.guma.desarrollo.gvm.DATABASE.SQLiteHelper;
import com.guma.desarrollo.gvm.LIB.Clock;
import com.guma.desarrollo.gvm.MODEL.Farmacias_model;
import com.guma.desarrollo.gvm.POJO.Farmacias;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_Farmacias;
import com.guma.desarrollo.gvm.services.Class_retrofit;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by maryan.espinoza on 08/03/2017.
 */

public class TaskFarmacias extends AsyncTask<Integer,Integer,String> {
    public ProgressDialog pdialog;
    Context cnxt;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    String user;

    private static final String TAG = "TaskFarmacias";
    public TaskFarmacias(Context cnxt) {
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

        List<Farmacias> oClientes = Farmacias_model.get(ManagerURI.getDirDb(),cnxt,"");
        String json = new Gson().toJson(oClientes);
        Log.d(TAG, "doInBackground: " + json);

       Class_retrofit.Objfit().create(Servicio.class).get_Farmacias(user,json).enqueue(new Callback<Respuesta_Farmacias>() {
            @Override
            public void onResponse(Call<Respuesta_Farmacias> call, Response<Respuesta_Farmacias> response) {
                if(response.isSuccessful()){
                    Respuesta_Farmacias respuesta = response.body();
                    Farmacias_model.Save(cnxt,respuesta.getResults(),"All");
                    Alerta();
                    pdialog.dismiss();
                }else{
                    pdialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<Respuesta_Farmacias> call, Throwable t) {
                Log.d(TAG, "doInBackground: " + t.getMessage());
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