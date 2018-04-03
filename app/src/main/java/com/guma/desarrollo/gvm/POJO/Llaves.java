package com.guma.desarrollo.gvm.POJO;

public class Llaves {
    String mRut,mFar,mMed;
    public Llaves(){}

    public Llaves(String mRut, String mFar, String mMed) {
        this.mRut = mRut;
        this.mFar = mFar;
        this.mMed = mMed;
    }

    public String getmRut() {
        return mRut;
    }

    public void setmRut(String mRut) {
        this.mRut = mRut;
    }

    public String getmFar() {
        return mFar;
    }

    public void setmFar(String mFar) {
        this.mFar = mFar;
    }

    public String getmMed() {
        return mMed;
    }

    public void setmMed(String mMed) {
        this.mMed = mMed;
    }
}
