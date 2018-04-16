package com.guma.desarrollo.gvm.MODEL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.guma.desarrollo.gvm.DATABASE.SQLiteHelper;
import com.guma.desarrollo.gvm.POJO.Articulo;
import com.guma.desarrollo.gvm.POJO.Cliente;
import com.guma.desarrollo.gvm.POJO.DetalleLog;
import com.guma.desarrollo.gvm.POJO.Log_Actividades;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maryan.espinoza on 01/03/2018.
 */

public class LogActividades_model {

    private static final String TAG = "MesActualActivity";
    public static void Save(Context context, ArrayList<Log_Actividades> ARTI, List<DetalleLog> ls){
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        String ID = "";
        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDirDb(), context);
            myDataBase = myDbHelper.getWritableDatabase();
            if (ARTI.size()>0){
                for(int i=0;i<ARTI.size();i++){
                    Log_Actividades a = ARTI.get(i);
                    ContentValues contentValues = new ContentValues();
                    ID = a.getUID();
                    contentValues.put("IDRPT" , a.getUID());
                    contentValues.put("CLIENTE" , a.getmCliente());
                    contentValues.put("LONGITUD" , a.getmLogitud());
                    contentValues.put("LATITUD" , a.getmLatitud());
                    contentValues.put("RUTA" , a.getmRuta());
                    contentValues.put("COMENTARIO" , a.getmComentario());
                    contentValues.put("FECHA" , a.getmFecha());
                    myDataBase.insert("log_actividades", null, contentValues );
                }

                if (ls.size()>0){
                    for(int n=0;n<ls.size();n++){
                        DetalleLog a = ls.get(n);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("IDRPT" , ID);
                        contentValues.put("ARTICULOS" , a.getArticulos());
                        contentValues.put("DESCRI" , a.getDescrp());
                        contentValues.put("CANTIDAD" , a.getCantidad());
                        myDataBase.insert("log_actividades_detalle", null, contentValues );

                    }
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

    // TODO: 06/04/2018 QUEDA PENDIENTE MANDARLO AL SERVICOR 
    public static List<Log_Actividades> get(String basedir, Context context, String Cls) {
        List<Log_Actividades> lst = new ArrayList<>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = myDataBase.query(true, "log_actividades", null, "CLIENTE"+ "=?", new String[] { Cls }, null, null, null, null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    Log_Actividades tmp = new Log_Actividades();
                    tmp.setUID(cursor.getString(cursor.getColumnIndex("IDRPT")));
                    tmp.setmCliente(cursor.getString(cursor.getColumnIndex("CLIENTE")));
                    tmp.setmLogitud(cursor.getString(cursor.getColumnIndex("LONGITUD")));
                    tmp.setmLatitud(cursor.getString(cursor.getColumnIndex("LATITUD")));
                    tmp.setmComentario(cursor.getString(cursor.getColumnIndex("COMENTARIO")));
                    tmp.setmRuta(cursor.getString(cursor.getColumnIndex("RUTA")));
                    tmp.setmFecha(cursor.getLong(cursor.getColumnIndex("FECHA")));
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

    public static List<DetalleLog> get_detalle(String basedir, Context context) {
        List<DetalleLog> lst = new ArrayList<>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = myDataBase.query(true, "log_actividades_detalle", null, null, null, null, null, null, null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();

                while(!cursor.isAfterLast()) {

                    DetalleLog tmp = new DetalleLog();
                    Log.i("LlenarDetalle:", "Llenar: " + cursor.getColumnIndex("ARTICULOS"));
                    tmp.setID(cursor.getString(cursor.getColumnIndex("IDRPT")));
                    tmp.setArticulos(cursor.getString(cursor.getColumnIndex("ARTICULOS")));
                    tmp.setDescrp(cursor.getString(cursor.getColumnIndex("DESCRI")));
                    tmp.setCantidad(cursor.getString(cursor.getColumnIndex("CANTIDAD")));
                    lst.add(tmp);
                    cursor.moveToNext();

                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.i("LlenarDetalle:", "Llenar: " + e.getMessage());
        }
        finally
        {
            if(myDataBase != null) { myDataBase.close(); }
            if(myDbHelper != null) { myDbHelper.close(); }
        }
        return lst;
    }
}
