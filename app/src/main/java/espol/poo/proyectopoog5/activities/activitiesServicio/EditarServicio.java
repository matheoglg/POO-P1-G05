package espol.poo.proyectopoog5.activities.activitiesServicio;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.modelo.Servicio;

public class EditarServicio extends AppCompatActivity {

    private EditText etNombreServ, etPrecioServ;
    private TextView tvOldNombreServicio;
    private int posicion;
    private ArrayList<Servicio> lista;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_servicio);

        etNombreServ = findViewById(R.id.etNewNombreServicio);
        etPrecioServ = findViewById(R.id.etNewPrecioServicio);
        tvOldNombreServicio =findViewById(R.id.tvOldNombreServicio);

        Button btnGuardar = findViewById(R.id.btnGuardarServicio);
        Button btnCancelar = findViewById(R.id.btnCancelarServicio);

        lista = Servicio.cargarServicios(this);
        posicion = getIntent().getIntExtra("posicion", -1);

        if (posicion != -1 && posicion < lista.size()) {
            Servicio serv = lista.get(posicion);
            etNombreServ.setText(serv.getNombreServ());
            etPrecioServ.setText(String.valueOf(serv.getPrecioActual()));
            tvOldNombreServicio.setText(serv.getNombreServ());
        } else {
            Toast.makeText(this, "Error al cargar servicio", Toast.LENGTH_SHORT).show();
            finish();
        }

        btnGuardar.setOnClickListener(v -> guardarCambios());
        btnCancelar.setOnClickListener(v -> finish());
    }

    private void guardarCambios() {
        String nombre = etNombreServ.getText().toString().trim();
        String precioStr = etPrecioServ.getText().toString().trim();

        if (nombre.isEmpty() || precioStr.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        double precio = Double.parseDouble(precioStr);

        Servicio servicio = lista.get(posicion);
        servicio.setNombreServ(nombre);
        servicio.setPrecioActual(precio);

        try {
            Servicio.guardarLista(this, lista);
            Toast.makeText(this, "Servicio actualizado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error al guardar cambios", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}