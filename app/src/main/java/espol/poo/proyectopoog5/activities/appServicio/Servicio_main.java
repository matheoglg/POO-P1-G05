package espol.poo.proyectopoog5.activities.appServicio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.modelo.Servicio;

public class Servicio_main extends AppCompatActivity {
    private RecyclerView rvServicios;
    private ServicioAdapter adapter;
    private static List<Servicio> listaServicios = new ArrayList<>();

    private ActivityResultLauncher<Intent> launcherNuevoServicio;
    private ActivityResultLauncher<Intent> launcherEditarServicio;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Datos default
        if (listaServicios.isEmpty()) {
            listaServicios.add(new Servicio("Ser1", 10.0));
            listaServicios.add(new Servicio("Ser2", 15.0));


            // Configurar RecyclerView
            rvServicios = findViewById(R.id.rvServicios);
            rvServicios.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ServicioAdapter(listaServicios, servicio -> {
                Intent intent = new Intent(this, Servicio_editar_agregar.class);
                intent.putExtra("modo", "editar");
                intent.putExtra("codigo", servicio.getCodigoServ());
                intent.putExtra("precio", servicio.getPrecioActual());
                launcherEditarServicio.launch(intent);
            });
            rvServicios.setAdapter(adapter);

            // Botón regresar al menú
            Button btnRegresarMenu = findViewById(R.id.btnRegresarServicio);
            btnRegresarMenu.setOnClickListener(v -> finish());

            // Botón agregar servicio
            findViewById(R.id.btnAgregarServicio).setOnClickListener(v -> {
                Intent intent = new Intent(this, Servicio_editar_agregar.class);
                intent.putExtra("modo", "nuevo");
                launcherNuevoServicio.launch(intent);
            });

            // Lanzador para nuevo servicio
            launcherNuevoServicio = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            String nombre = result.getData().getStringExtra("nombre");
                            double precio = result.getData().getDoubleExtra("precio", 0);
                            listaServicios.add(new Servicio(nombre, precio));
                            adapter.notifyDataSetChanged();
                        }
                    }
            );

            // Lanzador para editar servicio
            launcherEditarServicio = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            int codigoServ = result.getData().getIntExtra("codigo", -1);
                            double nuevoPrecio = result.getData().getDoubleExtra("precio", 0);
                            for (Servicio servicio : listaServicios) {
                                if (servicio.getCodigoServ() == codigoServ) {
                                    servicio.setPrecioActual(nuevoPrecio);
                                    break;
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
            );

        }
    }
}
