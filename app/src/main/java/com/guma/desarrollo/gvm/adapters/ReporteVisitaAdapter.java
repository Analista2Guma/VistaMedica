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

import com.guma.desarrollo.gvm.POJO.Articulo;
import com.guma.desarrollo.gvm.POJO.DetalleLog;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.activities.DetalleArticulosActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin.rivera on 27/02/2018.
 */

public class ReporteVisitaAdapter extends RecyclerView.Adapter<ReporteVisitaAdapter.ViewHolder> {
    private List<DetalleLog> listaArticulos;
    private Context context;
    private Activity activity;

    public ReporteVisitaAdapter(List<DetalleLog> listaArticulos, Context context, Activity activity) {
        this.listaArticulos = listaArticulos;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_reportevisita, parent, false);


        return new ReporteVisitaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final DetalleLog articulo = listaArticulos.get(position);


        holder.articuloNombre.setText(articulo.getDescrp().toUpperCase());
        holder.Cantidad.setText(articulo.getCantidad().toUpperCase());


        holder.articulosCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //The selected card is set to colorSelected
                //holder.articulosCard.setCardBackgroundColor(context.getResources().getColor(R.color.color_Accent));
             /*   Intent intent = new Intent(context, DetalleArticulosActivity.class);
                intent.putExtra("Cod_articulo", articulo.getmCod());
                intent.putExtra("Name_articulo", articulo.getmNam());
                intent.putExtra("Art_Reglas", articulo.getmRgl());
                activity.startActivity(intent);*/

            }
        });
    }

    @Override
    public int getItemCount() {
        return listaArticulos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView articuloNombre,Cantidad;
        private CardView articulosCard;

        public ViewHolder(View itemView) {
            super(itemView);
            Cantidad = itemView.findViewById(R.id.cardArticuloExistencia);
            articulosCard = itemView.findViewById(R.id.cardArticuloLayout);
            articuloNombre = itemView.findViewById(R.id.cardArticuloNombre);

        }

    }
}
