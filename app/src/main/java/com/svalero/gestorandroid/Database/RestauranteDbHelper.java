package com.svalero.gestorandroid.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.svalero.gestorandroid.Model.RestauranteModel;

import java.util.ArrayList;
import java.util.List;

public class RestauranteDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "app_database_restaurante";
    private static final int DATABASE_VERSION = 4 ;

    public static final String TABLE_RESTAURANTES = "restaurantes";
    public static final String COLUMN_RESTAURANTE_ID = "id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_DIRECCION = "direccion";



    public static final String CREATE_RESTAURANTES_TABLE = "CREATE TABLE " + TABLE_RESTAURANTES + "("
            + COLUMN_RESTAURANTE_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_NOMBRE + " TEXT,"
            + COLUMN_DIRECCION + " TEXT" + ")";

    public RestauranteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Database", "onCreate: " + CREATE_RESTAURANTES_TABLE);
        db.execSQL(CREATE_RESTAURANTES_TABLE);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Puedes manejar la actualización de la base de datos aquí si es necesario
    }

    public long agregarRestaurante(String nombre, String direccion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, nombre);
        values.put(COLUMN_DIRECCION, direccion);

        return db.insert(TABLE_RESTAURANTES, null, values);
    }

    @SuppressLint("Range")
    public List<RestauranteModel> obtenerTodosLosRestaurantes() {
        List<RestauranteModel> restaurantes = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_RESTAURANTES,
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            RestauranteModel restaurante = new RestauranteModel();
            restaurante.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_RESTAURANTE_ID)));
            restaurante.setNombre(cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE)));
            restaurante.setDireccion(cursor.getString(cursor.getColumnIndex(COLUMN_DIRECCION)));

            restaurantes.add(restaurante);
        }

        cursor.close();

        return restaurantes;
    }
    public void actualizarRestaurante(RestauranteModel restaurante) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, restaurante.getNombre());
        values.put(COLUMN_DIRECCION, restaurante.getDireccion());

        db.update(TABLE_RESTAURANTES, values, COLUMN_RESTAURANTE_ID + " = ?",
                new String[]{String.valueOf(restaurante.getId())});
    }

    public void eliminarRestaurante(int idRestaurante) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RESTAURANTES, COLUMN_RESTAURANTE_ID + " = ?",
                new String[]{String.valueOf(idRestaurante)});
    }
    @SuppressLint("Range")
    public RestauranteModel obtenerRestaurantePorNombre(String nombre) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_RESTAURANTES,
                null,
                COLUMN_NOMBRE + " = ?",
                new String[]{nombre},
                null,
                null,
                null
        );

        RestauranteModel restaurante = null;

        if (cursor.moveToFirst()) {
            restaurante = new RestauranteModel();
            restaurante.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_RESTAURANTE_ID)));
            restaurante.setNombre(cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE)));
            restaurante.setDireccion(cursor.getString(cursor.getColumnIndex(COLUMN_DIRECCION)));
        }

        cursor.close();

        return restaurante;
    }


    public boolean verificarExistenciaTabla(String tableRestaurantes) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM sqlite_master WHERE type='table' AND name='" + TABLE_RESTAURANTES + "'", null);
        boolean tableExists = cursor.getCount() > 0;
        cursor.close();
        Log.d("Database", "Tabla restaurantes existe: " + tableExists);
        return tableExists;
    }


}
