package com.guma.desarrollo.gvm.MODEL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.guma.desarrollo.gvm.DATABASE.SQLiteHelper;
import com.guma.desarrollo.gvm.POJO.Facturas_puntos;
import com.guma.desarrollo.gvm.POJO.Lotes;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class Facturas_Puntos_model {

    private static final String TAG = "ArticulosActivity";
    public static void Save(Context context, ArrayList<Facturas_puntos> ARTI){
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDirDb(), context);
            myDataBase = myDbHelper.getWritableDatabase();
            if (ARTI.size()>0){
                SQLiteHelper.ExecuteSQL(ManagerURI.getDirDb(), context,"DELETE FROM FACTURAS_PUNTOS");
                for(int i=0;i<ARTI.size();i++){
                    Facturas_puntos a = ARTI.get(i);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("mFch" , a.getmFch());
                    contentValues.put("mClt" , a.getmClt());
                    contentValues.put("mFct" , a.getmFct());
                    contentValues.put("mPnt" , a.getmPnt());
                    contentValues.put("mRmT" , a.getmRmT());
                    myDataBase.insert("FACTURAS_PUNTOS", null, contentValues );
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            if(myDataBase != null) { myDataBase.close(); }
            if(myDbHelper != null) { myDbHelper.close(); }
        }
    }
    public static List<Facturas_puntos> get(String basedir, Context context,String Articulos) {
        List<Facturas_puntos> lst = new ArrayList<>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = myDataBase.query(true, "FACTURAS_PUNTOS", null, "mClt"+ "=?", new String[] { Articulos }, null, null, null, null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    Facturas_puntos tmp = new Facturas_puntos();
                    tmp.setmFch(cursor.getString(cursor.getColumnIndex("mFch")));
                    tmp.setmClt(cursor.getString(cursor.getColumnIndex("mClt")));
                    tmp.setmFct(cursor.getString(cursor.getColumnIndex("mFct")));
                    tmp.setmPnt(cursor.getString(cursor.getColumnIndex("mPnt")));
                    tmp.setmRmT(cursor.getString(cursor.getColumnIndex("mRmT")));
                    lst.add(tmp);
                    cursor.moveToNext();
                }
            }
        }
        catch (Exception e) { e.printStackTrace(); }
        finally
        {
            if(myDataBase != null) { myDataBase.close(); }
            if(myDbHelper != null) { myDbHelper.close(); }
        }
        return lst;
    }
}
