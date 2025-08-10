package espol.poo.proyectopoog5.activities.activitiesTecnico;

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
import espol.poo.proyectopoog5.modelo.Tecnico;

public class TecnicoActivity extends AppCompatActivity { //Extiende de AppCompatActivity para sobreescribir métodos como onCreate
    private RecyclerView recyclerView;
    private TecnicoAdapter tecnicoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.lista_tecnicos_activity);
        cargarDatos();

        Log.d("TecniTools","en onCreate");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn_AgregarTecnico = findViewById(R.id.btnAgregarTecnico);

        btn_AgregarTecnico.setOnClickListener( v -> {
            Intent intent = new Intent(this, AgregarTecnico.class);
            Log.d("TecniTools","Al dar click en botón Agregar Cliente");
            this.startActivity(intent);
        });

        Button btn_Volver = findViewById(R.id.btnVolverT);
        btn_Volver.setOnClickListener(v -> finish());
    }
    private void llenarLista() {
        recyclerView = findViewById(R.id.recyclerViewTecnicos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Configurar el adaptador
        ArrayList<Tecnico> listaTecnicos = new ArrayList<>();
        try{
            listaTecnicos = Tecnico.cargarTecnicos(this);
            Log.d("TecniTools","Datos leidos desde el archivo");
        }catch (Exception e){
            listaTecnicos = Tecnico.obtenerTecnicos();
            Log.d("TecniTools", "Error al cargar datos"+e.getMessage());
        }

        Log.d("TecniTools", listaTecnicos.toString());//muestra la lista en el log

        tecnicoAdapter = new TecnicoAdapter(listaTecnicos,this);
        recyclerView.setAdapter(tecnicoAdapter);
    }

    private void cargarDatos() {
        boolean guardado = false;
        try{
            guardado = Tecnico.crearDatosIniciales(this);

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
