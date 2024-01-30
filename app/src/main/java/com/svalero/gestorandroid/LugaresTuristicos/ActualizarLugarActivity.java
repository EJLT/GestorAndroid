package com.svalero.gestorandroid.LugaresTuristicos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gestorandroid.R;
import com.svalero.gestorandroid.Model.LugarTuristicoModel;
import com.svalero.gestorandroid.Repository.LugarRepository;

public class ActualizarLugarActivity extends AppCompatActivity {

    private EditText editTextNombreActualizar;
    private EditText editTextDescripcionActualizar;
    private Button btnActualizarLugar;
    private LugarRepository lugarRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_lugar);

        lugarRepository = new LugarRepository(this);
        editTextNombreActualizar = findViewById(R.id.editTextNombreLugarActualizar);
        editTextDescripcionActualizar = findViewById(R.id.editTextDescripcionLugarActualizar);
        btnActualizarLugar = findViewById(R.id.btnActualizarLugar);

        btnActualizarLugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarLugarTuristico();
            }
        });
    }

    private void actualizarLugarTuristico() {
        String Nombre = editTextNombreActualizar.getText().toString();
        String Descripcion = editTextDescripcionActualizar.getText().toString();

        if (!Nombre.isEmpty() && !Descripcion.isEmpty()) {
            LugarTuristicoModel lugares = lugarRepository.obtenerLugarPorNombre(Nombre);

            if (lugares != null) {
                lugares.setDescripcion(Descripcion);
                lugarRepository.actualizarLugar(lugares);

                Toast.makeText(ActualizarLugarActivity.this, "Lugar Actualizado ", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(ActualizarLugarActivity.this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
        }


    }
}

