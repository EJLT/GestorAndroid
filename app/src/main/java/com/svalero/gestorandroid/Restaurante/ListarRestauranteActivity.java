package com.svalero.gestorandroid.Restaurante;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestorandroid.R;
import com.svalero.gestorandroid.Adapter.RestauranteAdapter;
import com.svalero.gestorandroid.Model.RestauranteModel;
import com.svalero.gestorandroid.Repository.RestauranteRepository;

import java.util.ArrayList;
import java.util.List;

public class ListarRestauranteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RestauranteAdapter restauranteAdapter;
    private RestauranteRepository restauranteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_restaurante);

        recyclerView = findViewById(R.id.recyclerViewRestaurantes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        restauranteAdapter = new RestauranteAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(restauranteAdapter);

        restauranteRepository = new RestauranteRepository(this);

        // Actualizar la lista inicialmente
        actualizarLista();

        // Verificar si se ha actualizado un restaurante
        boolean restauranteActualizado = getIntent().getBooleanExtra("actualizado", false);
        if (restauranteActualizado) {
            // Actualizar la lista de restaurantes
            actualizarLista();
        }
    }

    private void actualizarLista() {
        List<RestauranteModel> restaurantes = restauranteRepository.obtenerTodosLosRestaurantes();
        if (restaurantes != null && !restaurantes.isEmpty()) {
            restauranteAdapter.actualizarLista(restaurantes);
        } else {
            Log.d("ListarRestauranteActivity", "La lista de restaurantes obtenida está vacía o nula.");
        }
    }





}
