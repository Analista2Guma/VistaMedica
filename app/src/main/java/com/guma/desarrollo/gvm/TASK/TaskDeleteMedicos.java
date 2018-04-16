package com.guma.desarrollo.gvm.TASK;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.guma.desarrollo.gvm.API.Servicio;
import com.guma.desarrollo.gvm.MODEL.Farmacias_model;
import com.guma.desarrollo.gvm.MODEL.Medicos_model;
import com.guma.desarrollo.gvm.services.Class_retrofit;
import com.guma.desarrollo.gvm.services.ManagerURI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by maryan.espinoza on 08/03/2017.
 */

public class TaskDeleteMedicos extends AsyncTask<String,Integer,String> {
    Context cnxt;

    private static final String TAG = "TaskDeleteFarmacias";
    public TaskDeleteMedicos(Context cnxt) {
        this.cnxt = cnxt;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(final String... params) {
       Class_retrofit.Objfit().create(Servicio.class).Send_Id_delete_Row_medicos(params[0]).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){

                    if (response.body().equals("OK")){
                        Medicos_model.Delete(ManagerURI.getDirDb(),cnxt,params[0]);
                    }
                    Alerta();
                }else{
                    Toast.makeText(cnxt, "Algo Salio mal :(", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, "doInBackground: " + t.getMessage());
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
        new AlertDialog.Builder(cnxt).setTitle("Notificacion").setMessage("Registro borrado...").setCancelable(false).setPositiveButton("OK",null).show();
    }
}