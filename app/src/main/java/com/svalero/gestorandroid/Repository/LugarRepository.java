package com.svalero.gestorandroid.Repository;

import android.content.Context;
import android.util.Log;

import com.svalero.gestorandroid.Database.LugarDbHelper;
import com.svalero.gestorandroid.Model.LugarTuristicoModel;

import java.util.List;

public class LugarRepository {

    private LugarDbHelper dbHelper;

    public LugarRepository(Context context) {
        dbHelper = new LugarDbHelper(context);
    }

    public long agregarLugar(LugarTuristicoModel lugar) {
        return dbHelper.agregarLugar(lugar.getNombre(), lugar.getDescripcion());
    }

    public List<LugarTuristicoModel> obtenerTodosLosLugares() {
        List<LugarTuristicoModel> lugares = dbHelper.obtenerTodosLosLugares();
        // Agregar log para verificar la lista de lugares
        Log.d("LugarRepository", "Número de lugares obtenidos: " + lugares.size());
        return lugares;
    }

    public void actualizarLugar(LugarTuristicoModel lugar) {
        Log.d("LugarRepository", "Actualizando lugar: " + lugar.getNombre());
        dbHelper.actualizarLugar(lugar);
        Log.d("LugarRepository", "Lugar actualizado correctamente.");
    }


    public LugarTuristicoModel obtenerLugarPorNombre(String nombre){
        return dbHelper.obtenerLugarPorNombre (nombre);
    }
    public void borrarLugarPorNombre(String nombre) {
        dbHelper.borrarLugarPorNombre(nombre);
    }
}
