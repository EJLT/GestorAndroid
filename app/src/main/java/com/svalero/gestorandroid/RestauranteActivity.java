package com.svalero.gestorandroid;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gestorandroid.R;
import com.svalero.gestorandroid.Implements.RestaurantePresenterImpl;
import com.svalero.gestorandroid.Model.RestauranteModel;
import com.svalero.gestorandroid.Presenter.RestaurantePresenter;
import com.svalero.gestorandroid.Restaurante.ActualizarRestauranteActivity;
import com.svalero.gestorandroid.Restaurante.AgregarRestauranteActivity;
import com.svalero.gestorandroid.Restaurante.BorrarRestauranteActivity;
import com.svalero.gestorandroid.Restaurante.ListarRestauranteActivity;
import com.svalero.gestorandroid.View.RestauranteView;

import java.util.List;
import java.util.Locale;

public class RestauranteActivity extends AppCompatActivity implements RestauranteView {

    private Button btnListarRestaurantes;
    private Button btnAgregarRestaurante;
    private Button btnActualizarRestaurante;
    private Button btnEliminarRestaurante;

    private RestaurantePresenter restaurantePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante);

        btnListarRestaurantes = findViewById(R.id.btnListarRestaurantes);
        btnAgregarRestaurante = findViewById(R.id.btnAgregarRestaurante);
        btnActualizarRestaurante = findViewById(R.id.btnActualizarRestaurante);
        btnEliminarRestaurante = findViewById(R.id.btnEliminarRestaurante);

        // Inicializar el presentador y pasar la vista actual (this)
        restaurantePresenter = new RestaurantePresenterImpl(this);

        // Configurar clics de botones
        btnListarRestaurantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restaurantePresenter.listarRestaurantes();
                // Agrega un Intent para abrir la actividad correspondiente
                Intent intent = new Intent(RestauranteActivity.this, ListarRestauranteActivity.class);
                startActivity(intent);
            }
        });

        btnAgregarRestaurante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restaurantePresenter.mostrarFormularioAgregar();
                // Agrega un Intent para abrir la actividad correspondiente
                Intent intent = new Intent(RestauranteActivity.this, AgregarRestauranteActivity.class);
                startActivity(intent);
            }
        });

        btnActualizarRestaurante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restaurantePresenter.mostrarFormularioActualizar();
                // Agrega un Intent para abrir la actividad correspondiente
                Intent intent = new Intent(RestauranteActivity.this, ActualizarRestauranteActivity.class);
                startActivity(intent);
            }
        });

        btnEliminarRestaurante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restaurantePresenter.mostrarConfirmacionEliminar();
                // Agrega un Intent para abrir la actividad correspondiente
                Intent intent = new Intent(RestauranteActivity.this, BorrarRestauranteActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void mostrarRestaurantes(List<RestauranteModel> restaurantes) {
        // Implementa la lógica para mostrar la lista de restaurantes
        showToast("Listar restaurantes");
    }

    @Override
    public void mostrarFormularioAgregar() {
        showToast("Mostrar formulario agregar");
    }

    @Override
    public void mostrarFormularioActualizar() {
        showToast("Mostrar formulario actualizar");
    }

    @Override
    public void mostrarConfirmacionEliminar() {
        showToast("Mostrar confirmación eliminar");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void cambiarIdioma() {
        // Obtener el idioma actual
        String currentLanguage = Locale.getDefault().getLanguage();

        // Cambiar el idioma
        String newLanguage = (currentLanguage.equals("en")) ? "es" : "en";

        // Aplicar el cambio de idioma
        cambiarIdioma(newLanguage);

        // Recrear la actividad para aplicar los cambios
        recreate();
    }

    private void cambiarIdioma(String languageCode) {
        // Cambiar el idioma en tiempo de ejecución
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        getMenuInflater().inflate(R.menu.language_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_change_language) {
            cambiarIdioma(); // Cambiar idioma al hacer clic en el botón de cambio de idioma
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
