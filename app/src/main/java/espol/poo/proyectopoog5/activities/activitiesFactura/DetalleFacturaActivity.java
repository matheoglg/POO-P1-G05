package espol.poo.proyectopoog5.activities.activitiesFactura;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.activities.activitiesCliente.ClienteAdapter;
import espol.poo.proyectopoog5.modelo.Cliente;
import espol.poo.proyectopoog5.modelo.Factura;

public class DetalleFacturaActivity extends AppCompatActivity {

    private Factura factura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_factura);

        String idFactura = getIntent().getStringExtra("idFactura"); // ✅ recibimos ID
        ArrayList<Factura> listaFacturas = Factura.cargarFacturas(this);

        factura = null;
        for (Factura f : listaFacturas) {
            if (f.getIdFactura().equals(idFactura)) {
                factura = f;
                break;
            }
        }

        if (factura == null) {
            Toast.makeText(this, "No se pudo cargar la factura", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }


        RecyclerView rvCliente = findViewById(R.id.rvClienteDetalleFactura);
        rvCliente.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Cliente> clienteUnico = new ArrayList<>();
        clienteUnico.add(factura.getCliente());
        ClienteAdapter clienteAdapter = new ClienteAdapter(clienteUnico, this);
        rvCliente.setAdapter(clienteAdapter);


        TextView tvFecha = findViewById(R.id.tvFechadetalleFactura);
        TextView tvPeriodo = findViewById(R.id.tvPeriodoDetalleFactura);
        TextView tvTotal = findViewById(R.id.tvTotalDetalleFactura);

        tvFecha.setText(factura.getFechaCreacion());
        tvPeriodo.setText(factura.getPeriodo());
        tvTotal.setText("Total: $" + String.format("%.2f", factura.getTotal()));


        RecyclerView rvServicios = findViewById(R.id.rvServicioDetalleFactura);
        rvServicios.setLayoutManager(new LinearLayoutManager(this));
        DetalleFacturaAdapter adapterServicios = new DetalleFacturaAdapter(factura.getListaServicios());
        rvServicios.setAdapter(adapterServicios);


        Button btnRegresar = findViewById(R.id.btnVolverDetalleFactura);
        btnRegresar.setOnClickListener(v -> finish());
    }
}