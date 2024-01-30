package com.svalero.gestorandroid.Repository;

import android.content.Context;
import android.util.Log;

import com.svalero.gestorandroid.Database.EventoDbHelper;
import com.svalero.gestorandroid.Model.EventoModel;

import java.util.List;

public class EventoRepository {

    private EventoDbHelper dbHelper;

    public EventoRepository(Context context) {
        dbHelper = new EventoDbHelper(context);
    }


    public long agregarEvento(EventoModel evento) {
        String imagenesString = (evento.getImagenes() != null) ? evento.getImagenes().toString() : null;
        return dbHelper.agregarEvento(
                evento.getTitulo(),
                evento.getDescripcion(),
                evento.getFecha(),
                evento.getLatitud(),
                evento.getLongitud(),
                imagenesString
        );
    }



    public List<EventoModel> obtenerTodosLosEventos() {
        List<EventoModel> eventos = dbHelper.obtenerTodosLosEventos();
        Log.d("EventoRepository", "Número de enventos obtenidos: " + eventos.size());
        return eventos;
    }

    // Actualizar un evento existente
    public void actualizarEvento(EventoModel evento) {
        Log.d("EventoRepository", "Actualizando Lugar:" + evento.getTitulo());
        dbHelper.actualizarEvento(evento);
        Log.d("EventoRepository", "Evento actualizado.");
    }

    // Eliminar un evento
    public void eliminarEvento(int idEvento) {
        dbHelper.eliminarEvento(idEvento);
    }

    public List<EventoModel> obtenerEventosPorNombre(String nombreEvento) {
        return dbHelper.obtenerEventosPorNombre(nombreEvento);
    }
}
