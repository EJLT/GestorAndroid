package com.svalero.gestorandroid.Evento;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.gestorandroid.R;
import com.svalero.gestorandroid.Model.EventoModel;
import com.svalero.gestorandroid.Repository.EventoRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AgregarEventoActivity extends AppCompatActivity {

    private EditText etTitulo, etDescripcion;
    private DatePicker datePicker;
    private CheckBox checkBoxDisponible;
    private Button btnAgregar;
    private EventoRepository eventoRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_evento);

        etTitulo = findViewById(R.id.etTitulo);
        etDescripcion = findViewById(R.id.etDescripcion);
        datePicker = findViewById(R.id.datePicker);
        checkBoxDisponible = findViewById(R.id.checkBoxDisponible);
        btnAgregar = findViewById(R.id.btnAgregar);

        eventoRepository = new EventoRepository(this);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarEvento();
            }
        });
    }

    private void agregarEvento() {
        String titulo = etTitulo.getText().toString();
        String descripcion = etDescripcion.getText().toString();

        // Obtiene la fecha seleccionada del DatePicker
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();

        // Crea un objeto Calendar y configura la fecha
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        // Obtiene la fecha formateada en formato deseado
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = sdf.format(calendar.getTime());


        boolean disponible = checkBoxDisponible.isChecked();

        // Validaciones adicionales según tus requisitos

        EventoModel nuevoEvento = new EventoModel();
        nuevoEvento.setTitulo(titulo);
        nuevoEvento.setDescripcion(descripcion);
        nuevoEvento.setFecha(fecha);
        nuevoEvento.setDisponible(disponible);

        long resultado = eventoRepository.agregarEvento(nuevoEvento);

        if (resultado != -1) {
            Toast.makeText(this, "Evento guardado", Toast.LENGTH_SHORT).show();

            //Obtener lista actualizada
            List<EventoModel> nuevaLista = eventoRepository.obtenerTodosLosEventos();


            Intent intent = new Intent(this, ListarEventoActivity.class);
            intent.putExtra("nuevaLista", new ArrayList<>(nuevaLista));
            startActivity(intent);

            finish();
        }else {
            Toast.makeText(this,"Error al guardar el evento", Toast.LENGTH_SHORT).show();
        }
    }
}