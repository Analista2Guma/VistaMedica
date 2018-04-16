package com.guma.desarrollo.gvm.MODEL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.guma.desarrollo.gvm.DATABASE.SQLiteHelper;
import com.guma.desarrollo.gvm.POJO.Farmacias;
import com.guma.desarrollo.gvm.POJO.Llaves;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class Llaves_model {

    private static final String TAG = "Llaves_model_log";
    public static void Save(Context context, ArrayList<Llaves> ARTI){
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDirDb(), context);
            myDataBase = myDbHelper.getWritableDatabase();
            if (ARTI.size()>0){
                SQLiteHelper.ExecuteSQL(ManagerURI.getDirDb(), context,"DELETE FROM Llaves");

                for(int i=0;i<ARTI.size();i++){
                    Llaves a = ARTI.get(i);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("RUTA" , a.getmRut());
                    contentValues.put("FARMACIAS" , a.getmFar());
                    contentValues.put("MEDICOS" , a.getmMed());
                    contentValues.put("REPORTES" , a.getmRpt());
                    myDataBase.insert("Llaves", null, contentValues );
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
    public static List<Llaves> get(String basedir, Context context) {
        List<Llaves> lst = new ArrayList<>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = myDataBase.query(true, "Llaves", null, null, null, null, null, null, null);

            if(cursor.getCount() > 0) {

                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    Llaves tmp = new Llaves();
                    tmp.setmRut(cursor.getString(cursor.getColumnIndex("RUTA")));
                    tmp.setmFar(cursor.getString(cursor.getColumnIndex("FARMACIAS")));
                    tmp.setmMed(cursor.getString(cursor.getColumnIndex("MEDICOS")));
                    tmp.setmRpt(cursor.getString(cursor.getColumnIndex("REPORTES")));
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

    public static void updtID(String basedir, Context context, Integer Valor,String Ruta,String Type) {
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getWritableDatabase();
            SQLiteHelper.ExecuteSQL(ManagerURI.getDirDb(), context,"UPDATE Llaves SET "+Type+" = '"+Valor+"' WHERE RUTA ='"+Ruta+"'");
        }
        catch (Exception e) { e.printStackTrace(); }
        finally
        {
            if(myDataBase != null) { myDataBase.close(); }
            if(myDbHelper != null) { myDbHelper.close(); }
        }
    }
}
