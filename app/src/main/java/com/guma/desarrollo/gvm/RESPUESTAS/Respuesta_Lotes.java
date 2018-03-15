package com.guma.desarrollo.gvm.RESPUESTAS;

import com.guma.desarrollo.gvm.POJO.Lotes;

import java.util.ArrayList;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class Respuesta_Lotes {
    private ArrayList<Lotes> results;
    private int count;
    public int getCount(){
        return count = results.size();
    }
    public ArrayList<Lotes> getResults(){
        return results;
    }
}
