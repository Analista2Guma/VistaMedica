package com.guma.desarrollo.gvm.RESPUESTAS;

import com.guma.desarrollo.gvm.POJO.Farmacias;
import com.guma.desarrollo.gvm.POJO.Medicos;

import java.util.ArrayList;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class Respuesta_Medicos {
    private ArrayList<Medicos> results;
    private int count;
    public int getCount(){
        return count = results.size();
    }
    public ArrayList<Medicos> getResults(){
        return results;
    }
}
