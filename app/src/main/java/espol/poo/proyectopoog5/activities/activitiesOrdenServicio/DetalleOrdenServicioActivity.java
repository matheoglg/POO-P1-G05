package espol.poo.proyectopoog5.activities.activitiesOrdenServicio;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.activities.activitiesCliente.ClienteAdapter;
import espol.poo.proyectopoog5.modelo.Cliente;
import espol.poo.proyectopoog5.modelo.OrdenServicio;

public class DetalleOrdenServicioActivity extends AppCompatActivity {

    private TextView  tvTotal, tvTipoVehiculo, tvPlaca;
    private RecyclerView rvServicios;
    private ArrayList<OrdenServicio> listaOrdenes;
    private int posicion;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_orden_servicio);

        tvTotal = findViewById(R.id.tvTotalDetalleOS);
        rvServicios = findViewById(R.id.rvServiciosAgregadosOS);
        rvServicios.setLayoutManager(new LinearLayoutManager(this));

        tvTipoVehiculo =findViewById(R.id.tvTipoVehiculoDetalleOS);
        tvPlaca = findViewById(R.id.tvPlacaVehiculoOS);


        listaOrdenes = OrdenServicio.cargarOrdenes(this);
        posicion = getIntent().getIntExtra("posicion", -1);

        if (posicion != -1 && posicion < listaOrdenes.size()) {
            OrdenServicio orden = listaOrdenes.get(posicion);

            RecyclerView rvCliente = findViewById(R.id.rvDetalleCliente);
            rvCliente.setLayoutManager(new LinearLayoutManager(this));
            ArrayList<Cliente> clienteUnico = new ArrayList<>();
            clienteUnico.add(orden.getCliente());
            ClienteAdapter clienteAdapter = new ClienteAdapter(clienteUnico, this);
            rvCliente.setAdapter(clienteAdapter);

            tvTipoVehiculo.setText(String.valueOf(orden.getVehiculo().getTipoVehiculo()));
            tvPlaca.setText(orden.getVehiculo().getNumPlaca());


            DetalleOrdenServicioAdapter adapterServicios = new DetalleOrdenServicioAdapter(orden.getListaDetalleServicios());
            rvServicios.setAdapter(adapterServicios);

            tvTotal.setText("Total: $" + String.format("%.2f", orden.getTotal()));

        } else {
            Toast.makeText(this, "Error al cargar detalles", Toast.LENGTH_SHORT).show();
            finish();
        }

        Button btnRegresar = findViewById(R.id.btnVolverDetalleOS);
        btnRegresar.setOnClickListener(v -> finish());
    }
}