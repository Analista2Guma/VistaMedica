package com.guma.desarrollo.gvm.LIB;

import android.text.format.Time;

/**
 * Created by maryan.espinoza on 05/03/2018.
 */

public class Clock {
    public static String getTimeStamp() {
        Time now = new Time();
        now.setToNow();
        String sTime = now.format("%Y-%m-%d %H:%M:%S");
        return sTime;
    }
}
