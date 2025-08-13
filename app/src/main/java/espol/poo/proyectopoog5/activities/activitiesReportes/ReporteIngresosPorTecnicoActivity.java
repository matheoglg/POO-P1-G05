package espol.poo.proyectopoog5.activities.activitiesReportes;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.modelo.ReporteTecnico;

public class ReporteIngresosPorTecnicoActivity extends AppCompatActivity {

    private EditText etAnio;
    Spinner spinnerMes;
    private Button btnConsultar;
    private RecyclerView rvResultados;
    private ControladorReportes controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_ingresos_tecnico);
        spinnerMes = findViewById(R.id.spinnerMes);

        // Lista de meses del 01 al 12
        String[] meses = {
                "01", "02", "03", "04", "05", "06",
                "07", "08", "09", "10", "11", "12"
        };

        ArrayAdapter<String> adapterMeses = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                meses
        );
        adapterMeses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerMes.setAdapter(adapterMeses);


        etAnio = findViewById(R.id.etAnio);
        spinnerMes = findViewById(R.id.spinnerMes);
        btnConsultar = findViewById(R.id.btnConsultar);
        rvResultados = findViewById(R.id.rvResultados);
        rvResultados.setLayoutManager(new LinearLayoutManager(this));

        controlador = new ControladorReportes();

        btnConsultar.setOnClickListener(v -> {
            String anio = etAnio.getText().toString();
            String mes = spinnerMes.getSelectedItem().toString();

            if (anio.isEmpty()) {
                Toast.makeText(this, "Ingrese el año", Toast.LENGTH_SHORT).show();
                return;
            }

            List<ReporteTecnico> datos = controlador.obtenerIngresosPorTecnico(this, anio, mes);

            ReporteTecnicoAdapter adapter = new ReporteTecnicoAdapter(datos);
            rvResultados.setAdapter(adapter);
        });

    }
}
