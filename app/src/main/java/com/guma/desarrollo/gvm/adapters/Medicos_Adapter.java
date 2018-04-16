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

import com.guma.desarrollo.gvm.POJO.Farmacias;
import com.guma.desarrollo.gvm.POJO.Medicos;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.activities.DetalleContactoActivity;

import java.util.List;

/**
 * Created by kevin.rivera on 27/02/2018.
 */

public class Medicos_Adapter extends RecyclerView.Adapter<Medicos_Adapter.ViewHolder> {

    private List<Medicos> listaClientes;
    private Context context;
    private Activity activity;



    public Medicos_Adapter(List<Medicos> listaClientes, Context context, Activity activity) {
        this.listaClientes = listaClientes;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cliente, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Medicos cliente = listaClientes.get(position);

        holder.clienteNombre.setText((cliente.getmUID()).concat(" | ").concat(cliente.getM01()));
        holder.clienteCodigo.setText(cliente.getM03());
        holder.clsPuntos.setText(cliente.getM04());


        holder.clienteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetalleContactoActivity.class);
                intent.putExtra("Accion", "Editar");
                intent.putExtra("Tipo", "M");
                intent.putExtra("peNombre", cliente.getM01());
                intent.putExtra("peDireccion", cliente.getM03());
                intent.putExtra("UID", cliente.getmUID());
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

        private TextView clienteNombre;
        private TextView clienteCodigo;
        private TextView clsPuntos;
        private CardView clienteCard;

        public ViewHolder(View itemView) {
            super(itemView);

            clienteCard = itemView.findViewById(R.id.cardClienteLayout);
            clienteNombre = itemView.findViewById(R.id.cardClienteNombre);
            clienteCodigo = itemView.findViewById(R.id.cardClienteCodigo);
            clsPuntos = itemView.findViewById(R.id.cardClientePuntos);
        }

    }
}
