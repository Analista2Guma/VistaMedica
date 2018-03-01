package com.guma.desarrollo.gvm.POJO;

/**
 * Created by kevin.rivera on 27/02/2018.
 */

public class Cliente {

    private String Nombre;
    private String Codigo;

    public Cliente(String nombre, String codigo) {
        Nombre = nombre;
        Codigo = codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }
}
