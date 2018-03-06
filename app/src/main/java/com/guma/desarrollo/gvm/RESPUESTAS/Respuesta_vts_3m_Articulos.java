package com.guma.desarrollo.gvm.RESPUESTAS;

import com.guma.desarrollo.gvm.POJO.vts_3m_Articulos;

import java.util.ArrayList;

/**
 * Created by maryan.espinoza on 28/02/2018.
 */

public class Respuesta_vts_3m_Articulos {
    private ArrayList<vts_3m_Articulos> results;
    private int count;
    public int getCount(){
        return count = results.size();
    }
    public ArrayList<vts_3m_Articulos> getResults(){
        return results;
    }
}
