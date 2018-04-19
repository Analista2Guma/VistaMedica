package com.guma.desarrollo.gvm.RESPUESTAS;

import com.guma.desarrollo.gvm.POJO.Especialidades;


import java.util.ArrayList;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class Respuesta_Especialidades {
    private ArrayList<Especialidades> results;
    private int count;
    public int getCount(){
        return count = results.size();
    }
    public ArrayList<Especialidades> getResults(){
        return results;
    }
}
