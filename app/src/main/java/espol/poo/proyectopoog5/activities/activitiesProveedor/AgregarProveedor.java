package espol.poo.proyectopoog5.activities.activitiesProveedor;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.modelo.Proveedor;

public class AgregarProveedor extends AppCompatActivity {

    private EditText etIdProveedor;
    private EditText etNombre;
    private EditText etTelefono;
    private EditText etDescripcion;
    private Button btnVolver;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_proveedor);

        etIdProveedor= findViewById(R.id.etIdProveedor);
        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);
        etDescripcion = findViewById(R.id.etDescripcion);
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

        String identificacion = etIdProveedor.getText().toString();
        String nombre = etNombre.getText().toString();
        String telefono = etTelefono.getText().toString();
        String descripcion = etDescripcion.getText().toString();

        // Validar que los campos no estén vacíos
        if (identificacion.isEmpty() || nombre.isEmpty() || descripcion.isEmpty() || telefono.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Proveedor nuevoProveedor = new Proveedor(identificacion, nombre, telefono, descripcion);

        Log.d("AppProveedores", nuevoProveedor.toString());

        ArrayList<Proveedor> lista = new ArrayList<>();
        try {
            lista = Proveedor.cargarProveedores(this);
            lista.add(nuevoProveedor);
            Log.d("AppProveedores", "Proveedor actualizado en lista");
            Proveedor.guardarLista(this, lista);
            Toast.makeText(getApplicationContext(), "Proveedor guardado exitosamente", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.d("AppProveedores", "Error al guardar proveedor: " + e.getMessage());
            Toast.makeText(getApplicationContext(), "Error al guardar el proveedor", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

}
