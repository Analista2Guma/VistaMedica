package com.guma.desarrollo.gvm.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guma.desarrollo.gvm.POJO.Articulo;
import com.guma.desarrollo.gvm.POJO.MvstCLA;
import com.guma.desarrollo.gvm.R;

import java.util.List;

/**
 * Created by kevin.rivera on 27/02/2018.
 */

public class HistoricoAdapter extends RecyclerView.Adapter<HistoricoAdapter.ViewHolder> {

    private List<MvstCLA> listaArticulos;
    private Context context;
    private Activity activity;


    public HistoricoAdapter(List<MvstCLA> listaArticulos, Context context, Activity activity) {
        this.listaArticulos = listaArticulos;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_articulo, parent, false);
        return new HistoricoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        MvstCLA p = listaArticulos.get(position);
        holder.articuloNombre.setText(p.getmDec());
        holder.articuloCodigo.setText("Codigo: " + p.getmArt());
        holder.articuloPuntos.setText("Cantidad: " + p.getmCnt());
        holder.articuloExistencia.setText(p.getmDia() );





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
