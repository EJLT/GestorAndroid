package com.svalero.gestorandroid.Evento;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestorandroid.R;
import com.svalero.gestorandroid.Adapter.EventoAdapter;
import com.svalero.gestorandroid.Model.EventoModel;
import com.svalero.gestorandroid.Repository.EventoRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ListarEventoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EventoAdapter eventoAdapter;

    private EventoRepository eventoRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_evento);

        // Inicializa el RecyclerView y el EventoAdapter
        recyclerView = findViewById(R.id.recyclerViewEventos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        eventoAdapter = new EventoAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(eventoAdapter);

        eventoRepository = new EventoRepository(this);

        // Actualizar la lista
        actualizarLista();

        // Verificamos la actualización
        boolean eventoActualizado = getIntent().getBooleanExtra("Actualizado", false);
        if (eventoActualizado) {
            actualizarLista();
        }
    }

    private void actualizarLista() {
        List<EventoModel> eventos = eventoRepository.obtenerTodosLosEventos();
        eventoAdapter.actualizarLista(eventos);
    }


}
