package com.svalero.gestorandroid.Evento;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gestorandroid.R;
import com.svalero.gestorandroid.Model.EventoModel;
import com.svalero.gestorandroid.Repository.EventoRepository;

import java.util.List;

public class BorrarEventoActivity extends AppCompatActivity {

    private EditText editTextNombreEventoBorrar;
    private Button btnConfirmarEliminar, btnCancelarEliminar;
    private EventoRepository eventoRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_evento);

        // Inicializar vistas
        editTextNombreEventoBorrar = findViewById(R.id.editTextNombreEventoBorrar);
        btnConfirmarEliminar = findViewById(R.id.btnConfirmarEliminar);

        // Inicializar repositorio
        eventoRepository = new EventoRepository(this);

        // Configurar el botón de confirmar eliminación
        btnConfirmarEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener el nombre del evento a borrar
                String nombreEvento = editTextNombreEventoBorrar.getText().toString();

                // Verificar si el nombre no está vacío
                if (!nombreEvento.isEmpty()) {
                    // Buscar eventos con el nombre proporcionado
                    List<EventoModel> eventosEncontrados = eventoRepository.obtenerEventosPorNombre(nombreEvento);

                    if (!eventosEncontrados.isEmpty()) {
                        // Eliminar los eventos encontrados
                        for (EventoModel evento : eventosEncontrados) {
                            eventoRepository.eliminarEvento(evento.getId());
                        }
                        Toast.makeText(BorrarEventoActivity.this, "Evento eliminado", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(BorrarEventoActivity.this, "No se encontraron eventos con ese nombre", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(BorrarEventoActivity.this, "Ingrese un nombre de evento", Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        });

    }
}


