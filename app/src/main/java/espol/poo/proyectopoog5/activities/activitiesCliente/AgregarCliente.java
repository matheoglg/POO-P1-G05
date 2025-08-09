package espol.poo.proyectopoog5.activities.activitiesCliente;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.modelo.Cliente;

public class AgregarCliente extends AppCompatActivity {

    private EditText etIdCliente;
    private EditText etNombre;
    private EditText etDireccion;
    private EditText etTelefono;
    private Spinner spnTipoCliente;
    private Button btnVolver;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_cliente);

        etIdCliente= findViewById(R.id.etIdCliente);
        etNombre = findViewById(R.id.etNombre);
        etDireccion = findViewById(R.id.etDireccion);
        etTelefono = findViewById(R.id.etTelefono);
        spnTipoCliente = findViewById(R.id.spnTipoCliente);
        btnVolver = findViewById(R.id.btnVolver);
        btnGuardar = findViewById(R.id.btnGuardar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnVolver.setOnClickListener(v -> {
            finish();
        });
    }
    public void guardar(View view) {
        // Validación de la selección del Spinner
        if (spnTipoCliente.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Por favor, seleccione un tipo de cliente", Toast.LENGTH_SHORT).show();
            return; // Detiene la ejecución si no se ha seleccionado nada
        }

        String identificacion = etIdCliente.getText().toString();
        String nombre = etNombre.getText().toString();
        String direccion = etDireccion.getText().toString();
        String telefono = etTelefono.getText().toString();
        String tipoClienteString = spnTipoCliente.getSelectedItem().toString();

        // Validar que los campos no estén vacíos
        if (identificacion.isEmpty() || nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear el objeto Cliente con los datos recogidos del formulario
        Cliente nuevoCliente = new Cliente(identificacion, nombre, direccion, telefono, tipoClienteString);

        Log.d("AppClientes", nuevoCliente.toString());

        // Lógica para guardar el cliente en un archivo
        ArrayList<Cliente> lista = new ArrayList<>();
        try {
            // Suponiendo que tienes un método similar a Empleado.cargarEmpleados
            // que ahora carga clientes
            lista = Cliente.cargarClientes(this);
            lista.add(nuevoCliente);
            Log.d("AppClientes", "Cliente actualizado en lista");

            // Guardar la lista actualizada en el archivo
            // Suponiendo que tienes un método similar a Empleado.guardarLista
            Cliente.guardarLista(this, lista);
            Toast.makeText(getApplicationContext(), "Cliente guardado exitosamente", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.d("AppClientes", "Error al guardar cliente: " + e.getMessage());
            Toast.makeText(getApplicationContext(), "Error al guardar el cliente", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

}
