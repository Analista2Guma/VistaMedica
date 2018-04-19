package com.guma.desarrollo.gvm.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guma.desarrollo.gvm.POJO.Log_Actividades;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.activities.LocationActivity;

import java.util.List;

/**
 * Created by kevin.rivera on 27/02/2018.
 */

public class Actividad_Adapter extends RecyclerView.Adapter<Actividad_Adapter.ViewHolder> {

    private List<Log_Actividades> listaClientes;
    private Context context;
    private Activity activity;



    public Actividad_Adapter(List<Log_Actividades> listaClientes, Context context, Activity activity) {
        this.listaClientes = listaClientes;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_actividad_cliente, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Log_Actividades cliente = listaClientes.get(position);
        final String Fecha = (String) DateFormat.format("EEEE dd 'de' MMMM 'de' yyyy HH:mm:ss 's'", cliente.getmFecha());
        holder.clienteNombre.setText(Fecha);
        holder.clienteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LocationActivity.class);
                intent.putExtra("Accion", "view");
                intent.putExtra("UID", cliente.getUID());
                activity.startActivity(intent);
                activity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView clienteCard;
        private TextView clienteNombre;



        public ViewHolder(View itemView) {
            super(itemView);
            clienteCard = itemView.findViewById(R.id.cardClienteLayout);
            clienteNombre = itemView.findViewById(R.id.cardClienteNombre);

        }

    }
}
