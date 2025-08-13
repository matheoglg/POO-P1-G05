package espol.poo.proyectopoog5.activities.activitiesReportes;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import espol.poo.proyectopoog5.R;

public class ReporteIngresosPorServicioActivity extends AppCompatActivity {

    private EditText etAnio2;
    Spinner spinnerMes2;
    private Button btnConsultar2;
    private RecyclerView rvResultados2;
    private ControladorReportes controlador2;
    private Button btnVolver2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_ingresos_servicio);

        spinnerMes2 = findViewById(R.id.spinnerMes2);

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

        spinnerMes2.setAdapter(adapterMeses);


        etAnio2 = findViewById(R.id.etAnio2);
        spinnerMes2 = findViewById(R.id.spinnerMes2);
        btnConsultar2 = findViewById(R.id.btnConsultar2);
        rvResultados2 = findViewById(R.id.rvResultados);
        rvResultados2.setLayoutManager(new LinearLayoutManager(this));
        btnVolver2 = findViewById(R.id.btnVolver2);
        btnVolver2.setOnClickListener(v -> {
            finish();
        });
        controlador2 = new ControladorReportes();

        btnConsultar2.setOnClickListener(v -> {
            String anio = etAnio2.getText().toString();
            String mes = spinnerMes2.getSelectedItem().toString();

            // Validar entrada mínima para evitar errores
            if(anio.isEmpty()){
                etAnio2.setError("Ingrese un año válido");
                return;
            }

            List<espol.poo.proyectopoog5.activities.activitiesReportes.ReporteServicio> datos = controlador2.obtenerIngresosPorServicio(this, anio, mes);

            ReporteServicioAdapter adapter = new ReporteServicioAdapter(datos);
            rvResultados2.setAdapter(adapter);
        });
    }
}
