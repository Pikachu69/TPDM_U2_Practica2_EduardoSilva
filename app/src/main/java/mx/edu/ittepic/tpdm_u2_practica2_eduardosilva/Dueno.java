package mx.edu.ittepic.tpdm_u2_practica2_eduardosilva;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class Dueno {
    String id, nombre, domicilio, telefono;
    BDSeguros base;
    Poliza p;

    public Dueno(String id, String n, String d, String t){
        this.id = id;
        nombre = n;
        domicilio = d;
        telefono = t;
    }
    public Dueno(Activity activity){
        base = new BDSeguros(activity,"SEGUROS",null,1);
    }

    public boolean insertar(Dueno dueno){
        try{
            SQLiteDatabase tabla = base.getWritableDatabase();
            ContentValues data = new ContentValues();
            data.put("ID",dueno.id);
            data.put("NOMBRE",dueno.nombre);
            data.put("DOMICILIO",dueno.domicilio);
            data.put("TELEFONO",dueno.telefono);
            long res = tabla.insert("DUEÑO","null",data);
            tabla.close();
            if(res<0){
                return false;
            }
        }catch (SQLiteException e){
            Log.e("ERROR: ",e.getMessage());
            return false;
        }
        return true;
    }

    public Dueno consultar(String id){
        Dueno pl = null;
        try{
            SQLiteDatabase tabla = base.getReadableDatabase();
            String SQL = "SELECT * FROM DUEÑO WHERE ID=?";
            String claves[] = {id};

            Cursor c = tabla.rawQuery(SQL,claves);
            if(c.moveToFirst()){
                pl = new Dueno(c.getString(0),c.getString(1),c.getString(2)
                        ,c.getString(3));
            }
            c.close();
            tabla.close();
        }catch (SQLiteException e){
            return null;
        }
        return pl;
    }//consultar

    public Dueno[] consulta(){
        Dueno[] resultado=null;
        try{
            SQLiteDatabase tabla = base.getReadableDatabase();
            String SQL = "SELECT * FROM DUEÑO";


            Cursor c = tabla.rawQuery(SQL,null);
            if(c.moveToFirst()){
                resultado = new Dueno[c.getCount()];
                int i=0;
                do {
                    resultado[i++] = new Dueno(c.getString(0),c.getString(1),c.getString(2)
                            ,c.getString(3));
                }while(c.moveToNext());
            }
            c.close();
            tabla.close();
        }catch (SQLiteException e){
            return null;
        }
        return resultado;
    }//consultar

    public boolean eliminar(Dueno dueno){
        try{
            SQLiteDatabase tabla = base.getWritableDatabase();
            String[] data = {""+dueno.id};
            long res = tabla.delete("DUEÑO","ID=?",data);
            tabla.close();
            if(res==0){
                return false;
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }//eliminar

    public boolean actualizar(Dueno dueno){
        try{
            SQLiteDatabase tabla = base.getWritableDatabase();
            ContentValues data = new ContentValues();
            data.put("ID",dueno.id);
            data.put("NOMBRE",dueno.nombre);
            data.put("DOMICILIO",dueno.domicilio);
            data.put("TELEFONO",dueno.telefono);
            String[] clave = {dueno.id};
            long res = tabla.update("DUEÑO",data,"ID = ?",clave);
            tabla.close();
            if(res<0){
                return false;
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }//actualizar
}
