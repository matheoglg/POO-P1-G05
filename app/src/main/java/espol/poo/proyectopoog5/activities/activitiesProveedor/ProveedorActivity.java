package espol.poo.proyectopoog5.activities.activitiesProveedor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.modelo.Proveedor;

public class ProveedorActivity extends AppCompatActivity { //Extiende de AppCompatActivity para sobreescribir métodos como onCreate
    private RecyclerView recyclerView;
    private ProveedorAdapter proveedorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.lista_proveedores_activity);
        cargarDatos();

        Log.d("TecniTools","en onCreate");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn_AgregarProveedor = findViewById(R.id.btnAgregarProveedor);

        btn_AgregarProveedor.setOnClickListener( v -> {
            Intent intent = new Intent(this, AgregarProveedor.class);
            Log.d("TecniTools","Al dar click en botón Agregar Cliente");
            this.startActivity(intent);
        });

        Button btn_Volver = findViewById(R.id.btnVolverP);
        btn_Volver.setOnClickListener(v -> finish());


    }

    private void llenarLista() {
        recyclerView = findViewById(R.id.recyclerViewProveedores);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Configurar el adaptador
        ArrayList<Proveedor> listaProveedores = new ArrayList<>();
        try{
            listaProveedores = Proveedor.cargarProveedores(this);
            Log.d("TecniTools","Datos leidos desde el archivo");
        }catch (Exception e){
            listaProveedores = Proveedor.obtenerProveedores();
            Log.d("TecniTools", "Error al cargar datos"+e.getMessage());
        }

        Log.d("TecniTools", listaProveedores.toString());//muestra la lista en el log

        proveedorAdapter = new ProveedorAdapter(listaProveedores,this);
        recyclerView.setAdapter(proveedorAdapter);
    }

    private void cargarDatos() {
        boolean guardado = false;
        try{
            guardado = Proveedor.crearDatosIniciales(this);

        }catch (Exception e){
            guardado = false;
            Log.d("TecniTools", "Error al crear los datos iniciales"+e.getMessage());
        }
        if (guardado) {
            Log.d("TecniTools", "DATOS INICIALES GUARDADOS");
        }
    }



    @Override
    public void onResume() {
        super.onResume();
        llenarLista();
        Log.d("TecniTools", "En onResume");//muestra la lista en el log

    }

}
