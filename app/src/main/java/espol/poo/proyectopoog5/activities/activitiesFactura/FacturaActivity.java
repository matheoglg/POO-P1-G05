package espol.poo.proyectopoog5.activities.activitiesFactura;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.modelo.Factura;

public class FacturaActivity extends AppCompatActivity {

    private RecyclerView rv;
    private FacturaAdapter adapter;
    private ArrayList<Factura> facturas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_factura_activity);

        rv = findViewById(R.id.rvListaFactura);
        rv.setLayoutManager(new LinearLayoutManager(this));

        Button btnGenerar = findViewById(R.id.btnGenerarFactura);
        Button btnVolver = findViewById(R.id.btnRegresarFactura);

        btnGenerar.setOnClickListener(v ->
                startActivity(new Intent(this, AgregarFactura.class)));

        btnVolver.setOnClickListener(v -> finish());
    }

    private void llenarLista() {
        facturas = Factura.cargarFacturas(this);

        // Ordenar por fecha de creación descendente (más reciente arriba)
        Collections.sort(facturas, Comparator.comparing(Factura::getClaveOrdenacionFecha).reversed());

        adapter = new FacturaAdapter(facturas, this);
        rv.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        llenarLista();
    }
}
