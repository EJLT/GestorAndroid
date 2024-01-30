    package com.svalero.gestorandroid;

    import android.os.Bundle;
    import android.util.Log;

    import androidx.appcompat.app.AppCompatActivity;

    import com.example.gestorandroid.R;
    import com.mapbox.mapboxsdk.Mapbox;
    import com.mapbox.mapboxsdk.annotations.MarkerOptions;
    import com.mapbox.mapboxsdk.geometry.LatLng;
    import com.mapbox.mapboxsdk.maps.MapView;
    import com.mapbox.mapboxsdk.maps.MapboxMap;
    import com.mapbox.mapboxsdk.maps.Style;

    public class MapsActivity extends AppCompatActivity {

        private MapView mapView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Mapbox.getInstance(this, "pk.eyJ1IjoidG9rZ29hbCIsImEiOiJjbGx3eTg4MDEwMTNnM2VtaXoyNnJ1MnhtIn0.3tFAnmlHersopLNFu-WuKg");
            setContentView(R.layout.activity_maps);

            mapView = findViewById(R.id.mapView);
            mapView.onCreate(savedInstanceState);

            mapView.getMapAsync(mapboxMap -> {
                mapboxMap.setStyle(Style.MAPBOX_STREETS, style -> {
                    Log.d("MapsActivity", "Estilo de mapa cargado correctamente");
                    // Ahora puedes agregar marcadores porque el estilo está listo
                    agregarMarcador(mapboxMap, new LatLng(41.6488, -0.8891), "Zona 1", "Descripción de la Zona 1");
                    agregarMarcador(mapboxMap, new LatLng(41.6561, -0.8773), "Zona 2", "Descripción de la Zona 2");
                    agregarMarcador(mapboxMap, new LatLng(41.6584, -0.8780), "Zona 3", "Descripción de la Zona 3");
                });
            });

        }

        private void agregarMarcador(MapboxMap mapboxMap, LatLng latLng, String titulo, String descripcion) {
            // Añade un marcador al mapa
            mapboxMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(titulo)
                    .snippet(descripcion));
        }


        @Override
        public void onStart() {
            super.onStart();
            mapView.onStart();
        }

        @Override
        public void onResume() {
            super.onResume();
            mapView.onResume();
        }

        @Override
        public void onPause() {
            super.onPause();
            mapView.onPause();
        }

        @Override
        public void onStop() {
            super.onStop();
            mapView.onStop();
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            mapView.onSaveInstanceState(outState);
        }

        @Override
        public void onLowMemory() {
            super.onLowMemory();
            mapView.onLowMemory();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            mapView.onDestroy();
        }
    }
