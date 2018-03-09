package com.guma.desarrollo.gvm.POJO;

/**
 * Created by maryan.espinoza on 06/03/2018.
 */

public class Cuotas {

    String mRuta,mArti,mDesc,mCant,mCnAc;

    public Cuotas(String mRuta, String mArti, String mDesc, String mCant,String mCnAc) {
        this.mRuta = mRuta;
        this.mArti = mArti;
        this.mDesc = mDesc;
        this.mCant = mCant;
        this.mCnAc = mCnAc;
    }
    public Cuotas(){}

    public String getmRuta() {
        return mRuta;
    }

    public void setmRuta(String mRuta) {
        this.mRuta = mRuta;
    }

    public String getmCnAc() {
        return mCnAc;
    }

    public void setmCnAc(String mCnAc) {
        this.mCnAc = mCnAc;
    }

    public String getmArti() {
        return mArti;
    }

    public void setmArti(String mArti) {
        this.mArti = mArti;
    }

    public String getmDesc() {
        return mDesc;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public String getmCant() {
        return mCant;
    }

    public void setmCant(String mCant) {
        this.mCant = mCant;
    }
}
