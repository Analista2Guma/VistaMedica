package com.guma.desarrollo.gvm.MODEL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.guma.desarrollo.gvm.DATABASE.SQLiteHelper;
import com.guma.desarrollo.gvm.POJO.Cliente;
import com.guma.desarrollo.gvm.POJO.Cuotas;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class Cuotas_model {

    private static final String TAG = "MesActualActivity";
    public static void Save(Context context, ArrayList<Cuotas> ARTI){
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDirDb(), context);
            myDataBase = myDbHelper.getWritableDatabase();
            if (ARTI.size()>0){
                SQLiteHelper.ExecuteSQL(ManagerURI.getDirDb(), context,"DELETE FROM CuotasMes");
                for(int i=0;i<ARTI.size();i++){
                    Cuotas a = ARTI.get(i);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("ARTICULO" , a.getmArti());
                    contentValues.put("DESCRIPCION" , a.getmDesc());
                    contentValues.put("CANTIDAD" , a.getmCant());
                    myDataBase.insert("CuotasMes", null, contentValues );
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
    public static List<Cuotas> get(String basedir, Context context) {
        List<Cuotas> lst = new ArrayList<>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = myDataBase.query(true, "CuotasMes", null, null, null, null, null, null, null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    Cuotas tmp = new Cuotas();
                    tmp.setmArti(cursor.getString(cursor.getColumnIndex("ARTICULO")));
                    tmp.setmDesc(cursor.getString(cursor.getColumnIndex("DESCRIPCION")));
                    tmp.setmCant(cursor.getString(cursor.getColumnIndex("CANTIDAD")));
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
