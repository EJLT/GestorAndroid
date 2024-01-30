package com.svalero.gestorandroid.Restaurante;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gestorandroid.R;
import com.svalero.gestorandroid.Adapter.RestauranteAdapter;
import com.svalero.gestorandroid.Model.RestauranteModel;
import com.svalero.gestorandroid.Repository.RestauranteRepository;

import java.util.ArrayList;
import java.util.List;

public class AgregarRestauranteActivity extends AppCompatActivity {

    private EditText editTextNombre;
    private EditText editTextDireccion;
    private Button btnGuardarRestaurante;
    private RestauranteRepository restauranteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_restaurante);

        restauranteRepository = new RestauranteRepository(getApplicationContext());

        editTextNombre = findViewById(R.id.editTextNombreRestaurante);
        editTextDireccion = findViewById(R.id.editTextDireccionRestaurante);
        btnGuardarRestaurante = findViewById(R.id.btnGuardarRestaurante);

        btnGuardarRestaurante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarRestaurante();
            }
        });
    }

    private void agregarRestaurante() {
        String nombre = editTextNombre.getText().toString();
        String direccion = editTextDireccion.getText().toString();

        RestauranteModel nuevoRestaurante = new RestauranteModel();
        nuevoRestaurante.setNombre(nombre);
        nuevoRestaurante.setDireccion(direccion);

        long resultado = restauranteRepository.agregarRestaurante(nuevoRestaurante);

        if (resultado != -1) {
            Toast.makeText(this, "Restaurante guardado", Toast.LENGTH_SHORT).show();

            // Obtener la lista actualizada de restaurantes después de la inserción
            List<RestauranteModel> nuevaLista = restauranteRepository.obtenerTodosLosRestaurantes();

            // Notificar al adaptador de la actividad RestauranteListaActivity
           Intent intent = new Intent(this, ListarRestauranteActivity.class);
           intent.putExtra("nuevaLista", new ArrayList<>(nuevaLista));
           startActivity(intent);

            finish();
        } else {
            Toast.makeText(this, "Error al guardar restaurante", Toast.LENGTH_SHORT).show();
        }
    }

}
