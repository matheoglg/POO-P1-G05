package espol.poo.proyectopoog5.activities.activitiesTecnico;

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
import espol.poo.proyectopoog5.modelo.Tecnico;

public class AgregarTecnico extends AppCompatActivity {

    private EditText etIdTecnico;
    private EditText etNombre;
    private EditText etTelefono;
    private EditText etEspecialidad;
    private Button btnVolver;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_tecnico);

        etIdTecnico= findViewById(R.id.etIdTecnico);
        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);
        etEspecialidad = findViewById(R.id.etEspTecnico);
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

        String identificacion = etIdTecnico.getText().toString();
        String nombre = etNombre.getText().toString();
        String telefono = etTelefono.getText().toString();
        String especialidad = etEspecialidad.getText().toString();

        // Validar que los campos no estén vacíos
        if (identificacion.isEmpty() || nombre.isEmpty() || especialidad.isEmpty() || telefono.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Tecnico nuevoTecnico = new Tecnico(identificacion, nombre, telefono, especialidad);

        Log.d("AppTecnicos", nuevoTecnico.toString());

        ArrayList<Tecnico> lista = new ArrayList<>();
        try {
            lista = Tecnico.cargarTecnicos(this);
            lista.add(nuevoTecnico);
            Log.d("AppTecnicos", "Tecnico actualizado en lista");
            Tecnico.guardarLista(this, lista);
            Toast.makeText(getApplicationContext(), "Técnico guardado exitosamente", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.d("AppTecnicos", "Error al guardar técnico: " + e.getMessage());
            Toast.makeText(getApplicationContext(), "Error al guardar el técnico", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

}
