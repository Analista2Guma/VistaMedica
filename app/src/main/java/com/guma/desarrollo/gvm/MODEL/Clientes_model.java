package com.guma.desarrollo.gvm.MODEL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.guma.desarrollo.gvm.DATABASE.SQLiteHelper;
import com.guma.desarrollo.gvm.POJO.Cliente;
import com.guma.desarrollo.gvm.POJO.MvtsCliente;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class Clientes_model {

    private static final String TAG = "MesActualActivity";
    public static void Save(Context context, ArrayList<Cliente> ARTI){
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDirDb(), context);
            myDataBase = myDbHelper.getWritableDatabase();
            if (ARTI.size()>0){
                SQLiteHelper.ExecuteSQL(ManagerURI.getDirDb(), context,"DELETE FROM Clientes");
                for(int i=0;i<ARTI.size();i++){
                    Cliente a = ARTI.get(i);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("mCod" , a.getmCod());
                    contentValues.put("mNam" , a.getmNam());
                    contentValues.put("mDir" , a.getmDir());
                    contentValues.put("mRuc" , a.getmRuc());
                    myDataBase.insert("Clientes", null, contentValues );
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
    public static List<Cliente> get(String basedir, Context context) {
        List<Cliente> lst = new ArrayList<>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = myDataBase.query(true, "Clientes", null, null, null, null, null, null, null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    Cliente tmp = new Cliente();
                    tmp.setmCod(cursor.getString(cursor.getColumnIndex("mCod")));
                    tmp.setmNam(cursor.getString(cursor.getColumnIndex("mNam")));
                    tmp.setmDir(cursor.getString(cursor.getColumnIndex("mDir")));
                    tmp.setmRuc(cursor.getString(cursor.getColumnIndex("mRuc")));
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
