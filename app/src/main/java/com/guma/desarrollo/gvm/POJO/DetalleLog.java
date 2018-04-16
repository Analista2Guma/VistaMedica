package com.guma.desarrollo.gvm.POJO;

public class DetalleLog {
    String ID,Articulos,Descrp,Cantidad;
    public DetalleLog(){};
    public DetalleLog(String ID, String articulos, String descrp, String cantidad) {
        this.ID = ID;
        Articulos = articulos;
        Descrp = descrp;
        Cantidad = cantidad;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getArticulos() {
        return Articulos;
    }

    public void setArticulos(String articulos) {
        Articulos = articulos;
    }

    public String getDescrp() {
        return Descrp;
    }

    public void setDescrp(String descrp) {
        Descrp = descrp;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String cantidad) {
        Cantidad = cantidad;
    }
}
