package espol.poo.proyectopoog5.activities.activitiesCliente;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.modelo.Cliente;

public class ClientesActivity extends AppCompatActivity { //Extiende de AppCompatActivity para sobreescribir métodos como onCreate
    private RecyclerView recyclerView;
    private ClienteAdapter clienteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.lista_clientes_activity);
        cargarDatos();

        Log.d("TecniTools","en onCreate");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn_AgregarCliente = findViewById(R.id.btnAgregarCliente);

        btn_AgregarCliente.setOnClickListener( v -> {
            Intent intent = new Intent(this, AgregarCliente.class);
            Log.d("TecniTools","Al dar click en botón Agregar Cliente");
            this.startActivity(intent);
        });

        Button btn_Volver = findViewById(R.id.btnVolverC);
        btn_Volver.setOnClickListener(v -> finish());


    }

    private void llenarLista() {
        recyclerView = findViewById(R.id.recyclerViewClientes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Configurar el adaptador
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        try{
            listaClientes = Cliente.cargarClientes(this);
            Log.d("TecniTools","Datos leidos desde el archivo");
        }catch (Exception e){
            listaClientes = Cliente.obtenerClientes();
            Log.d("TecniTools", "Error al cargar datos"+e.getMessage());
        }

        Log.d("TecniTools", listaClientes.toString());//muestra la lista en el log

        clienteAdapter = new ClienteAdapter(listaClientes,this);
        recyclerView.setAdapter(clienteAdapter);
    }

    private void cargarDatos() {
        boolean guardado = false;
        try{
            guardado = Cliente.crearDatosIniciales(this);

        }catch (Exception e){
            guardado = false;
            Log.d("TecniTools", "Error al crear los datos iniciales"+e.getMessage());
        }
        if (guardado) {
            Log.d("TecniTools", "DATOS INICIALES GUARDADOS");
            //LEER LOS DATOS
        }
    }



    @Override
    public void onResume() {
        super.onResume();
        llenarLista();
        Log.d("TecniTools", "En onResume");//muestra la lista en el log

    }

}
