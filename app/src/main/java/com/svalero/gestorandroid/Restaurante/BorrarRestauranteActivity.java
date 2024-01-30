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

public class BorrarRestauranteActivity extends AppCompatActivity {

    private RestauranteRepository restauranteRepository;

    private EditText editTextNombreRestaurante;
    private Button btnBorrarRestaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_restaurante);

        restauranteRepository = new RestauranteRepository(this);

        editTextNombreRestaurante = findViewById(R.id.editTextNombreRestauranteBorrar);
        btnBorrarRestaurante = findViewById(R.id.btnBorrarRestaurante);

        btnBorrarRestaurante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrarRestaurante();
            }
        });
    }

    private void borrarRestaurante() {
        String nombreRestaurante = editTextNombreRestaurante.getText().toString().trim();

        if (!nombreRestaurante.isEmpty()) {
            RestauranteModel restaurante = restauranteRepository.obtenerRestaurantePorNombre(nombreRestaurante);

            if (restaurante != null) {
                restauranteRepository.eliminarRestaurante(restaurante.getId());
                Toast.makeText(this, "Restaurante eliminado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Restaurante no encontrado", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Ingrese el nombre del restaurante a borrar", Toast.LENGTH_SHORT).show();
        }
    }
}
