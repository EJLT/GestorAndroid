package com.svalero.gestorandroid.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.svalero.gestorandroid.Model.EventoModel;
import com.svalero.gestorandroid.Model.RestauranteModel;

import java.util.ArrayList;
import java.util.List;

public class EventoDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "app_database_evento";
    private static final int DATABASE_VERSION = 3;

    // Tabla para eventos
    public static final String TABLE_EVENTOS = "eventos";
    public static final String COLUMN_EVENTO_ID = "id";
    public static final String COLUMN_TITULO = "titulo";
    public static final String COLUMN_DESCRIPCION_EVENTO = "descripcion";
    public static final String COLUMN_FECHA = "fecha";
    public static final String COLUMN_LATITUD_EVENTO = "latitud";
    public static final String COLUMN_LONGITUD_EVENTO = "longitud";
    public static final String COLUMN_IMAGENES_EVENTO = "imagenes";

    public EventoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String CREATE_EVENTOS_TABLE = "CREATE TABLE " + TABLE_EVENTOS + "("
            + COLUMN_EVENTO_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_TITULO + " TEXT,"
            + COLUMN_DESCRIPCION_EVENTO + " TEXT,"
            + COLUMN_FECHA + " TEXT,"
            + COLUMN_LATITUD_EVENTO + " REAL,"
            + COLUMN_LONGITUD_EVENTO + " REAL,"
            + COLUMN_IMAGENES_EVENTO + " TEXT" + ")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Database", "onCreate: " + CREATE_EVENTOS_TABLE);
        db.execSQL(CREATE_EVENTOS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public long agregarEvento(String titulo, String descripcion, String fecha, double latitud, double longitud, String imagenes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITULO, titulo);
        values.put(COLUMN_DESCRIPCION_EVENTO, descripcion);
        values.put(COLUMN_FECHA, fecha);
        values.put(COLUMN_LATITUD_EVENTO, latitud);
        values.put(COLUMN_LONGITUD_EVENTO, longitud);
        values.put(COLUMN_IMAGENES_EVENTO, imagenes);

        return db.insert(TABLE_EVENTOS, null, values);
    }

    // Método para obtener todos los eventos de la base de datos
    @SuppressLint("Range")
    public List<EventoModel> obtenerTodosLosEventos() {
        List<EventoModel> eventos = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_EVENTOS,
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            EventoModel evento = new EventoModel();
            evento.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_EVENTO_ID)));
            evento.setTitulo(cursor.getString(cursor.getColumnIndex(COLUMN_TITULO)));
            evento.setDescripcion(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION_EVENTO)));
            evento.setFecha(cursor.getString(cursor.getColumnIndex(COLUMN_FECHA)));
            evento.setLatitud(cursor.getDouble(cursor.getColumnIndex(COLUMN_LATITUD_EVENTO)));
            evento.setLongitud(cursor.getDouble(cursor.getColumnIndex(COLUMN_LONGITUD_EVENTO)));

            eventos.add(evento);
        }

        cursor.close();
        // No cierres la conexión con la base de datos aquí, se cerrará automáticamente después de leer

        return eventos;
    }

    // Método para actualizar un evento en la base de datos
    public void actualizarEvento(EventoModel evento) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITULO, evento.getTitulo());
        values.put(COLUMN_DESCRIPCION_EVENTO, evento.getDescripcion());
        values.put(COLUMN_FECHA, evento.getFecha());
        values.put(COLUMN_LATITUD_EVENTO, evento.getLatitud());
        values.put(COLUMN_LONGITUD_EVENTO, evento.getLongitud());

        // Actualizar el evento en la base de datos
        db.update(TABLE_EVENTOS, values, COLUMN_EVENTO_ID + " = ?", new String[]{String.valueOf(evento.getId())});
    }

    // Método para eliminar un evento de la base de datos
    public void eliminarEvento(int idEvento) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Eliminar el evento de la base de datos
        db.delete(TABLE_EVENTOS, COLUMN_EVENTO_ID + " = ?", new String[]{String.valueOf(idEvento)});
    }

    public boolean verificarExistenciaTabla(String tableEventos) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='" + TABLE_EVENTOS + "'", null);
        boolean tableExists = cursor.getCount() > 0;
        db.close();
        return tableExists;
    }

    @SuppressLint("Range")
    public List<EventoModel> obtenerEventosPorNombre(String evento) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_EVENTOS,
                null,
                COLUMN_TITULO + " = ?",
                new String[]{evento},
                null,
                null,
                null
        );

        List<EventoModel> eventosList = new ArrayList<>();

        while (cursor.moveToNext()) {
            EventoModel eventoModel = new EventoModel();
            eventoModel.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_EVENTO_ID)));
            eventoModel.setTitulo(cursor.getString(cursor.getColumnIndex(COLUMN_TITULO)));

            eventosList.add(eventoModel);
        }

        cursor.close();

        return eventosList;
    }
}
