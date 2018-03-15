package com.guma.desarrollo.gvm.RESPUESTAS;

import com.guma.desarrollo.gvm.POJO.HstItemFacturados;

import java.util.ArrayList;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class Respuesta_HstItemFacturados {
    private ArrayList<HstItemFacturados> results;
    private int count;
    public int getCount(){
        return count = results.size();
    }
    public ArrayList<HstItemFacturados> getResults(){
        return results;
    }
}
