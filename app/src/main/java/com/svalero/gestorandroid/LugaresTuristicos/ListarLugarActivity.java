    package com.svalero.gestorandroid.LugaresTuristicos;

    import android.os.Bundle;
    import android.util.Log;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import com.example.gestorandroid.R;
    import com.svalero.gestorandroid.Adapter.LugarTuristicoAdapter;
    import com.svalero.gestorandroid.Model.LugarTuristicoModel;
    import com.svalero.gestorandroid.Repository.LugarRepository;

    import java.util.ArrayList;
    import java.util.List;

    public class ListarLugarActivity extends AppCompatActivity {

        private RecyclerView recyclerView;
        private LugarTuristicoAdapter lugarTuristicoAdapter;
        private LugarRepository lugarRepository;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_listar_lugares);

            recyclerView = findViewById(R.id.recyclerViewLugares);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            lugarTuristicoAdapter = new LugarTuristicoAdapter(this, new ArrayList<>());
            recyclerView.setAdapter(lugarTuristicoAdapter);

            lugarRepository = new LugarRepository(this);

            // Actualizar la lista inicialmente
            actualizarLista();

            // Verificar si se ha actualizado un lugar
            boolean lugarActualizado = getIntent().getBooleanExtra("actualizado", false);
            if (lugarActualizado) {
                // Actualizar la lista de lugares
                actualizarLista();
            }
        }

        private void actualizarLista() {
            List<LugarTuristicoModel> lugares = lugarRepository.obtenerTodosLosLugares();
            if(lugares != null && !lugares.isEmpty()){
                lugarTuristicoAdapter.actualizarLista(lugares);
            }else{
                Log.d("ListarLugarActivity", "La lista esta vacía");
            }
        }


    }
