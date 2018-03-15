package com.guma.desarrollo.gvm.MODEL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.guma.desarrollo.gvm.DATABASE.SQLiteHelper;
import com.guma.desarrollo.gvm.POJO.Usuario;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class Usuario_model {

    private static final String TAG = "UsuarioModel";
    public static void Save(Context context, ArrayList<Usuario> ARTI){
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDirDb(), context);
            myDataBase = myDbHelper.getWritableDatabase();
            if (ARTI.size()>0){
                SQLiteHelper.ExecuteSQL(ManagerURI.getDirDb(), context,"DELETE FROM Usuario");
                for(int i=0;i<ARTI.size();i++){
                    Usuario a = ARTI.get(i);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("mUser" , a.getmUser());
                    contentValues.put("mNamv" , a.getmNamv());
                    contentValues.put("mPass" , a.getmPass());
                    myDataBase.insert("Usuario", null, contentValues );
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
    public static List<Usuario> get(String basedir, Context context,List<Usuario> LstUsu) {
        List<Usuario> lst = new ArrayList<>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getReadableDatabase();

            Cursor cursor = myDataBase.query(true, "Usuario", null, "mUser"+"=?" + " AND "+ " mPass" +"=?", new String[] { LstUsu.get(0).getmUser(),LstUsu.get(0).getmPass() }, null, null, null, null);
           // Cursor cursor = myDataBase.query(true, "Usuario", null, null, null, null, null, null, null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    Usuario tmp = new Usuario();
                    tmp.setmUser(cursor.getString(cursor.getColumnIndex("mUser")));
                    tmp.setmNamv(cursor.getString(cursor.getColumnIndex("mNamv")));
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
