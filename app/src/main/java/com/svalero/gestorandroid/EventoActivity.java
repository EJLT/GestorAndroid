package com.svalero.gestorandroid;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gestorandroid.R;

import com.svalero.gestorandroid.Evento.ActualizarEventoActivity;
import com.svalero.gestorandroid.Evento.AgregarEventoActivity;
import com.svalero.gestorandroid.Evento.BorrarEventoActivity;
import com.svalero.gestorandroid.Evento.ListarEventoActivity;
import com.svalero.gestorandroid.Implements.EventPresenterImpl;

import com.svalero.gestorandroid.Model.EventoModel;
import com.svalero.gestorandroid.Presenter.EventPresenter;
import com.svalero.gestorandroid.View.EventoView;

import java.util.List;
import java.util.Locale;


public class EventoActivity extends AppCompatActivity implements EventoView {

    private Button btnListarEventos;
    private Button btnAgregarEvento;
    private Button btnActualizarEvento;
    private Button btnEliminarEvento;

    private EventPresenter eventPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);

        // Inicializar los botones
        btnListarEventos = findViewById(R.id.btnListarEventos);
        btnAgregarEvento = findViewById(R.id.btnAgregarEvento);
        btnActualizarEvento = findViewById(R.id.btnActualizarEvento);
        btnEliminarEvento = findViewById(R.id.btnEliminarEvento);

        eventPresenter = new EventPresenterImpl(this);


        // Configurar clics de botones
        btnListarEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para listar eventos
                Log.d("EventoActivity", "onClick: Botón Listar Eventos clicado");
                eventPresenter.listarEventos();

                // Inicia la actividad para listar eventos
                Intent intent = new Intent(EventoActivity.this, ListarEventoActivity.class);
                startActivity(intent);
            }
        });


        btnAgregarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para agregar evento
                eventPresenter.mostrarFormularioAgregar();
                // Inicia la actividad para agregar evento
                Intent intent = new Intent(EventoActivity.this, AgregarEventoActivity.class);
                startActivity(intent);
            }
        });

        btnActualizarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para actualizar evento
                eventPresenter.mostrarFormularioActualizar();
                // Inicia la actividad para actualizar evento
                Intent intent = new Intent(EventoActivity.this, ActualizarEventoActivity.class);
                startActivity(intent);
            }
        });

        btnEliminarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para eliminar evento
                eventPresenter.mostrarConfirmacionEliminar();
                // Inicia la actividad para eliminar evento
                Intent intent = new Intent(EventoActivity.this, BorrarEventoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void listarEventos() {
        // Lógica para listar eventos
        // (puedes mostrar la lista en un RecyclerView, por ejemplo)
        showToast("Listar eventos");
    }

    @Override
    public void mostrarFormularioAgregar() {
        // Lógica para mostrar el formulario de agregar evento
        showToast("Mostrar formulario agregar");
    }

    @Override
    public void mostrarFormularioActualizar() {
        // Lógica para mostrar el formulario de actualizar evento
        showToast("Mostrar formulario actualizar");
    }

    @Override
    public void mostrarConfirmacionEliminar() {
        // Lógica para mostrar la confirmación de eliminación de evento
        showToast("Mostrar confirmación eliminar");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarEventos(List<EventoModel> eventos) {

    }

    @Override
    public void mostrarMensaje(String mensaje) {

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
