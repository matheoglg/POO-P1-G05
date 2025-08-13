package espol.poo.proyectopoog5.activities.activitiesOrdenServicio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
