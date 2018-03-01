package com.guma.desarrollo.gvm.POJO;

/**
 * Created by kevin.rivera on 27/02/2018.
 */

public class Articulo {

    private String Nombre;
    private String Codigo;
    private int Puntos;

    public Articulo(String nombre, String codigo, int puntos) {
        Nombre = nombre;
        Codigo = codigo;
        Puntos = puntos;
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

    public int getPuntos() {
        return Puntos;
    }

    public void setPuntos(int puntos) {
        Puntos = puntos;
    }
}
