package espol.poo.proyectopoog5.activities.activitiesServicio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.modelo.Servicio;


public class ServicioActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ServicioAdapter servicioAdapter;
    private ArrayList<Servicio> listaServicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_servicio_activity);

        cargarDatos();

        Button btnAgregarServicio = findViewById(R.id.btnAgregarServicio);
        btnAgregarServicio.setOnClickListener(v -> {
            Intent intent = new Intent(this, AgregarServicio.class);
            startActivity(intent);
        });

        Button btnVolver = findViewById(R.id.btnRegresarServicio);
        btnVolver.setOnClickListener(v -> finish());
    }

    private void llenarLista() {
        recyclerView = findViewById(R.id.rvServicios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listaServicios = Servicio.cargarServicios(this);
        servicioAdapter = new ServicioAdapter(listaServicios, this);
        recyclerView.setAdapter(servicioAdapter);
    }

    private void cargarDatos() {
        try {
            Servicio.crearDatosIniciales(this);
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
