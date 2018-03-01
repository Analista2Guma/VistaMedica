package com.guma.desarrollo.gvm.RESPUESTAS;

import com.guma.desarrollo.gvm.POJO.MvtsArticulos;

import java.util.ArrayList;

/**
 * Created by maryan.espinoza on 28/02/2018.
 */

public class Respuesta_MvtsArticulos {
    private ArrayList<MvtsArticulos> results;
    private int count;
    public int getCount(){
        return count = results.size();
    }
    public ArrayList<MvtsArticulos> getResults(){
        return results;
    }
}
