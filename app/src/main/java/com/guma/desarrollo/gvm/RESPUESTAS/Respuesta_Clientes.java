package com.guma.desarrollo.gvm.RESPUESTAS;

import com.guma.desarrollo.gvm.POJO.Cliente;

import java.util.ArrayList;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class Respuesta_Clientes {
    private ArrayList<Cliente> results;
    private int count;
    public int getCount(){
        return count = results.size();
    }
    public ArrayList<Cliente> getResults(){
        return results;
    }
}
