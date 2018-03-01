package com.guma.desarrollo.gvm.RESPUESTAS;

import com.guma.desarrollo.gvm.POJO.MvstCLA;

import java.util.ArrayList;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class Respuesta_MvstCLA {
    private ArrayList<MvstCLA> results;
    private int count;
    public int getCount(){
        return count = results.size();
    }
    public ArrayList<MvstCLA> getResults(){
        return results;
    }
}
