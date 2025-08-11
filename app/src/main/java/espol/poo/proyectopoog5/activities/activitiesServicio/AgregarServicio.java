package espol.poo.proyectopoog5.activities.activitiesServicio;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import espol.poo.proyectopoog5.R;
import android.widget.Toast;
import java.util.ArrayList;
import espol.poo.proyectopoog5.modelo.Servicio;

public class AgregarServicio extends AppCompatActivity {

    private EditText etNombreServ, etPrecioServ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_servicio);

        etNombreServ = findViewById(R.id.etNombreServicio);
        etPrecioServ = findViewById(R.id.etPrecioServicio);

        Button btnGuardar = findViewById(R.id.btnGuardarServicio);
        Button btnCancelar = findViewById(R.id.btnCancelarServicio);

        btnGuardar.setOnClickListener(v -> guardarServicio());
        btnCancelar.setOnClickListener(v -> finish());
    }

    private void guardarServicio() {
        String nombre = etNombreServ.getText().toString().trim();
        String precioStr = etPrecioServ.getText().toString().trim();

        if (nombre.isEmpty() || precioStr.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        double precio = Double.parseDouble(precioStr);
        Servicio nuevo = new Servicio(nombre, precio);

        ArrayList<Servicio> lista = Servicio.cargarServicios(this);
        lista.add(nuevo);
        try {
            Servicio.guardarLista(this, lista);
            Toast.makeText(this, "Servicio guardado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
