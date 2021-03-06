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
import com.guma.desarrollo.gvm.MODEL.Articulos_model;
import com.guma.desarrollo.gvm.MODEL.Clientes_model;
import com.guma.desarrollo.gvm.MODEL.Cuotas_model;
import com.guma.desarrollo.gvm.MODEL.Especialidades_model;
import com.guma.desarrollo.gvm.MODEL.Facturas_Puntos_model;

import com.guma.desarrollo.gvm.MODEL.HstItemFacturado_model;
import com.guma.desarrollo.gvm.MODEL.Llaves_model;
import com.guma.desarrollo.gvm.MODEL.LogActividades_model;
import com.guma.desarrollo.gvm.MODEL.Lotes_model;
import com.guma.desarrollo.gvm.MODEL.Mvstcla_model;
import com.guma.desarrollo.gvm.MODEL.MvtsArticulos_model;
import com.guma.desarrollo.gvm.MODEL.MvtsCliente_model;
import com.guma.desarrollo.gvm.MODEL.vst_3m_cla_model;
import com.guma.desarrollo.gvm.MODEL.vts_3m_Cliente_model;
import com.guma.desarrollo.gvm.MODEL.vts_m3_Articulos_model;
import com.guma.desarrollo.gvm.POJO.Llaves;
import com.guma.desarrollo.gvm.POJO.Log_Actividades;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_Clientes;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_Cuotas;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_Especialidades;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_Facturas_puntos;

import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_HstItemFacturados;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_Llaves;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_Lotes;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_MvstCLA;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_MvtsArticulos;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_MvtsCliente;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_articulos;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_vts_3m_Articulos;
import com.guma.desarrollo.gvm.services.Class_retrofit;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by maryan.espinoza on 08/03/2017.
 */

public class TaskDownload extends AsyncTask<Integer,Integer,String> {
    public ProgressDialog pdialog;
    Context cnxt;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    String user,mUID;
    private static final String TAG = "TaskDownload";
    public TaskDownload(Context cnxt) {
        this.cnxt = cnxt;
        preferences = PreferenceManager.getDefaultSharedPreferences(cnxt);
        editor = preferences.edit();
        user =preferences.getString("Ruta","");
        mUID=preferences.getString("IDVM","");
    }

