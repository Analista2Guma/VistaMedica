package com.guma.desarrollo.gvm.POJO;

public class Especialidades {
    String mUID,mName;

    public Especialidades(String mUID, String mName) {
        this.mUID = mUID;
        this.mName = mName;
    }
    public Especialidades(){}

    public String getmUID() {
        return mUID;
    }

    public void setmUID(String mUID) {
        this.mUID = mUID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
