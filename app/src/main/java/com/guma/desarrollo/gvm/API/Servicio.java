package com.guma.desarrollo.gvm.API;

import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_Clientes;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_Cuotas;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_Especialidades;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_Facturas_puntos;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_Farmacias;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_HstItemFacturados;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_Llaves;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_Login;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_Lotes;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_Medicos;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_MvstCLA;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_MvtsArticulos;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_MvtsCliente;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_articulos;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_vts_3m_Articulos;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by maryan.espinoza on 01/03/2017.
 */

public interface Servicio {


    //VENTA DE ARTICULOS EN EL MES ACTUAL
    @FormUrlEncoded
    @POST("MvtsArticulos")
    Call<Respuesta_MvtsArticulos> get_MvtsArticulos(@Field("mVendedor") String mVendedor,@Field("mUID") String mUID);

    //VENTAS POR ARTICULOS EN LOS ULTIMOS 3 MESES
    @FormUrlEncoded
    @POST("vtsArticulos")
    Call<Respuesta_vts_3m_Articulos> get_vm_3M_vtsArticulos(@Field("mVendedor") String mVendedor,@Field("mUID") String mUID);


    //CLIENTES FACTURADOS POR MES
    @FormUrlEncoded
    @POST("MvtsCliente")
    Call<Respuesta_MvtsCliente> get_MvtsCliente(@Field("mVendedor") String mVendedor,@Field("mUID") String mUID);

    //CLIENTES FACTURADOS EN LOS ULTIMOS 3 MESES
    @FormUrlEncoded
    @POST("vtsCliente")
    Call<Respuesta_MvtsCliente> get_vts_3M_Cliente(@Field("mVendedor") String mVendedor,@Field("mUID") String mUID);

    //ARTICULOS FACTURADOS EN EL MES
    @FormUrlEncoded
    @POST("MvstCLA")
    Call<Respuesta_MvstCLA>get_MvstCLA(@Field("mVendedor") String mVendedor,@Field("mUID") String mUID);

    //ARTICULOS FACTURADOS EN LOS ULTIMOS 3 MESES
    @FormUrlEncoded
    @POST("vstCLA")
    Call<Respuesta_MvstCLA>get_vst_3M_CLA(@Field("mVendedor") String mVendedor,@Field("mUID") String mUID);

    @FormUrlEncoded
    @POST("HstItemFacturados")
    Call<Respuesta_HstItemFacturados>get_vst_HstItemFacturados(@Field("mVendedor") String mVendedor,@Field("mUID") String mUID);


    //MODULO GENERALES.
    @GET("ARTICULOS")
    Call<Respuesta_articulos> get_Articulos();

    @GET("Especialidades")
    Call<Respuesta_Especialidades> get_Especialidades();

    @FormUrlEncoded
    @POST("Login")
    Call<Respuesta_Login>get_Login(@Field("mUser") String mUser, @Field("mPassword") String mPassword);

    @FormUrlEncoded
    @POST("CLIENTES")
    Call<Respuesta_Clientes> get_Clientes(@Field("mVendedor") String mVendedor);

    @FormUrlEncoded
    @POST("Mcuotas")
    Call<Respuesta_Cuotas> get_Coutas(@Field("mVendedor") String mVendedor,@Field("mUID") String mUID);

    @FormUrlEncoded
    @POST("PUNTOS")
    Call<Respuesta_Facturas_puntos> get_Facturas_puntos(@Field("mVendedor") String mVendedor);

    @FormUrlEncoded
    @POST("Farmacias")
    Call<Respuesta_Farmacias> get_Farmacias(@Field("mVendedor") String mVendedor,@Field("mFarmacias") String mFarmacias);

    @FormUrlEncoded
    @POST("Medicos")
    Call<Respuesta_Medicos> get_Medicos(@Field("mVendedor") String mVendedor, @Field("mJSONMedicos") String mFarmacias);

    @FormUrlEncoded
    @POST("Llaves")
    Call<Respuesta_Llaves> get_Llaves(@Field("mVendedor") String mVendedor,@Field("mJsonLlaves") String mJson);

    @FormUrlEncoded
    @POST("UpdateLlaves")
    Call<String> update_Llaves(@Field("mVendedor") String mVendedor,
                                      @Field("mFarmacias") Integer mFarmacias,
                                      @Field("mMedicos") Integer mMedicos,
                                      @Field("mReportes") Integer mReportes);

    @GET("LOTES")
    Call<Respuesta_Lotes> get_Lotes();

    @FormUrlEncoded
    @POST("DeleteFarmacia")
    Call<String> Send_Id_delete_Row(@Field("mID") String mID);

    @FormUrlEncoded
    @POST("Logs")
    Call<String> SendLogs(@Field("mLogs") String mLogs,@Field("mLogsDetalles") String mLogsDetalles);

    @FormUrlEncoded
    @POST("DeleteMedicos")
    Call<String> Send_Id_delete_Row_medicos(@Field("mID") String mID);




}