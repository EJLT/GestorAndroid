package com.svalero.gestorandroid.View;


import com.svalero.gestorandroid.Model.LugarTuristicoModel;

import java.util.List;

public interface    LugarTuristicoView {
    void mostrarLugares(List<LugarTuristicoModel> lugares);
    void mostrarFormularioAgregar();
    void mostrarFormularioActualizar();
    void mostrarConfirmacionEliminar();
}
