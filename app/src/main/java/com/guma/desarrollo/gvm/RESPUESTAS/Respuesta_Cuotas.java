package com.guma.desarrollo.gvm.RESPUESTAS;

import com.guma.desarrollo.gvm.POJO.Articulo;
import com.guma.desarrollo.gvm.POJO.Cuotas;

import java.util.ArrayList;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class Respuesta_Cuotas {
    private ArrayList<Cuotas> results;
    private int count;
    public int getCount(){
        return count = results.size();
    }
    public ArrayList<Cuotas> getResults(){
        return results;
    }
}
