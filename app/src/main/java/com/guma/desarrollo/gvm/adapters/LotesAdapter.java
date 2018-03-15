package com.guma.desarrollo.gvm.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guma.desarrollo.gvm.POJO.Lotes;
import com.guma.desarrollo.gvm.R;

import java.util.List;

/**
 * Created by kevin.rivera on 27/02/2018.
 */

public class LotesAdapter extends RecyclerView.Adapter<LotesAdapter.ViewHolder> {

    private List<Lotes> listaArticulos;
    private Context context;
    private Activity activity;


    public LotesAdapter(List<Lotes> listaArticulos, Context context, Activity activity) {
        this.listaArticulos = listaArticulos;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_articulo, parent, false);
        return new LotesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Lotes p = listaArticulos.get(position);

        holder.articuloCodigo.setText("Lote: " + p.getmLot());
        holder.articuloPuntos.setText("Fecha Vencimiento: " +p.getmFvc());
        holder.articuloExistencia.setText("Cantidad: " + p.getmCds());
    }

    @Override
    public int getItemCount() {
        return listaArticulos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView articuloNombre;
        private TextView articuloCodigo;
        private TextView articuloPuntos;
        private TextView articuloExistencia;

        public ViewHolder(View itemView) {
            super(itemView);
            articuloNombre = itemView.findViewById(R.id.cardArticuloNombre);
            articuloCodigo = itemView.findViewById(R.id.cardArticuloCodigo);
            articuloPuntos = itemView.findViewById(R.id.cardArticuloPuntos);
            articuloExistencia = itemView.findViewById(R.id.cardArticuloExistencia);
        }

    }
}
