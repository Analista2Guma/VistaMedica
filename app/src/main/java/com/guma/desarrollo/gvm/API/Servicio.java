package com.guma.desarrollo.gvm.API;

import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_Clientes;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_MvstCLA;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_MvtsArticulos;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_MvtsCliente;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_articulos;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by maryan.espinoza on 01/03/2017.
 */

public interface Servicio {


    //INFORMACION ACTUAL DEL MES.
    @FormUrlEncoded
    @POST("MvtsArticulos")
    Call<Respuesta_MvtsArticulos> get_MvtsArticulos(@Field("mVendedor") String mVendedor);

    @FormUrlEncoded
    @POST("MvtsCliente")
    Call<Respuesta_MvtsCliente> get_MvtsCliente(@Field("mVendedor") String mVendedor);

    @FormUrlEncoded
    @POST("MvstCLA")
    Call<Respuesta_MvstCLA>get_MvstCLA(@Field("mVendedor") String mVendedor);


    //MODULO GENERALES.
    @GET("ARTICULOS")
    Call<Respuesta_articulos> get_Articulos();

    @FormUrlEncoded
    @POST("CLIENTES")
    Call<Respuesta_Clientes> get_Clientes(@Field("mVendedor") String mVendedor);





}