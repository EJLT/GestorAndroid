package com.svalero.gestorandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.gestorandroid.R;
import com.svalero.gestorandroid.Model.LugarTuristicoModel;

import java.util.List;

public class LugarTuristicoAdapter extends RecyclerView.Adapter<LugarTuristicoAdapter.ViewHolder> {

    private List<LugarTuristicoModel> lugares;
    private Context context;

    public LugarTuristicoAdapter(Context context, List<LugarTuristicoModel> lugares) {
        this.context = context;
        this.lugares = lugares;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lugar_turistico, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LugarTuristicoModel lugar = lugares.get(position);
        holder.txtNombre.setText(lugar.getNombre());
        holder.txtDescripcion.setText(lugar.getDescripcion());
    }
    public void actualizarLista(List<LugarTuristicoModel> nuevaLista) {
        lugares.clear();
        lugares.addAll(nuevaLista);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return lugares.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNombre;
        public TextView txtDescripcion;

        public ViewHolder(View view) {
            super(view);
            txtNombre = view.findViewById(R.id.txtNombreLugarTuristico);
            txtDescripcion = view.findViewById(R.id.txtDescripcionLugarTuristico);
        }
    }
}
