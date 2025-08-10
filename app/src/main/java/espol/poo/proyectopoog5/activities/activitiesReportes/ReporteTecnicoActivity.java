package espol.poo.proyectopoog5.activities.activitiesReportes;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.modelo.OrdenServicio;

/**
public class ReporteTecnicoActivity extends AppCompatActivity {

    private EditText edtAnio;
    private Spinner spnMes;
    private Button btnConsultar;
    private RecyclerView rvReporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_tecnico);

        edtAnio = findViewById(R.id.edtAnioTecnico);
        spnMes = findViewById(R.id.spnMesTecnico);
        btnConsultar = findViewById(R.id.btnConsultarTecnico);
        rvReporte = findViewById(R.id.rvReporteTecnico);

        spnMes.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                Arrays.asList("1","2","3","4","5","6","7","8","9","10","11","12")));

        rvReporte.setLayoutManager(new LinearLayoutManager(this));

        btnConsultar.setOnClickListener(v -> {
            int anio = Integer.parseInt(edtAnio.getText().toString());
            int mes = Integer.parseInt(spnMes.getSelectedItem().toString());

            List<OrdenServicio> ordenes = obtenerOrdenes(); // Simulado

            Map<String, Double> datos = ReporteController.calcularIngresosPorTecnico(ordenes, anio, mes);
            rvReporte.setAdapter(new ReporteAdapter(datos));
        });
    }

    private List<OrdenServicio> obtenerOrdenes() {
        return new ArrayList<>(); // Aquí irá tu fuente real
    }
}
**/