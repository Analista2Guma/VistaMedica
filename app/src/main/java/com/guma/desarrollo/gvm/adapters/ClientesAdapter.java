package com.guma.desarrollo.gvm.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guma.desarrollo.gvm.MODEL.Facturas_Puntos_model;
import com.guma.desarrollo.gvm.POJO.Facturas_puntos;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.activities.ClienteDetalleActivity;
import com.guma.desarrollo.gvm.POJO.Cliente;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin.rivera on 27/02/2018.
 */

public class ClientesAdapter extends RecyclerView.Adapter<ClientesAdapter.ViewHolder> {

    private List<Cliente> listaClientes;
    private Context context;
    private Activity activity;
    private List<Facturas_puntos> oFactura_Puntos;


    public ClientesAdapter(List<Cliente> listaClientes, Context context, Activity activity) {
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
        int Puntos=0;
        oFactura_Puntos = Facturas_Puntos_model.get(ManagerURI.getDirDb(),activity,cliente.getmCod());
        for(Facturas_puntos o:oFactura_Puntos){
            Puntos += Float.parseFloat(o.getmRmT());
        }

        holder.clienteNombre.setText(cliente.getmNam());
        holder.clienteCodigo.setText(cliente.getmCod());
        holder.clsPuntos.setText(String.valueOf(NumberFormat.getInstance().format(Puntos)) + " Pts.");


        holder.clienteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ClienteDetalleActivity.class);
                intent.putExtra("nombre", cliente.getmNam());
                intent.putExtra("codigo", cliente.getmCod());
                intent.putExtra("Dire", cliente.getmDir());

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
