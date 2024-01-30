package com.svalero.gestorandroid.Database;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME_MAIN = "app_database_main";

    private static final int DATABASE_VERSION = 3;

    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME_MAIN, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        LugarDbHelper lugarDbHelper = new LugarDbHelper(context);
        if (!lugarDbHelper.verificarExistenciaTabla(LugarDbHelper.TABLE_LUGARES)) {
            db.execSQL(LugarDbHelper.CREATE_LUGARES_TABLE);
            Log.d("Database", "Tabla lugares creada correctamente");
        }

        EventoDbHelper eventoDbHelper = new EventoDbHelper(context);
        if (!eventoDbHelper.verificarExistenciaTabla(EventoDbHelper.TABLE_EVENTOS)) {
            db.execSQL(EventoDbHelper.CREATE_EVENTOS_TABLE);
            Log.d("Database", "Tabla eventos creada correctamente");
        }

        RestauranteDbHelper restauranteDbHelper = new RestauranteDbHelper(context);
        if (!restauranteDbHelper.verificarExistenciaTabla(RestauranteDbHelper.TABLE_RESTAURANTES)) {
            db.execSQL(RestauranteDbHelper.CREATE_RESTAURANTES_TABLE);
            Log.d("Database", "Tabla Restaurantes creada correctamente");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Puedes manejar la actualización de la base de datos aquí si es necesario
    }

    public EventoDbHelper getEventoDbHelper() {
        return new EventoDbHelper(context);
    }

    public RestauranteDbHelper getRestauranteDbHelper() {
        return new RestauranteDbHelper(context);
    }

    public LugarDbHelper getLugarDbHelper() {
        return new LugarDbHelper(context);
    }

    public Context getAppContext() {
        return context;
    }


}
