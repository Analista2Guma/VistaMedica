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

import com.guma.desarrollo.gvm.MODEL.Facturas_Puntos_model;
import com.guma.desarrollo.gvm.POJO.Cliente;
import com.guma.desarrollo.gvm.POJO.Facturas_puntos;
import com.guma.desarrollo.gvm.POJO.Farmacias;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.activities.ClienteDetalleActivity;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by kevin.rivera on 27/02/2018.
 */

public class Farmacias_Adapter extends RecyclerView.Adapter<Farmacias_Adapter.ViewHolder> {

    private List<Farmacias> listaClientes;
    private Context context;
    private Activity activity;



    public Farmacias_Adapter(List<Farmacias> listaClientes, Context context, Activity activity) {
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
        final Farmacias cliente = listaClientes.get(position);

        holder.clienteNombre.setText(cliente.getmNFR());
        holder.clienteCodigo.setText(cliente.getmDIR());
        holder.clsPuntos.setText(cliente.getmTFR());


        holder.clienteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ClienteDetalleActivity.class);
                /*intent.putExtra("nombre", cliente.getmNam());
                intent.putExtra("codigo", cliente.getmCod());
                intent.putExtra("Dire", cliente.getmDir());*/

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
