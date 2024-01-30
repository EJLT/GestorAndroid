    package com.svalero.gestorandroid.Repository;

    import android.content.Context;
    import android.util.Log;

    import com.svalero.gestorandroid.Database.RestauranteDbHelper;
    import com.svalero.gestorandroid.Model.RestauranteModel;

    import java.util.List;

    public class RestauranteRepository {

        private RestauranteDbHelper dbHelper;

        public RestauranteRepository(Context context) {
            Log.d("Database", "Contexto en RestauranteRepository: " + context);
            dbHelper = new RestauranteDbHelper(context.getApplicationContext());
        }


        public long agregarRestaurante(RestauranteModel restaurante) {
            return dbHelper.agregarRestaurante(
                    restaurante.getNombre(),
                    restaurante.getDireccion()
            );
        }

        public List<RestauranteModel> obtenerTodosLosRestaurantes() {
            List<RestauranteModel> restaurante = dbHelper.obtenerTodosLosRestaurantes();
            Log.d("RestauranteRepository", "Numero de restaurantes obtenidos: " + restaurante.size());
            return restaurante;
        }

        public RestauranteModel obtenerRestaurantePorNombre(String nombre) {
            return dbHelper.obtenerRestaurantePorNombre(nombre);
        }

        public void actualizarRestaurante(RestauranteModel restaurante) {
            dbHelper.actualizarRestaurante(restaurante);
        }

        public void eliminarRestaurante(int idRestaurante) {
            dbHelper.eliminarRestaurante(idRestaurante);
        }
    }
