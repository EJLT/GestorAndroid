package com.svalero.gestorandroid;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.gestorandroid.R;
import com.svalero.gestorandroid.Implements.LugarTuristicoPresenterImpl;
import com.svalero.gestorandroid.LugaresTuristicos.ActualizarLugarActivity;
import com.svalero.gestorandroid.LugaresTuristicos.AgregarLugarActivity;
import com.svalero.gestorandroid.LugaresTuristicos.BorrarLugarActivity;
import com.svalero.gestorandroid.LugaresTuristicos.ListarLugarActivity;
import com.svalero.gestorandroid.Model.LugarTuristicoModel;
import com.svalero.gestorandroid.Presenter.LugarTuristicoPresenter;
import com.svalero.gestorandroid.View.LugarTuristicoView;

import java.util.List;
import java.util.Locale;


public class LugarTuristicoActivity extends AppCompatActivity implements LugarTuristicoView {

    private Button btnListarLugares;
    private Button btnAgregarLugarTuristico;
    private Button btnActualizarLugarTuristico;
    private Button btnEliminarLugarTuristico;

    private LugarTuristicoPresenter lugarTuristicoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar_turistico);

        btnListarLugares = findViewById(R.id.btnListarLugares);
        btnAgregarLugarTuristico = findViewById(R.id.btnAgregarLugarTuristico);
        btnActualizarLugarTuristico = findViewById(R.id.btnActualizarLugarTuristico);
        btnEliminarLugarTuristico = findViewById(R.id.btnEliminarLugarTuristico);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configurar clic de ítem de menú
        toolbar.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.action_change_language) {
                cambiarIdioma(); // Cambiar idioma al hacer clic en el botón de cambio de idioma
                return true;
            }
            return false;
        });


        // Inicializar el presentador y pasar la vista actual (this)
        lugarTuristicoPresenter = new LugarTuristicoPresenterImpl(this);

        // Configurar clics de botones
        btnListarLugares.setOnClickListener(v -> startActivity(new Intent(LugarTuristicoActivity.this, ListarLugarActivity.class)));

        btnAgregarLugarTuristico.setOnClickListener(v -> startActivity(new Intent(LugarTuristicoActivity.this, AgregarLugarActivity.class)));

        btnActualizarLugarTuristico.setOnClickListener(v -> startActivity(new Intent(LugarTuristicoActivity.this, ActualizarLugarActivity.class)));

        btnEliminarLugarTuristico.setOnClickListener(v -> startActivity(new Intent(LugarTuristicoActivity.this, BorrarLugarActivity.class)));
    }


    @Override
    public void mostrarLugares(List<LugarTuristicoModel> lugares) {
        showToast(getString(R.string.mostrar_lugares)); // Usa la cadena localizada
    }

    @Override
    public void mostrarFormularioAgregar() {
        showToast(getString(R.string.mostrar_formulario_agregar)); // Usa la cadena localizada
    }

    @Override
    public void mostrarFormularioActualizar() {
        showToast(getString(R.string.mostrar_formulario_actualizar)); // Usa la cadena localizada
    }

    @Override
    public void mostrarConfirmacionEliminar() {
        showToast(getString(R.string.mostrar_confirmacion_eliminar)); // Usa la cadena localizada
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

