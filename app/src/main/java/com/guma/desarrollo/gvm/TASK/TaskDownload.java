package com.guma.desarrollo.gvm.TASK;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.guma.desarrollo.gvm.API.Servicio;
import com.guma.desarrollo.gvm.MODEL.MvtsArticulos_model;
import com.guma.desarrollo.gvm.MODEL.MvtsCliente_model;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_MvtsArticulos;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_MvtsCliente;
import com.guma.desarrollo.gvm.services.Class_retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by maryan.espinoza on 08/03/2017.
 */

public class TaskDownload extends AsyncTask<Integer,Integer,String> {
    public ProgressDialog pdialog;
    Context cnxt;

    private static final String TAG = "TaskDownload";
    public TaskDownload(Context cnxt) {
        this.cnxt = cnxt;
    }

    @Override
    protected void onPreExecute() {
        pdialog = ProgressDialog.show(cnxt, "","Iniciando...", true);
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Integer... params) {



        Class_retrofit.Objfit().create(Servicio.class).get_MvtsArticulos("f06").enqueue(new Callback<Respuesta_MvtsArticulos>() {
            @Override
            public void onResponse(Call<Respuesta_MvtsArticulos> call, Response<Respuesta_MvtsArticulos> response) {
                if(response.isSuccessful()){
                    Respuesta_MvtsArticulos respuesta = response.body();
                    Log.d(TAG, "onResponse: Successful MvtsArticulos" + respuesta.getCount());
                    MvtsArticulos_model.Save(cnxt,respuesta.getResults());
                }else{
                    pdialog.dismiss();
                    Log.d(TAG, "onResponse: noSuccessful MvtsArticulos" + response.errorBody() );
                }
            }
            @Override
            public void onFailure(Call<Respuesta_MvtsArticulos> call, Throwable t) {
                Log.d(TAG, "onResponse: onFailure MvtsArticulos" + t.getMessage() );
                pdialog.dismiss();
            }

        });

        Class_retrofit.Objfit().create(Servicio.class).get_MvtsCliente("f06").enqueue(new Callback<Respuesta_MvtsCliente>() {
            @Override
            public void onResponse(Call<Respuesta_MvtsCliente> call, Response<Respuesta_MvtsCliente> response) {
                if(response.isSuccessful()){
                    Respuesta_MvtsCliente respuesta = response.body();
                    Log.d(TAG, "onResponse: Successful " + respuesta.getCount());
                    MvtsCliente_model.Save(cnxt,respuesta.getResults());
                    pdialog.dismiss();
                }else{
                    pdialog.dismiss();
                    Log.d(TAG, "onResponse: noSuccessful" + response.errorBody() );
                }
            }
            @Override
            public void onFailure(Call<Respuesta_MvtsCliente> call, Throwable t) {
                Log.d(TAG, "onResponse: onFailure" + t.getMessage() );
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