package com.svalero.gestorandroid.Restaurante;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gestorandroid.R;
import com.svalero.gestorandroid.Model.RestauranteModel;
import com.svalero.gestorandroid.Repository.RestauranteRepository;

public class ActualizarRestauranteActivity extends AppCompatActivity {

    private RestauranteRepository restauranteRepository;
    private EditText editTextNombre;
    private EditText editTextDireccion;
    private Button btnActualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_restaurante);

        restauranteRepository = new RestauranteRepository(this);
        editTextNombre = findViewById(R.id.editTextNombreRestauranteActualizar);
        editTextDireccion = findViewById(R.id.editTextDireccionRestauranteActualizar);
        btnActualizar = findViewById(R.id.btnActualizarRestaurante);

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarRestaurante();
            }
        });

    }

    private void actualizarRestaurante() {
        String nombre = editTextNombre.getText().toString().trim();
        String direccion = editTextDireccion.getText().toString().trim();

        // Verificar si los campos son válidos
        if (!nombre.isEmpty() && !direccion.isEmpty()) {
            // Obtener el restaurante a través del Repository y actualizar sus datos
            RestauranteModel restaurante = restauranteRepository.obtenerRestaurantePorNombre(nombre);

            if (restaurante != null) {
                restaurante.setDireccion(direccion);
                restauranteRepository.actualizarRestaurante(restaurante);

                // Mostrar un mensaje de éxito
                Toast.makeText(ActualizarRestauranteActivity.this, "Restaurante actualizado correctamente", Toast.LENGTH_SHORT).show();

            }
        } else {
            // Manejar el caso en que los campos no estén completos
            Toast.makeText(ActualizarRestauranteActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}
