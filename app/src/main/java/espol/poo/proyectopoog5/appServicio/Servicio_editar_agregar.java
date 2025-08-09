package espol.poo.proyectopoog5.appServicio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import espol.poo.proyectopoog5.R;

public class Servicio_editar_agregar extends AppCompatActivity {
    private EditText etNombre;
    private EditText etPrecio;
    private String modo;     //para reciclar el activity de agregar servicio  (desde el main)y editar servicio (desde el item servicio)
    private int codigoServ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_servicio_editar_agregar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNombre = findViewById(R.id.etNombreServicio);
        etPrecio = findViewById(R.id.etPrecioServicio);
        Button btnGuardar = findViewById(R.id.btnGuardarServicio);
        Button btnCancelar = findViewById(R.id.btnCancelarServicio);

        Intent intent = getIntent();
        modo = intent.getStringExtra("modo");

        if ("editar".equals(modo)) {
            codigoServ = intent.getIntExtra("codigo", -1);
            etNombre.setEnabled(false); // No se cambia el nombre
            etPrecio.setText(String.valueOf(intent.getDoubleExtra("precio", 0)));
        }

        btnGuardar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString();
            double precio = Double.parseDouble(etPrecio.getText().toString());

            Intent result = new Intent();
            if ("editar".equals(modo)) {
                result.putExtra("codigo", codigoServ);
                result.putExtra("precio", precio);
            } else {  //aqui es agregar
                result.putExtra("nombre", nombre);
                result.putExtra("precio", precio);
            }
            setResult(RESULT_OK, result);
            finish();
        });


        btnCancelar.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish(); // regresa al serviciomain
        });

    }
}
