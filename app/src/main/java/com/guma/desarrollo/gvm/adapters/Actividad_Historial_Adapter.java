package com.guma.desarrollo.gvm.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guma.desarrollo.gvm.POJO.Log_Actividades;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.activities.LocationActivity;

import java.util.List;

/**
 * Created by kevin.rivera on 27/02/2018.
 */

public class Actividad_Historial_Adapter extends RecyclerView.Adapter<Actividad_Historial_Adapter.ViewHolder> {

    private List<Log_Actividades> listaClientes;
    private Context context;
    private Activity activity;



    public Actividad_Historial_Adapter(List<Log_Actividades> listaClientes, Context context, Activity activity) {
        this.listaClientes = listaClientes;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historico, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Log_Actividades cliente = listaClientes.get(position);
        final String Fecha = (String) DateFormat.format("EEEE dd 'de' MMMM 'de' yyyy ", cliente.getmFecha());
        holder.clienteNombre.setText(Fecha);
        holder.txtName.setText(cliente.getName());
        Log.d("onBindViewHolder:", "onBindViewHolder: " + cliente.getStatus());

        int name = ((cliente.getStatus().equals("0")) ? context.getResources().getColor(R.color.Status) : context.getResources().getColor(R.color.color_ok));

        holder.imgStatus.setColorFilter(name);




        holder.clienteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LocationActivity.class);
                intent.putExtra("Accion", "view");
                intent.putExtra("UID", cliente.getUID());
                activity.startActivity(intent);
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
        private TextView txtName;
        private ImageView imgStatus;
        public ViewHolder(View itemView) {
            super(itemView);
            clienteCard = itemView.findViewById(R.id.cardClienteLayout);
            clienteNombre = itemView.findViewById(R.id.cardClienteNombre);
            txtName = itemView.findViewById(R.id.idNombre);
            imgStatus = itemView.findViewById(R.id.idStatus);

        }

    }
}
