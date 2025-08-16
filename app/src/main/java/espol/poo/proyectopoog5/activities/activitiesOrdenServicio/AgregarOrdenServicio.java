package espol.poo.proyectopoog5.activities.activitiesOrdenServicio;

import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.modelo.Cliente;
import espol.poo.proyectopoog5.modelo.DetalleServicioOS;
import espol.poo.proyectopoog5.modelo.OrdenServicio;
import espol.poo.proyectopoog5.modelo.Servicio;
import espol.poo.proyectopoog5.modelo.Tecnico;
import espol.poo.proyectopoog5.modelo.Vehiculo;

public class AgregarOrdenServicio extends AppCompatActivity {

    private Spinner spCliente, spVehiculo, spServicio, spTecnico;
    private EditText etPlaca, etCantidad, etFecha;
    private RecyclerView rvServiciosAgregados;
    private TextView tvTotal;
    private ArrayList<DetalleServicioOS> listaDetallesTemp = new ArrayList<>();
    private DetalleServicioOSAdapter adapterDetalles;
    private double totalActual = 0;

    private ArrayList<Cliente> listaClientes;
    private ArrayList<Servicio> listaServicios;
    private ArrayList<Tecnico> listaTecnicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_orden_servicio);

        spCliente = findViewById(R.id.spnClienteOS);
        spVehiculo = findViewById(R.id.spnVehiculoOS);
        spServicio = findViewById(R.id.spnNombreServicio);
        spTecnico = findViewById(R.id.spnTecnicoOS);
        etPlaca = findViewById(R.id.etPlacaOrdenServicio);
        etCantidad = findViewById(R.id.etCantidadServicioOS);
        etFecha = findViewById(R.id.etFechaOrdenServicio); // formato: dd-mm-yyyy
        tvTotal = findViewById(R.id.tvTotalOS);

        rvServiciosAgregados = findViewById(R.id.rvServiciosAgregadosOS);
        rvServiciosAgregados.setLayoutManager(new LinearLayoutManager(this));
        adapterDetalles = new DetalleServicioOSAdapter(listaDetallesTemp, position -> {
            listaDetallesTemp.remove(position);
            recalcularTotal();
            adapterDetalles.notifyDataSetChanged();
        });
        rvServiciosAgregados.setAdapter(adapterDetalles);

        cargarSpinners();

        Button btnAgregarServicio = findViewById(R.id.btnAgregarServicioOS);
        btnAgregarServicio.setOnClickListener(v -> agregarServicioTemporal());

        Button btnGuardar = findViewById(R.id.btnGuardarOS);
        btnGuardar.setOnClickListener(v -> guardarOrden());

        Button btnCancelar = findViewById(R.id.btnCancelarOS);
        btnCancelar.setOnClickListener(v -> finish());
    }

    private void cargarSpinners() {
        listaClientes = Cliente.cargarClientes(this);
        listaServicios = Servicio.cargarServicios(this);
        listaTecnicos = Tecnico.cargarTecnicos(this);

        ArrayAdapter<String> adapterClientes = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                listaClientes.stream().map(Cliente::getNombrePersona).toArray(String[]::new));
        adapterClientes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCliente.setAdapter(adapterClientes);

        ArrayAdapter<String> adapterVehiculos = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"Automóvil", "Motocicleta", "Bus"});
        adapterVehiculos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spVehiculo.setAdapter(adapterVehiculos);

        ArrayAdapter<String> adapterServicios = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                listaServicios.stream().map(Servicio::getNombreServ).toArray(String[]::new));
        adapterServicios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spServicio.setAdapter(adapterServicios);

        ArrayAdapter<String> adapterTecnicos = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                listaTecnicos.stream().map(Tecnico::getNombrePersona).toArray(String[]::new));
        adapterTecnicos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTecnico.setAdapter(adapterTecnicos);
    }

   /* private void agregarServicioTemporal() {
        if (etCantidad.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Ingrese cantidad", Toast.LENGTH_SHORT).show();
            return;
        }
        int cantidad = Integer.parseInt(etCantidad.getText().toString().trim());
        Servicio servSel = listaServicios.get(spServicio.getSelectedItemPosition());
        Tecnico tecSel = listaTecnicos.get(spTecnico.getSelectedItemPosition());

        DetalleServicioOS detalle = new DetalleServicioOS(servSel, cantidad, tecSel);
        listaDetallesTemp.add(detalle);
        recalcularTotal();
        adapterDetalles.notifyDataSetChanged();
    }*/

    private void agregarServicioTemporal() {
        // Verificar que haya servicios y técnicos cargados
        if (listaServicios == null || listaServicios.isEmpty()) {
            Toast.makeText(this, "No hay servicios disponibles", Toast.LENGTH_SHORT).show();
            return;
        }
        if (listaTecnicos == null || listaTecnicos.isEmpty()) {
            Toast.makeText(this, "No hay técnicos disponibles", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar que se haya seleccionado un  servicio y técnico
        if (spServicio.getSelectedItemPosition() == AdapterView.INVALID_POSITION) {
            Toast.makeText(this, "Seleccione un servicio", Toast.LENGTH_SHORT).show();
            return;
        }
        if (spTecnico.getSelectedItemPosition() == AdapterView.INVALID_POSITION) {
            Toast.makeText(this, "Seleccione un técnico", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar cantidad válida
        String cantidadStr = etCantidad.getText().toString().trim();
        if (cantidadStr.isEmpty()) {
            Toast.makeText(this, "Ingrese una cantidad", Toast.LENGTH_SHORT).show();
            return;
        }
        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadStr);
            if (cantidad <= 0) {
                Toast.makeText(this, "Cantidad inválida", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Cantidad inválida", Toast.LENGTH_SHORT).show();
            return;
        }

        // Si pasa todas las validaciones, agregar
        Servicio servSel = listaServicios.get(spServicio.getSelectedItemPosition());
        Tecnico tecSel = listaTecnicos.get(spTecnico.getSelectedItemPosition());

        DetalleServicioOS detalle = new DetalleServicioOS(servSel, cantidad, tecSel);
        listaDetallesTemp.add(detalle);
        recalcularTotal();
        adapterDetalles.notifyDataSetChanged();
    }


    private void recalcularTotal() {
        totalActual = listaDetallesTemp.stream().mapToDouble(DetalleServicioOS::getSubtotal).sum();
        tvTotal.setText("Total: $" + String.format("%.2f", totalActual));
    }

    private void guardarOrden() {
        Log.d("AgregarOrdenServicio", "Intentando guardar nueva orden...");

        if (listaDetallesTemp.isEmpty()) {
            Toast.makeText(this, "Agregue al menos un servicio", Toast.LENGTH_SHORT).show();
            Log.w("AgregarOrdenServicio", "No se guardó: lista de servicios vacía.");
            return;
        }
        if (etPlaca.getText().toString().trim().isEmpty() || etFecha.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Complete placa y fecha", Toast.LENGTH_SHORT).show();
            Log.w("AgregarOrdenServicio", "No se guardó: placa o fecha vacías.");
            return;
        }
        if (!etFecha.getText().toString().trim().matches("\\d{2}-\\d{2}-\\d{4}")) {
            Toast.makeText(this, "Fecha inválida (use dd-MM-yyyy)", Toast.LENGTH_SHORT).show();
            return;
        }


        try {
            Cliente clienteSel = listaClientes.get(spCliente.getSelectedItemPosition());
            String vehiculoSel = (spVehiculo.getSelectedItem().toString());
            String placa = etPlaca.getText().toString().trim();
            Vehiculo v = new Vehiculo(placa, vehiculoSel);
            String fecha = etFecha.getText().toString().trim();

            OrdenServicio nuevaOrden = new OrdenServicio(clienteSel, v, fecha);
            for (DetalleServicioOS d : listaDetallesTemp) {
                nuevaOrden.agregarDetalle(d);
            }

            ArrayList<OrdenServicio> listaOrdenes = OrdenServicio.cargarOrdenes(this);
            Log.d("AgregarOrdenServicio", "Órdenes antes de guardar: " + listaOrdenes.size());

            listaOrdenes.add(0, nuevaOrden); // agregar al inicio
            boolean guardadoOk = OrdenServicio.guardarLista(this, listaOrdenes);

            if (guardadoOk) {
                Log.d("AgregarOrdenServicio", "Orden guardada correctamente. Total ahora: " + listaOrdenes.size());
                Toast.makeText(this, "Orden guardada", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Log.e("AgregarOrdenServicio", "Fallo en guardarLista(): no devolvió true.");
                Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Log.e("AgregarOrdenServicio", "Error al guardar la orden: " + e.getMessage(), e);
            Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();
        }
    }

}