package com.guma.desarrollo.gvm.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by maryan.espinoza on 26/02/2018.
 */

public class Class_retrofit {
    public static Retrofit Objfit() {
        return  new Retrofit.Builder()
                .baseUrl(ManagerURI.getURL_Base())
                .client(ManagerURI.ConfigTimeOut())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}