package com.guma.desarrollo.gvm.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guma.desarrollo.gvm.POJO.HstItemFacturados;
import com.guma.desarrollo.gvm.POJO.MvstCLA;
import com.guma.desarrollo.gvm.R;

import java.util.List;

/**
 * Created by kevin.rivera on 27/02/2018.
 */

public class HistoricoArticuloAdapter extends RecyclerView.Adapter<HistoricoArticuloAdapter.ViewHolder> {

    private List<MvstCLA> listaArticulos;
    private Context context;
    private Activity activity;


    public HistoricoArticuloAdapter(List<MvstCLA> listaArticulos, Context context, Activity activity) {
        this.listaArticulos = listaArticulos;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_articulo, parent, false);
        return new HistoricoArticuloAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        MvstCLA p = listaArticulos.get(position);
        holder.articuloNombre.setText("[ " +p.getmCcl()+ " ] " + p.getmNcl());
        holder.articuloCodigo.setText(p.getmDia());
        holder.articuloPuntos.setText("Cantidad: " + p.getmCnt());
        holder.articuloExistencia.setText("C$:" + p.getmVnt() );





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
