package espol.poo.proyectopoog5.activities.activitiesFactura;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.stream.Collectors;

import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.modelo.Cliente;
import espol.poo.proyectopoog5.modelo.ClienteTipo;
import espol.poo.proyectopoog5.modelo.DetalleServicioOS;
import espol.poo.proyectopoog5.modelo.Factura;
import espol.poo.proyectopoog5.modelo.OrdenServicio;

public class AgregarFactura extends AppCompatActivity {

    private Spinner spCliente, spMes;
    private EditText etAnio, etFechaCreacion;

    private ArrayList<Cliente> clientesEmpresariales;

    private final String[] MESES = {
            "01 - Enero", "02 - Febrero", "03 - Marzo",
            "04 - Abril", "05 - Mayo", "06 - Junio",
            "07 - Julio", "08 - Agosto", "09 - Septiembre",
            "10 - Octubre", "11 - Noviembre", "12 - Diciembre"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_factura);

        spCliente = findViewById(R.id.spnClienteEmpresarial);
        spMes = findViewById(R.id.spnMesEmpresarial);
        etAnio = findViewById(R.id.etAnioEmpresarial);
        etFechaCreacion = findViewById(R.id.etFechaCreacion); // dd-MM-yyyy

        cargarClientesEmpresariales();
        cargarMeses();

        Button btnGuardar = findViewById(R.id.btnGuardarFactura);
        Button btnCancelar = findViewById(R.id.btnRegresarGenerarFactura);

        btnGuardar.setOnClickListener(v -> guardarFactura());
        btnCancelar.setOnClickListener(v -> finish());
    }

    private void cargarClientesEmpresariales() {
        ArrayList<Cliente> todos = Cliente.cargarClientes(this);
        clientesEmpresariales = (ArrayList<Cliente>) todos.stream()
                .filter(c -> c.getTipoCliente() == ClienteTipo.Empresarial)
                .collect(Collectors.toList());

        ArrayAdapter<String> ad = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                clientesEmpresariales.stream().map(Cliente::getNombrePersona).toArray(String[]::new)
        );
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCliente.setAdapter(ad);
    }

    private void cargarMeses() {
        ArrayAdapter<String> ad = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, MESES);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMes.setAdapter(ad);
    }

    private void guardarFactura() {
        // Validaciones
        if (clientesEmpresariales == null || clientesEmpresariales.isEmpty()) {
            Toast.makeText(this, "No hay clientes empresariales disponibles", Toast.LENGTH_SHORT).show();
            return;
        }
        String anio = etAnio.getText().toString().trim();
        String fechaCreacion = etFechaCreacion.getText().toString().trim();
        if (anio.isEmpty() || fechaCreacion.isEmpty()) {
            Toast.makeText(this, "Complete año y fecha de creación", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!anio.matches("\\d{4}")) {
            Toast.makeText(this, "Año inválido (use yyyy)", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!fechaCreacion.matches("\\d{2}-\\d{2}-\\d{4}")) {
            Toast.makeText(this, "Fecha inválida (use dd-MM-yyyy)", Toast.LENGTH_SHORT).show();
            return;
        }

        Cliente clienteSel = clientesEmpresariales.get(spCliente.getSelectedItemPosition());
        String mesNum = MESES[spMes.getSelectedItemPosition()].substring(0, 2); // "01".."12"
        String periodo = mesNum + "-" + anio;

        // Buscar órdenes de ese cliente en el periodo (fecha OS contiene "MM-YYYY")
        ArrayList<OrdenServicio> ordenes = OrdenServicio.cargarOrdenes(this);
        ArrayList<DetalleServicioOS> detallesAcumulados = new ArrayList<>();

        for (OrdenServicio os : ordenes) {
            if (os.getCliente() != null && os.getCliente().equals(clienteSel)) {
                String fechaOS = os.getFecha(); // dd-MM-yyyy
                if (fechaOS != null && fechaOS.contains(periodo)) {

                    if (os.getListaDetalleServicios() != null) {
                        detallesAcumulados.addAll(os.getListaDetalleServicios());
                    }
                }
            }
        }

        if (detallesAcumulados.isEmpty()) {
            Toast.makeText(this, "No hay órdenes para ese cliente en el periodo", Toast.LENGTH_SHORT).show();
            return;
        }


        Factura factura = new Factura(clienteSel, fechaCreacion, mesNum, anio, detallesAcumulados);
        ArrayList<Factura> facturas = Factura.cargarFacturas(this);
        facturas.add(0, factura); // más reciente arriba

        try {
            Factura.guardarLista(this, facturas);
            Toast.makeText(this, "Factura generada", Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            Toast.makeText(this, "Error al guardar factura", Toast.LENGTH_SHORT).show();
        }
    }
}
