package com.guma.desarrollo.gvm.RESPUESTAS;


import com.guma.desarrollo.gvm.POJO.Llaves;

import java.util.ArrayList;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class Respuesta_Llaves {
    private ArrayList<Llaves> results;
    private int count;
    public int getCount(){
        return count = results.size();
    }
    public ArrayList<Llaves> getResults(){
        return results;
    }
}
