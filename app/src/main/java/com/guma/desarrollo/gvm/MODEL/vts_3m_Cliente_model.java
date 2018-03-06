package com.guma.desarrollo.gvm.MODEL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.guma.desarrollo.gvm.DATABASE.SQLiteHelper;
import com.guma.desarrollo.gvm.POJO.MvtsCliente;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class vts_3m_Cliente_model {
    private static final String TAG = "MesActualActivity";
    public static void Save(Context context, ArrayList<MvtsCliente> ARTI){
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDirDb(), context);
            myDataBase = myDbHelper.getWritableDatabase();


            if (ARTI.size()>0){

                SQLiteHelper.ExecuteSQL(ManagerURI.getDirDb(), context,"DELETE FROM vts_m3_Cliente");
                for(int i=0;i<ARTI.size();i++){
                    MvtsCliente a = ARTI.get(i);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("mRut" , a.getmRut());
                    contentValues.put("mCcl" , a.getmCcl());
                    contentValues.put("mNam" , a.getmNam());
                    contentValues.put("mRuc" , a.getmRuc());
                    contentValues.put("mHts" , a.getmHts());
                    contentValues.put("mVnt" , a.getmVnt());
                    myDataBase.insert("vts_m3_Cliente", null, contentValues );
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
    public static List<MvtsCliente>get(String basedir, Context context) {
        List<MvtsCliente> lst = new ArrayList<>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = myDataBase.query(true, "vts_m3_Cliente", null, null, null, null, null, null, null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    MvtsCliente tmp = new MvtsCliente();
                    tmp.setmRut(cursor.getString(cursor.getColumnIndex("mRut")));
                    tmp.setmCcl(cursor.getString(cursor.getColumnIndex("mCcl")));
                    tmp.setmNam(cursor.getString(cursor.getColumnIndex("mNam")));
                    tmp.setmRuc(cursor.getString(cursor.getColumnIndex("mRuc")));
                    tmp.setmHts(cursor.getInt(cursor.getColumnIndex("mHts")));
                    tmp.setmVnt(cursor.getString(cursor.getColumnIndex("mVnt")));
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
