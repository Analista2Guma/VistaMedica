package com.guma.desarrollo.gvm.RESPUESTAS;

import com.guma.desarrollo.gvm.POJO.MvtsCliente;

import java.util.ArrayList;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class Respuesta_MvtsCliente {
    private ArrayList<MvtsCliente> results;
    private int count;
    public int getCount(){
        return count = results.size();
    }
    public ArrayList<MvtsCliente> getResults(){
        return results;
    }
}
