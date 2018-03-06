package com.guma.desarrollo.gvm.RESPUESTAS;

import com.guma.desarrollo.gvm.POJO.Cliente;
import com.guma.desarrollo.gvm.POJO.Usuario;

import java.util.ArrayList;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class Respuesta_Login {
    private ArrayList<Usuario> results;
    private int count;
    public int getCount(){
        return count = results.size();
    }
    public ArrayList<Usuario> getResults(){
        return results;
    }
}
