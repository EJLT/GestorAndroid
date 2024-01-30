package com.svalero.gestorandroid.View;

import com.svalero.gestorandroid.Model.EventoModel;

import java.util.List;

public interface EventoView {
    void listarEventos();

    void mostrarFormularioAgregar();

    void mostrarFormularioActualizar();

    void mostrarConfirmacionEliminar();

    void mostrarEventos(List<EventoModel> eventos);
    void mostrarMensaje(String mensaje);
    // Otros métodos para actualizar la interfaz de usuario según sea necesario
}
