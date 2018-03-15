package com.guma.desarrollo.gvm.RESPUESTAS;

import com.guma.desarrollo.gvm.POJO.Facturas_puntos;

import java.util.ArrayList;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class Respuesta_Facturas_puntos {
    private ArrayList<Facturas_puntos> results;
    private int count;
    public int getCount(){
        return count = results.size();
    }
    public ArrayList<Facturas_puntos> getResults(){
        return results;
    }
}
