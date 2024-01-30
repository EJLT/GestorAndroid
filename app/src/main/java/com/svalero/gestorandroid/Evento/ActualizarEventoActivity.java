package com.svalero.gestorandroid.Evento;

import android.app.Activity;
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
import java.util.Calendar;
import java.util.Locale;

public class ActualizarEventoActivity extends AppCompatActivity {

    private EditText etTituloActualizar, etDescripcionActualizar;
    private DatePicker datePickerActualizar;
    private CheckBox checkBoxDisponibleActualizar;
    private Button btnActualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_evento);

        // Inicializar vistas
        etTituloActualizar = findViewById(R.id.etTituloActualizar);
        etDescripcionActualizar = findViewById(R.id.etDescripcionActualizar);
        datePickerActualizar = findViewById(R.id.datePickerActualizar);
        checkBoxDisponibleActualizar = findViewById(R.id.checkBoxDisponibleActualizar);
        btnActualizar = findViewById(R.id.btnActualizar);


        // Supongamos que estamos recibiendo un objeto EventoModel
        EventoModel evento = obtenerDatosDelIntent();

        // Mostrar datos actuales en las vistas
        if (evento != null) {
            etTituloActualizar.setText(evento.getTitulo());
            etDescripcionActualizar.setText(evento.getDescripcion());

            // Convertir la fecha de String a Calendar
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            try {
                calendar.setTime(dateFormat.parse(evento.getFecha()));
                datePickerActualizar.updateDate(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
            } catch (Exception e) {
                e.printStackTrace();
            }

            checkBoxDisponibleActualizar.setChecked(evento.isDisponible());
        }

        // Configurar el botón de actualización
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Aquí debes implementar la lógica para actualizar el evento
                actualizarEvento(evento);

                // Configurar el resultado antes de cerrar la actividad
                Intent resultIntent = new Intent();
                resultIntent.putExtra("Actualizado", true);
                setResult(Activity.RESULT_OK, resultIntent);

                // Mostrar un mensaje de éxito
                Toast.makeText(ActualizarEventoActivity.this, "Evento actualizado exitosamente", Toast.LENGTH_SHORT).show();

                // Puedes cerrar la actividad después de la actualización
                finish();
            }
        });
    }

    // Método ficticio para obtener datos del Intent (puedes ajustarlo según tus necesidades)
    private EventoModel obtenerDatosDelIntent() {
        return getIntent().getParcelableExtra("evento");
    }

    // Método ficticio para actualizar el evento (puedes ajustarlo según tus necesidades)
    private void actualizarEvento(EventoModel evento) {
        // Actualizar el objeto evento con los nuevos datos
        evento.setTitulo(etTituloActualizar.getText().toString());
        evento.setDescripcion(etDescripcionActualizar.getText().toString());

        // Actualizar la fecha desde el DatePicker
        Calendar calendar = Calendar.getInstance();
        calendar.set(datePickerActualizar.getYear(), datePickerActualizar.getMonth(), datePickerActualizar.getDayOfMonth());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        evento.setFecha(dateFormat.format(calendar.getTime()));

        // Actualizar la disponibilidad desde el CheckBox
        evento.setDisponible(checkBoxDisponibleActualizar.isChecked());

        // Ahora, actualizar el evento en la base de datos
        EventoRepository eventoRepository = new EventoRepository(this);
        eventoRepository.actualizarEvento(evento);

        // Ejemplo: Mostrar un mensaje de éxito
        Toast.makeText(this, "Evento actualizado exitosamente", Toast.LENGTH_SHORT).show();

        // Puedes cerrar la actividad después de la actualización
        finish();
    }

}
