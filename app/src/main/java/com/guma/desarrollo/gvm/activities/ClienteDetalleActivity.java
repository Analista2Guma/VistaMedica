package com.guma.desarrollo.gvm.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.guma.desarrollo.gvm.R;

public class ClienteDetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_detalle);

        TextView textViewNombre = findViewById(R.id.detalleClienteNombre);
        TextView textViewCodigo = findViewById(R.id.detalleClienteCodigo);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            textViewNombre.setText(bundle.getString("nombre"));
            textViewCodigo.setText(bundle.getString("codigo"));
        }
    }
}
