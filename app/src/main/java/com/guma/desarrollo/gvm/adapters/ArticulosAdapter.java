package com.guma.desarrollo.gvm.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.POJO.Articulo;

import java.util.ArrayList;

/**
 * Created by kevin.rivera on 27/02/2018.
 */

public class ArticulosAdapter extends RecyclerView.Adapter<ArticulosAdapter.ViewHolder> {

    private ArrayList<Articulo> listaArticulos;
    private Context context;
    private Activity activity;

    public ArticulosAdapter(ArrayList<Articulo> listaArticulos, Context context, Activity activity) {
        this.listaArticulos = listaArticulos;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_articulo, parent, false);
        return new ArticulosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Articulo articulo = listaArticulos.get(position);

        holder.articuloNombre.setText(articulo.getNombre());
        holder.articuloCodigo.setText("Codigo: " + articulo.getCodigo());
        holder.articuloPuntos.setText("Puntos: " + String.valueOf(articulo.getPuntos()));
    }

    @Override
    public int getItemCount() {
        return listaArticulos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView articuloNombre;
        private TextView articuloCodigo;
        private TextView articuloPuntos;

        public ViewHolder(View itemView) {
            super(itemView);

            articuloNombre = itemView.findViewById(R.id.cardArticuloNombre);
            articuloCodigo = itemView.findViewById(R.id.cardArticuloCodigo);
            articuloPuntos = itemView.findViewById(R.id.cardArticuloPuntos);
        }

    }
}
