package com.guma.desarrollo.gvm.API;

import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_MvtsArticulos;
import com.guma.desarrollo.gvm.RESPUESTAS.Respuesta_MvtsCliente;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * Created by maryan.espinoza on 01/03/2017.
 */

public interface Servicio {

    /*@GET("ARTICULOS")
    Call<Respuesta_articulos> obtenerListaArticulos();*/

    @FormUrlEncoded
    @POST("MvtsArticulos")
    Call<Respuesta_MvtsArticulos> get_MvtsArticulos(@Field("mVendedor") String mVendedor);

    @FormUrlEncoded
    @POST("vtsCliente")
    Call<Respuesta_MvtsCliente> get_MvtsCliente(@Field("mVendedor") String mVendedor);



}