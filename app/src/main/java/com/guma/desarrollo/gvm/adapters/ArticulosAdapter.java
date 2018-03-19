package com.guma.desarrollo.gvm.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.POJO.Articulo;
import com.guma.desarrollo.gvm.activities.DetalleArticulosActivity;

import java.util.List;

/**
 * Created by kevin.rivera on 27/02/2018.
 */

public class ArticulosAdapter extends RecyclerView.Adapter<ArticulosAdapter.ViewHolder> {

    private List<Articulo> listaArticulos;
    private Context context;
    private Activity activity;

    public ArticulosAdapter(List<Articulo> listaArticulos, Context context, Activity activity) {
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
        final Articulo articulo = listaArticulos.get(position);

        holder.articuloNombre.setText(articulo.getmNam());
        holder.articuloCodigo.setText("Codigo: " + articulo.getmCod());
        holder.articuloPuntos.setText("Puntos: " + articulo.getmPts());
        holder.articuloExistencia.setText("Existencia: " + articulo.getmExi() + " [ "+ articulo.getmUnd() +" ] "  );

        holder.articulosCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetalleArticulosActivity.class);
                intent.putExtra("Cod_articulo", articulo.getmCod());
                intent.putExtra("Name_articulo", articulo.getmNam());
                intent.putExtra("Art_Reglas", articulo.getmRgl());
                activity.startActivity(intent);
            }
        });
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
        private CardView articulosCard;

        public ViewHolder(View itemView) {
            super(itemView);
            articulosCard = itemView.findViewById(R.id.cardArticuloLayout);

            articuloNombre = itemView.findViewById(R.id.cardArticuloNombre);
            articuloCodigo = itemView.findViewById(R.id.cardArticuloCodigo);
            articuloPuntos = itemView.findViewById(R.id.cardArticuloPuntos);
            articuloExistencia = itemView.findViewById(R.id.cardArticuloExistencia);
        }

    }
}
