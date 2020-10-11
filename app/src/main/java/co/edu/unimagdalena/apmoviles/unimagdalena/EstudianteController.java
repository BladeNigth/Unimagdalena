package co.edu.unimagdalena.apmoviles.unimagdalena;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class EstudianteController {
    private Basedatos bd;
    private Context c;

    public EstudianteController(Context c){
        this.bd = new Basedatos(c,1);
        this.c = c;
    }
    public void agregarEstudiante(Estudiante e){
        try{
            SQLiteDatabase sql = bd.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(DefBD.col_codigo,e.getCodigo());
            valores.put(DefBD.col_nombre,e.getNombre());
            valores.put(DefBD.col_programa,e.getPrograma());
            long id = sql.insert(DefBD.tabla_est,null,valores);
            Toast.makeText(c, "Estudiante registrado    ", Toast.LENGTH_SHORT).show();
        }catch (Exception Ex){
            Toast.makeText(c,"Error agregar Estudiante",Toast.LENGTH_LONG).show();
        }
    }

    public  boolean buscarEstudiante(String cod){
        String args[] = new String[]{cod};
        SQLiteDatabase sqL = bd.getReadableDatabase();
        Cursor c = sqL.query(DefBD.tabla_est,null,"codigo=?",args,null,null,null);
        if(c.getCount()>0){
            bd.close();
            return true;
        }else{
            bd.close();
            return false;
        }
    }

    public Cursor allEstudiantes (){
        try{
            SQLiteDatabase sql = bd.getReadableDatabase();
            Cursor c = sql.query(DefBD.tabla_est,null,null,null,null,null,null);
            return c;
        }catch (Exception ex){
            Toast.makeText(c,"Error consulta Estudiantes",Toast.LENGTH_LONG).show();
            return  null;
        }
    }

    public Cursor allEstudiantes2 (){
        try{
            SQLiteDatabase sql = bd.getReadableDatabase();
            Cursor c = sql.rawQuery("select codigo as _id, nombre, programa from estudiante",null);
            return c;
        }catch (Exception ex){
            Toast.makeText(c,"Error consulta Estudiantes",Toast.LENGTH_LONG).show();
            return  null;
        }
    }

}
