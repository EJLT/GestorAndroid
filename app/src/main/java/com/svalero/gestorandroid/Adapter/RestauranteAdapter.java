package com.svalero.gestorandroid.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.gestorandroid.R;
import com.svalero.gestorandroid.Model.RestauranteModel;


import java.util.List;

public class RestauranteAdapter extends RecyclerView.Adapter<RestauranteAdapter.RestauranteViewHolder> {

    private Context context;
    private List<RestauranteModel> restaurantes;

    public RestauranteAdapter(Context context, List<RestauranteModel> restaurantes) {
        this.context = context;
        this.restaurantes = restaurantes;
    }

    @NonNull
    @Override
    public RestauranteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_restaurante, parent, false);
        return new RestauranteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestauranteViewHolder holder, int position) {
        RestauranteModel restaurante = restaurantes.get(position);
        holder.bind(restaurante);
    }

    @Override
    public int getItemCount() {
        return restaurantes.size();
    }


    public void actualizarLista(List<RestauranteModel> nuevaListaRestaurantes) {
        if (nuevaListaRestaurantes != null && !nuevaListaRestaurantes.isEmpty()) {
            restaurantes.clear();
            restaurantes.addAll(nuevaListaRestaurantes);
            notifyDataSetChanged();
        } else {
            Log.d("RestauranteAdapter", "La lista de restaurantes está vacía o nula.");
        }
    }


    public class RestauranteViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNombreRestaurante;
        private TextView txtDireccionRestaurante;

        public RestauranteViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombreRestaurante = itemView.findViewById(R.id.txtNombreRestaurante); // Asegúrate de que coincida con el ID en tu item_restaurante.xml
            txtDireccionRestaurante = itemView.findViewById(R.id.txtDireccionRestaurante); // Asegúrate de que coincida con el ID en tu item_restaurante.xml
        }

        public void bind(RestauranteModel restaurante) {
            txtNombreRestaurante.setText(restaurante.getNombre());
            txtDireccionRestaurante.setText(restaurante.getDireccion());
        }
    }
}
