package com.guma.desarrollo.gvm.RESPUESTAS;

import com.guma.desarrollo.gvm.POJO.Cliente;
import com.guma.desarrollo.gvm.POJO.Farmacias;

import java.util.ArrayList;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class Respuesta_Farmacias {
    private ArrayList<Farmacias> results;
    private int count;
    public int getCount(){
        return count = results.size();
    }
    public ArrayList<Farmacias> getResults(){
        return results;
    }
}
