package com.guma.desarrollo.gvm.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.guma.desarrollo.gvm.POJO.DetalleLog;
import com.guma.desarrollo.gvm.R;
import java.util.List;

/**
 * Created by kevin.rivera on 27/02/2018.
 */

public class ReporteVisitaAdapter extends RecyclerView.Adapter<ReporteVisitaAdapter.ViewHolder> {
    private List<DetalleLog> listaArticulos;
    private Context context;
    private Activity activity;
    private String bandera;

    public ReporteVisitaAdapter(List<DetalleLog> listaArticulos, Context context, Activity activity,String Bandera) {
        this.listaArticulos = listaArticulos;
        this.context = context;
        this.activity = activity;
        this.bandera= Bandera;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_reportevisita, parent, false);


        return new ReporteVisitaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final DetalleLog articulo = listaArticulos.get(position);


        holder.articuloNombre.setText(articulo.getDescrp().toUpperCase());
        holder.Cantidad.setText(articulo.getCantidad().toUpperCase());
        holder.articulosCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {


                return false;
            }
        });


        holder.articulosCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bandera.equals("View")){

                }else{
                    new AlertDialog.Builder(activity)
                            .setMessage("¿Seguro que decea borrar el registro?")
                            .setCancelable(false)
                            .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    deleteItem(position);
                                }
                            })
                            .setNegativeButton("NO", null)
                            .show();

                }
            }
        });
    }
    void deleteItem(int index) {
        listaArticulos.remove(index);
        notifyItemRemoved(index);
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
