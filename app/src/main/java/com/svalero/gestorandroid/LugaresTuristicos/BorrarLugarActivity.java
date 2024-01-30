package com.svalero.gestorandroid.LugaresTuristicos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gestorandroid.R;
import com.svalero.gestorandroid.Repository.LugarRepository;

public class BorrarLugarActivity extends AppCompatActivity {

    private EditText editTextNombreBorrar;
    private Button btnBorrarLugar;
    private LugarRepository lugarRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_lugar);

        lugarRepository = new LugarRepository(this);

        editTextNombreBorrar = findViewById(R.id.editTextNombreLugarBorrar);
        btnBorrarLugar = findViewById(R.id.btnBorrarLugar);

        btnBorrarLugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarLugarTuristico();
            }
        });
    }

    private void borrarLugarTuristico() {
        String nombreLugarBorrar = editTextNombreBorrar.getText().toString();

        // Suponiendo que tienes un método en el repositorio para borrar un lugar por nombre
        lugarRepository.borrarLugarPorNombre(nombreLugarBorrar);

        Toast.makeText(this, "Lugar turístico borrado con éxito", Toast.LENGTH_SHORT).show();

        finish();
    }
}
