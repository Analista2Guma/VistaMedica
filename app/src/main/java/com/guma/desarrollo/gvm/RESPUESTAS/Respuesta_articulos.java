package com.guma.desarrollo.gvm.RESPUESTAS;

import com.guma.desarrollo.gvm.POJO.Articulo;

import java.util.ArrayList;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class Respuesta_articulos {
    private ArrayList<Articulo> results;
    private int count;
    public int getCount(){
        return count = results.size();
    }
    public ArrayList<Articulo> getResults(){
        return results;
    }
}
