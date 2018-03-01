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
import com.guma.desarrollo.gvm.activities.ClienteDetalleActivity;
import com.guma.desarrollo.gvm.POJO.Cliente;

import java.util.ArrayList;

/**
 * Created by kevin.rivera on 27/02/2018.
 */

public class ClientesAdapter extends RecyclerView.Adapter<ClientesAdapter.ViewHolder> {

    private ArrayList<Cliente> listaClientes;
    private Context context;
    private Activity activity;

    public ClientesAdapter(ArrayList<Cliente> listaClientes, Context context, Activity activity) {
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
        final Cliente cliente = listaClientes.get(position);
        holder.clienteNombre.setText(cliente.getNombre());
        holder.clienteCodigo.setText(cliente.getCodigo());
        holder.clienteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ClienteDetalleActivity.class);
                intent.putExtra("nombre", cliente.getNombre());
                intent.putExtra("codigo", cliente.getCodigo().toString());
                activity.startActivity(intent);

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
        private CardView clienteCard;

        public ViewHolder(View itemView) {
            super(itemView);

            clienteCard = itemView.findViewById(R.id.cardClienteLayout);
            clienteNombre = itemView.findViewById(R.id.cardClienteNombre);
            clienteCodigo = itemView.findViewById(R.id.cardClienteCodigo);
        }

    }
}
