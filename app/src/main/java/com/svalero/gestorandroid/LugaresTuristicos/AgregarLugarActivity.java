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

import java.util.ArrayList;
import java.util.List;

public class AgregarLugarActivity extends AppCompatActivity {

    private EditText editTextNombre;
    private EditText editTextDescripcion;
    private Button btnGuardarLugar;
    private LugarRepository lugarRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_lugar);

        lugarRepository = new LugarRepository(this);

        editTextNombre = findViewById(R.id.editTextNombreLugar);
        editTextDescripcion = findViewById(R.id.editTextDescripcionLugar);
        btnGuardarLugar = findViewById(R.id.btnGuardarLugar);

        btnGuardarLugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarLugarTuristico();
            }
        });
    }

    private void guardarLugarTuristico() {
        String nombre = editTextNombre.getText().toString();
        String descripcion = editTextDescripcion.getText().toString();

        LugarTuristicoModel nuevoLugar = new LugarTuristicoModel();
        nuevoLugar.setNombre(nombre);
        nuevoLugar.setDescripcion(descripcion);

        long resultado = lugarRepository.agregarLugar(nuevoLugar);

        if (resultado != -1) {
            Toast.makeText(this, "Lugar turístico guardado con éxito", Toast.LENGTH_SHORT).show();

            // Obtener la lista actualizada de lugares después de la inserción
            List<LugarTuristicoModel> nuevaLista = lugarRepository.obtenerTodosLosLugares();

            // Notificar al adaptador de la actividad ListarLugarActivity
            Intent intent = new Intent(this, ListarLugarActivity.class);
            intent.putExtra("nuevaLista", new ArrayList<>(nuevaLista));
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(this, "Error al guardar el lugar turístico", Toast.LENGTH_SHORT).show();
        }
    }

}
