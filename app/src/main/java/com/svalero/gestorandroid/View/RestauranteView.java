package com.svalero.gestorandroid.View;



import com.svalero.gestorandroid.Model.RestauranteModel;

import java.util.List;

public interface RestauranteView {
    void mostrarRestaurantes(List<RestauranteModel> restaurantes);
    void mostrarFormularioAgregar();
    void mostrarFormularioActualizar();
    void mostrarConfirmacionEliminar();
}
