package espol.poo.proyectopoog5.activities.activitiesOrdenServicio;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.modelo.OrdenServicio;

public class OrdenServicioActivity extends AppCompatActivity {

    private RecyclerView recyclerView1;
    private OrdenServicioAdapter ordenAdapter;
    private ArrayList<OrdenServicio> listaOrdenes; ///////------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_orden_servicio_activity);

        cargarDatos();

        Button btnAgregar = findViewById(R.id.btnAgregarOrdenServicio);
        btnAgregar.setOnClickListener(v -> {
            Intent intent = new Intent(this, AgregarOrdenServicio.class);
            startActivity(intent);
        });

        Button btnRegresar = findViewById(R.id.btnRegresarOrdenServicio);
        btnRegresar.setOnClickListener(v -> finish());
    }

    private void llenarLista() {
        recyclerView1 = findViewById(R.id.rvOrdenServicio);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        listaOrdenes = OrdenServicio.cargarOrdenes(this);

//<<<<<<< HEAD
        // Ordenar por fecha más reciente primero
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            listaOrdenes.sort((o1, o2) -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate fecha1 = LocalDate.parse(o1.getFecha(), formatter);
                LocalDate fecha2 = LocalDate.parse(o2.getFecha(), formatter);
                return fecha2.compareTo(fecha1); // invertir para el orden
            });
        }


        ordenAdapter = new OrdenServicioAdapter(listaOrdenes, this);
//=======
//>>>>>>> 761e6ec0ddc08315dc22927798b09c247476755d
        recyclerView1.setAdapter(ordenAdapter);
    }

    private void cargarDatos() {
        try {
            OrdenServicio.crearDatosIniciales(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        llenarLista();
    }
}
