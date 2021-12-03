package com.example.practica4_yharyarias.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import java.util.ArrayList;
import java.util.List;

public class Conexion extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "MinticC4B";
    private static final String TABLA_ARTICULOS = "articulos";

    public Conexion(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLA_ARTICULOS + "(" +
                        "codigo INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nombre TEXT NOT NULL," +
                        "precio DECIMAL NOT NULL," +
                        "descripcion TEXT" + ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLA_ARTICULOS);
        onCreate(sqLiteDatabase);
    }

    public void agregarArticulo(String nombre, String descripcion, String precio) {
        ContentValues cv = new ContentValues();
        cv.put("nombre", nombre);
        cv.put("descripcion", descripcion);
        cv.put("precio", precio);
        this.getWritableDatabase().insert("articulos", null, cv);
    }

    public void eliminarArticulo(String codigo) {
        this.getWritableDatabase().delete("articulos", "codigo = ?", new String[]{codigo.trim()});
    }

    public void actualizarArticulo(String codigo, String descripcion, String precio) {
        ContentValues cv = new ContentValues();
        cv.put("descripcion", descripcion);
        cv.put("precio", precio);
        this.getWritableDatabase().update("articulos", cv, "codigo = ?", new String[]{codigo.trim()});
    }

    public List<Articulo> consultarArticulos() {
        List<Articulo> articulos = new ArrayList<Articulo>();

        Cursor result = this.getWritableDatabase().query("articulos", new String[]{"codigo", "nombre", "descripcion", "precio"}, null, null, null, null, null);
        while (result.moveToNext()) {
            Articulo nuevoArticulo = new Articulo(
                    result.getInt((int) result.getColumnIndex("codigo")),
                    result.getString((int) result.getColumnIndex("nombre")),
                    result.getString((int) result.getColumnIndex("descripcion")),
                    result.getFloat((int) result.getColumnIndex("precio"))
            );
            articulos.add(nuevoArticulo);
        }

        return articulos;
    }
}
