package com.guma.desarrollo.gvm.POJO;

public class Log_Actividades {
    String UID,mCliente,mComentario,mRuta,mLogitud,mLatitud;
    long mFecha;
    public Log_Actividades(){}

    public Log_Actividades(String UID, String mCliente, String mComentario, String mRuta, String mLogitud, String mLatitud, long mFecha) {
        this.UID = UID;
        this.mCliente = mCliente;
        this.mComentario = mComentario;
        this.mRuta = mRuta;
        this.mLogitud = mLogitud;
        this.mLatitud = mLatitud;
        this.mFecha = mFecha;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getmCliente() {
        return mCliente;
    }

    public void setmCliente(String mCliente) {
        this.mCliente = mCliente;
    }

    public String getmComentario() {
        return mComentario;
    }

    public void setmComentario(String mComentario) {
        this.mComentario = mComentario;
    }

    public String getmRuta() {
        return mRuta;
    }

    public void setmRuta(String mRuta) {
        this.mRuta = mRuta;
    }

    public String getmLogitud() {
        return mLogitud;
    }

    public void setmLogitud(String mLogitud) {
        this.mLogitud = mLogitud;
    }

    public String getmLatitud() {
        return mLatitud;
    }

    public void setmLatitud(String mLatitud) {
        this.mLatitud = mLatitud;
    }

    public long getmFecha() {
        return mFecha;
    }

    public void setmFecha(long mFecha) {
        this.mFecha = mFecha;
    }
}
