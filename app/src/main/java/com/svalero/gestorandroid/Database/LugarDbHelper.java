package com.svalero.gestorandroid.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.svalero.gestorandroid.Model.LugarTuristicoModel;
import com.svalero.gestorandroid.Model.RestauranteModel;

import java.util.ArrayList;
import java.util.List;

public class LugarDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "app_database_lugar";
    private static final int DATABASE_VERSION = 3; // Incrementado el número de versión

    public static final String TABLE_LUGARES = "lugares";
    public static final String COLUMN_LUGAR_ID = "id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_DESCRIPCION = "descripcion";

    public static final String CREATE_LUGARES_TABLE = "CREATE TABLE " + TABLE_LUGARES + "("
            + COLUMN_LUGAR_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_NOMBRE + " TEXT,"
            + COLUMN_DESCRIPCION + " TEXT" + ")";

    public LugarDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Database", "onCreate: " + CREATE_LUGARES_TABLE); // Agrega esta línea para imprimir la sentencia CREATE
        db.execSQL(CREATE_LUGARES_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Puedes manejar la actualización de la base de datos aquí si es necesario
    }

    public long agregarLugar(String nombre, String descripcion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, nombre);
        values.put(COLUMN_DESCRIPCION, descripcion);

        return db.insert(TABLE_LUGARES, null, values);
    }

    @SuppressLint("Range")
    public List<LugarTuristicoModel> obtenerTodosLosLugares() {
        List<LugarTuristicoModel> lugares = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_LUGARES,
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            LugarTuristicoModel lugar = new LugarTuristicoModel();
            lugar.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_LUGAR_ID)));
            lugar.setNombre(cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE)));
            lugar.setDescripcion(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION)));

            lugares.add(lugar);
        }

        cursor.close();

        return lugares;
    }

    public void actualizarLugar(LugarTuristicoModel lugar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, lugar.getNombre());
        values.put(COLUMN_DESCRIPCION, lugar.getDescripcion());

        db.update(TABLE_LUGARES, values, COLUMN_LUGAR_ID + " = ?",
                new String[]{String.valueOf(lugar.getId())});
    }

    public void borrarLugarPorNombre(String nombre) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LUGARES, COLUMN_NOMBRE + " = ?", new String[]{nombre});
    }

    @SuppressLint("Range")
    public LugarTuristicoModel obtenerLugarPorNombre(String nombre) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_LUGARES,
                null,
                COLUMN_NOMBRE + " = ?",
                new String[]{nombre},
                null,
                null,
                null
        );

        LugarTuristicoModel lugares = null;

        if (cursor.moveToFirst()) {
            lugares = new LugarTuristicoModel();
            lugares.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_LUGAR_ID)));
            lugares.setNombre(cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE)));
            lugares.setDescripcion(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION)));
        }

        cursor.close();

        return lugares;
    }


    public boolean verificarExistenciaTabla(String tableLugares) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='" + TABLE_LUGARES + "'", null);
        boolean existeTabla = cursor.getCount() > 0;
        cursor.close();
        return existeTabla;
    }
}
