package com.svalero.gestorandroid.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.gestorandroid.R;
import com.svalero.gestorandroid.Model.EventoModel;

import java.util.List;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.ViewHolder> {

    private Context context;
    private List<EventoModel> eventos;
    private SparseBooleanArray selectedItems;

    public EventoAdapter(Context context, List<EventoModel> eventos) {
        this.context = context;
        this.eventos = eventos;
        this.selectedItems = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_evento, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EventoModel evento = eventos.get(position);
        holder.tvTitulo.setText(evento.getTitulo());
        holder.tvDescripcion.setText(evento.getDescripcion());
        holder.tvFecha.setText(evento.getFecha());
        holder.tvDisponibilidad.setText(evento.isDisponible() ? "Disponible" : "No disponible");

        // Aquí puedes manejar la lógica para resaltar elementos seleccionados
        holder.itemView.setBackgroundColor(selectedItems.get(position) ? Color.LTGRAY : Color.TRANSPARENT);
    }


    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitulo;
        public TextView tvDescripcion;
        public TextView tvFecha;
        public TextView tvDisponibilidad;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvFecha= itemView.findViewById(R.id.tvFecha);
            tvDisponibilidad = itemView.findViewById(R.id.tvDisponibilidad);
        }
    }

    // Método para marcar/desmarcar elementos
    public void toggleSelection(int position) {
        if (selectedItems.get(position, false)) {
            selectedItems.delete(position);
        } else {
            selectedItems.put(position, true);
        }
        notifyDataSetChanged();
    }

    // Método para desmarcar todos los elementos
    public void clearSelection() {
        selectedItems.clear();
        notifyDataSetChanged();
    }

    // Obtener la lista de elementos seleccionados
    public SparseBooleanArray getSelectedItems() {
        return selectedItems;
    }

    // Método para actualizar la lista
    public void actualizarLista(List<EventoModel> nuevaLista) {
        eventos.clear();
        eventos.addAll(nuevaLista);
        notifyDataSetChanged();
    }
}