    @Override
    protected void onPreExecute() {
        pdialog = ProgressDialog.show(cnxt, "","Iniciando...", true);
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Integer... params) {


        //VENTAS POR ARTICULOS MENSUALES
        Class_retrofit.Objfit().create(Servicio.class).get_MvtsArticulos(user,mUID).enqueue(new Callback<Respuesta_MvtsArticulos>() {
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
        //VENTAS POR ARTICULOS EN LOS ULTIMOS 3 MESES
        Class_retrofit.Objfit().create(Servicio.class).get_vm_3M_vtsArticulos(user,mUID).enqueue(new Callback<Respuesta_vts_3m_Articulos>() {
            @Override
            public void onResponse(Call<Respuesta_vts_3m_Articulos> call, Response<Respuesta_vts_3m_Articulos> response) {
                if(response.isSuccessful()){
                    Respuesta_vts_3m_Articulos respuesta = response.body();
                    vts_m3_Articulos_model.Save(cnxt,respuesta.getResults());
                }else{
                    pdialog.dismiss();

                }
            }
            @Override
            public void onFailure(Call<Respuesta_vts_3m_Articulos> call, Throwable t) {
                pdialog.dismiss();
            }

        });

        //CLIENTES FACTURADOS EN EL MES
        Class_retrofit.Objfit().create(Servicio.class).get_MvtsCliente(user,mUID).enqueue(new Callback<Respuesta_MvtsCliente>() {
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

        //CLIENTES FACTURADOS EN LOS ULTIMOS 3 MESES
        Class_retrofit.Objfit().create(Servicio.class).get_vts_3M_Cliente(user,mUID).enqueue(new Callback<Respuesta_MvtsCliente>() {
            @Override
            public void onResponse(Call<Respuesta_MvtsCliente> call, Response<Respuesta_MvtsCliente> response) {
                if(response.isSuccessful()){
                    Respuesta_MvtsCliente respuesta = response.body();
                    vts_3m_Cliente_model.Save(cnxt,respuesta.getResults());
                }else{
                    pdialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<Respuesta_MvtsCliente> call, Throwable t) {
                pdialog.dismiss();
            }

        });

        //ARTICULOS FACTURADOS EN EL MES
        Class_retrofit.Objfit().create(Servicio.class).get_MvstCLA(user,mUID).enqueue(new Callback<Respuesta_MvstCLA>() {
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

        //HISTORICO DE ITEM FACTURADOS POR CLIENTE
        Class_retrofit.Objfit().create(Servicio.class).get_vst_HstItemFacturados(user,mUID).enqueue(new Callback<Respuesta_HstItemFacturados>() {
            @Override
            public void onResponse(Call<Respuesta_HstItemFacturados> call, Response<Respuesta_HstItemFacturados> response) {
                if(response.isSuccessful()){
                    Respuesta_HstItemFacturados respuesta = response.body();
                    HstItemFacturado_model.Save(cnxt,respuesta.getResults());
                }else{
                    pdialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<Respuesta_HstItemFacturados> call, Throwable t) {
                pdialog.dismiss();
            }

        });

        //ARTICULOS FACTURADOS EN LOS ULTIMOS 3 MESES
        Class_retrofit.Objfit().create(Servicio.class).get_vst_3M_CLA(user,mUID).enqueue(new Callback<Respuesta_MvstCLA>() {
            @Override
            public void onResponse(Call<Respuesta_MvstCLA> call, Response<Respuesta_MvstCLA> response) {
                if(response.isSuccessful()){
                    Respuesta_MvstCLA respuesta = response.body();
                    vst_3m_cla_model.Save(cnxt,respuesta.getResults());
                }else{
                    pdialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<Respuesta_MvstCLA> call, Throwable t) {
                pdialog.dismiss();
            }

        });
        //CUOTAS CORRESPONDIENTES A CADA MES
        Class_retrofit.Objfit().create(Servicio.class).get_Coutas(user,mUID).enqueue(new Callback<Respuesta_Cuotas>() {
            @Override
            public void onResponse(Call<Respuesta_Cuotas> call, Response<Respuesta_Cuotas> response) {
                if(response.isSuccessful()){

                    Respuesta_Cuotas respuesta = response.body();
                    Log.d(TAG, "onResponse: " + respuesta.getCount());
                    Cuotas_model.Save(cnxt,respuesta.getResults());
                }else{
                    pdialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<Respuesta_Cuotas> call, Throwable t) {
                pdialog.dismiss();
            }

        });
        //CLIENTES DE LA RUTA
        Class_retrofit.Objfit().create(Servicio.class).get_Clientes(user).enqueue(new Callback<Respuesta_Clientes>() {
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
        //LOTES DE LOS ARTICULOS
        Class_retrofit.Objfit().create(Servicio.class).get_Lotes().enqueue(new Callback<Respuesta_Lotes>() {
            @Override
            public void onResponse(Call<Respuesta_Lotes> call, Response<Respuesta_Lotes> response) {
                if(response.isSuccessful()){
                    Respuesta_Lotes respuesta = response.body();
                    Lotes_model.Save(cnxt,respuesta.getResults());
                }else{
                    pdialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<Respuesta_Lotes> call, Throwable t) {
                pdialog.dismiss();
            }

        });
        //OBTIENE LAS FACTURAS QUE CONTIENEN PUNTOS
        Class_retrofit.Objfit().create(Servicio.class).get_Facturas_puntos(user).enqueue(new Callback<Respuesta_Facturas_puntos>() {
            @Override
            public void onResponse(Call<Respuesta_Facturas_puntos> call, Response<Respuesta_Facturas_puntos> response) {
                if(response.isSuccessful()){
                    Respuesta_Facturas_puntos respuesta = response.body();
                    Facturas_Puntos_model.Save(cnxt,respuesta.getResults());
                }else{
                    pdialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<Respuesta_Facturas_puntos> call, Throwable t) {
                pdialog.dismiss();
            }

        });

        Class_retrofit.Objfit().create(Servicio.class).get_Especialidades().enqueue(new Callback<Respuesta_Especialidades>() {
            @Override
            public void onResponse(Call<Respuesta_Especialidades> call, Response<Respuesta_Especialidades> response) {
                if(response.isSuccessful()){
                    Respuesta_Especialidades respuesta = response.body();
                    Especialidades_model.Save(cnxt,respuesta.getResults());
                }else{
                    pdialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<Respuesta_Especialidades> call, Throwable t) {
                pdialog.dismiss();
            }

        });

        List <Llaves> llaves = Llaves_model.getID(ManagerURI.getDirDb(),cnxt);
        String json_llaves = new Gson().toJson(llaves);
        Class_retrofit.Objfit().create(Servicio.class).get_Llaves(mUID,json_llaves).enqueue(new Callback<Respuesta_Llaves>() {
            @Override
            public void onResponse(Call<Respuesta_Llaves> call, Response<Respuesta_Llaves> response) {
                if(response.isSuccessful()){
                    Respuesta_Llaves respuesta = response.body();
                    Llaves_model.Save(cnxt,respuesta.getResults());
                }else{
                    pdialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<Respuesta_Llaves> call, Throwable t) {
                pdialog.dismiss();
            }

        });

        //MASTER DE ARTICULOS
        Class_retrofit.Objfit().create(Servicio.class).get_Articulos().enqueue(new Callback<Respuesta_articulos>() {
            @Override
            public void onResponse(Call<Respuesta_articulos> call, Response<Respuesta_articulos> response) {
                if(response.isSuccessful()){
                    Respuesta_articulos respuesta = response.body();
                    Articulos_model.Save(cnxt,respuesta.getResults());
                    Alerta();
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



        List<Log_Actividades> olst =LogActividades_model.get_aEnviar(ManagerURI.getDirDb(),cnxt);
        String json = new Gson().toJson(olst);
        if (olst.size()>0){
            String json2 = new Gson().toJson(LogActividades_model.get_detalle_aEnviar(ManagerURI.getDirDb(),cnxt));
            Log.d("JsonReportes:", "doInBackground: " + json);
            Class_retrofit.Objfit().create(Servicio.class).SendLogs(json,json2).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccessful()){
                        SQLiteHelper.ExecuteSQL(ManagerURI.getDirDb(),cnxt, "UPDATE log_actividades SET ESTADO='1' WHERE ESTADO='0'");
                        SQLiteHelper.ExecuteSQL(ManagerURI.getDirDb(),cnxt, "UPDATE log_actividades_detalle SET ESTADO='1' WHERE ESTADO='0'");
                        pdialog.dismiss();
                    }else{

                        pdialog.dismiss();
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    pdialog.dismiss();
                }
            });
        }







        editor.putLong("lstDownload", System.currentTimeMillis());
        editor.apply();
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
