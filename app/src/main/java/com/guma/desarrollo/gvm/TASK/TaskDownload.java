package com.guma.desarrollo.gvm.TASK;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.guma.desarrollo.gvm.API.Servicio;
import com.guma.desarrollo.gvm.MODEL.Articulos_model;
import com.guma.desarrollo.gvm.MODEL.Clientes_model;
import com.guma.desarrollo.gvm.MODEL.Mvstcla_model;
import com.guma.desarrollo.gvm.MODEL.MvtsArticulos_model;
import com.guma.desarrollo.gvm.MODEL.MvtsCliente_model;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_Clientes;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_MvstCLA;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_MvtsArticulos;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_MvtsCliente;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_articulos;
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
                    MvtsArticulos_model.Save(cnxt,respuesta.getResults());
                }else{
                    pdialog.dismiss();

                }
            }
            @Override
            public void onFailure(Call<Respuesta_MvtsArticulos> call, Throwable t) {
                pdialog.dismiss();
            }

        });

        Class_retrofit.Objfit().create(Servicio.class).get_MvtsCliente("f06").enqueue(new Callback<Respuesta_MvtsCliente>() {
            @Override
            public void onResponse(Call<Respuesta_MvtsCliente> call, Response<Respuesta_MvtsCliente> response) {
                if(response.isSuccessful()){
                    Respuesta_MvtsCliente respuesta = response.body();
                    MvtsCliente_model.Save(cnxt,respuesta.getResults());
                }else{
                    pdialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<Respuesta_MvtsCliente> call, Throwable t) {
                pdialog.dismiss();
            }

        });
        Class_retrofit.Objfit().create(Servicio.class).get_MvstCLA("f06").enqueue(new Callback<Respuesta_MvstCLA>() {
            @Override
            public void onResponse(Call<Respuesta_MvstCLA> call, Response<Respuesta_MvstCLA> response) {
                if(response.isSuccessful()){
                    Respuesta_MvstCLA respuesta = response.body();
                    Mvstcla_model.Save(cnxt,respuesta.getResults());
                }else{
                    pdialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<Respuesta_MvstCLA> call, Throwable t) {
                pdialog.dismiss();
            }

        });

        Class_retrofit.Objfit().create(Servicio.class).get_Clientes("f06").enqueue(new Callback<Respuesta_Clientes>() {
            @Override
            public void onResponse(Call<Respuesta_Clientes> call, Response<Respuesta_Clientes> response) {
                if(response.isSuccessful()){
                    Respuesta_Clientes respuesta = response.body();
                    Clientes_model.Save(cnxt,respuesta.getResults());
                }else{
                    pdialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<Respuesta_Clientes> call, Throwable t) {
                pdialog.dismiss();
            }

        });

        Class_retrofit.Objfit().create(Servicio.class).get_Articulos().enqueue(new Callback<Respuesta_articulos>() {
            @Override
            public void onResponse(Call<Respuesta_articulos> call, Response<Respuesta_articulos> response) {
                if(response.isSuccessful()){
                    Respuesta_articulos respuesta = response.body();
                    Articulos_model.Save(cnxt,respuesta.getResults());
                    pdialog.dismiss();
                }else{
                    pdialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<Respuesta_articulos> call, Throwable t) {
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