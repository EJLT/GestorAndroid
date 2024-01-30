package com.svalero.gestorandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.gestorandroid.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inflar el menú
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configurar clic de ítem de menú
        toolbar.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.action_maps) {
                // Lógica para abrir la actividad de mapas (MapsActivity)
                abrirActividad(MapsActivity.class);
                return true;
            } else if (itemId == R.id.action_camera) {
                // Lógica para abrir la cámara
                abrirCamara();
                return true;
            } else if (itemId == R.id.action_change_language) {
                cambiarIdioma(); // Cambiar idioma al hacer clic en el botón de cambio de idioma
                return true;
            }
            return false;
        });

        // Configurar clics de botones de opciones
        Button btnLugaresTuristicos = findViewById(R.id.btnLugaresTuristicos);
        btnLugaresTuristicos.setOnClickListener(v -> abrirActividad(LugarTuristicoActivity.class));
        btnLugaresTuristicos.setText(R.string.btn_lugares_turisticos);

        Button btnEventos = findViewById(R.id.btnEventos);
        btnEventos.setOnClickListener(v -> abrirActividad(EventoActivity.class));
        btnEventos.setText(R.string.btn_eventos);

        Button btnRestaurantes = findViewById(R.id.btnRestaurantes);
        btnRestaurantes.setOnClickListener(v -> abrirActividad(RestauranteActivity.class));
        btnRestaurantes.setText(R.string.btn_restaurantes);

    }

    private void abrirActividad(Class<?> actividad) {
        Intent intent = new Intent(MainActivity.this, actividad);
        startActivity(intent);
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void abrirCamara() {
        // Aquí colocar la lógica para abrir la cámara
        // En este ejemplo, se utiliza un intent para abrir la aplicación de la cámara
        Intent takePictureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
