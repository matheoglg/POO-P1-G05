package espol.poo.proyectopoog5.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.activities.activitiesCliente.ClientesActivity;
import espol.poo.proyectopoog5.activities.activitiesFactura.FacturaActivity;
import espol.poo.proyectopoog5.activities.activitiesOrdenServicio.OrdenServicioActivity;
import espol.poo.proyectopoog5.activities.activitiesProveedor.ProveedorActivity;
import espol.poo.proyectopoog5.activities.activitiesReportes.ReporteIngresosPorServicioActivity;
import espol.poo.proyectopoog5.activities.activitiesReportes.ReporteIngresosPorTecnicoActivity;
import espol.poo.proyectopoog5.activities.activitiesTecnico.TecnicoActivity;
import espol.poo.proyectopoog5.activities.activitiesServicio.ServicioActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.menu_principal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn_salir = findViewById(R.id.btnSalir);
        ImageButton btn_AdministrarClientes = findViewById(R.id.btnAdministrarClientes);
        ImageButton btn_AdministrarProveedores = findViewById(R.id.btnAdministrarProveedores);
        ImageButton btn_AdministrarTecnicos = findViewById(R.id.btnAdministrarTecnicos);
        ImageButton btn_AdministrarServicios = findViewById(R.id.btnAdministrarServicios);
        ImageButton btn_GenerarOrden = findViewById(R.id.btnGenerarOrden);
        ImageButton btn_GenerarFactura = findViewById(R.id.btnGenerarFactura);
        ImageButton btn_ReporteServicios = findViewById(R.id.btnReporteServicios);
        ImageButton btn_ReporteTecnicos = findViewById(R.id.btnReporteTecnicos);

        btn_salir.setOnClickListener(v -> {
            finishAffinity(); // Cierra la app completamente
        });

        btn_AdministrarClientes.setOnClickListener( v -> {
            Intent intent = new Intent(this, ClientesActivity.class);
            Log.d("App","Al dar click en botón Administrar Clientes");
            this.startActivity(intent);
        });

        btn_AdministrarProveedores.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProveedorActivity.class);
            Log.d("App","Al dar click en botón Administrar Proveedores");
            this.startActivity(intent);
        });

        btn_AdministrarTecnicos.setOnClickListener(v -> {
            Intent intent = new Intent(this, TecnicoActivity.class);
            Log.d("App","Al dar click en botón Administrar Tecnicos");
            this.startActivity(intent);
        });

        btn_AdministrarServicios.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ServicioActivity.class);
            Log.d("App","Al dar click en botón Administrar Servicios");
            this.startActivity(intent);
        });

        btn_GenerarOrden.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, OrdenServicioActivity.class);
            Log.d("App","Al dar click en botón Generar Orden de Servicio");
            this.startActivity(intent);
        });

        btn_ReporteServicios.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ReporteIngresosPorServicioActivity.class);
            Log.d("App","Al dar click en botón Reporte Ingresos por Servicio");
            this.startActivity(intent);
        });

        btn_ReporteTecnicos.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ReporteIngresosPorTecnicoActivity.class);
            Log.d("App","Al dar click en botón Reporte de Atención por Técnico");
            this.startActivity(intent);
        });

        btn_GenerarFactura.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FacturaActivity.class);
            Log.d("App","Al dar click en botón Generar Factura");
            this.startActivity(intent);
        });

    }
}