package com.guma.desarrollo.gvm.services;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


/**
 * Created by maryan.espinoza on 29/12/2016.
 */

public class ManagerURI {
    //private static String SERVER = "192.168.1.78:8448";

    private static String SERVER = "165.98.75.219:8448";//SERVIDOR
    //private static String SERVER = "165.98.75.45:8448";//SERVIDOR TIPITAPA

    private static String URL_Base= "http://"+ SERVER + "/API_vm/index.php/";
    private static String  DIR_DB = "/data/data/com.guma.desarrollo.gvm/";
    public static String getURL_Base() {
        return URL_Base;
    }
    public static String getDirDb() {
        return DIR_DB;
    }
    public static OkHttpClient ConfigTimeOut(){
        return new OkHttpClient().newBuilder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .build();
    }

    public static boolean isOnlinea(Context cxt){
        ConnectivityManager cm = (ConnectivityManager) cxt.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()){
            return true;
        }else {
            return false;
        }
    }

}
