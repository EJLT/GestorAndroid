package com.svalero.gestorandroid.Presenter;

import com.svalero.gestorandroid.Model.EventoModel;

import java.util.List;

public interface EventPresenter {
    void listarEventos();
    void mostrarFormularioAgregar();
    void mostrarFormularioActualizar();
    void mostrarConfirmacionEliminar();
}
