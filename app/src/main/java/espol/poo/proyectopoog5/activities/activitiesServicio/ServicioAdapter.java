package espol.poo.proyectopoog5.activities.activitiesServicio;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.modelo.Servicio;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;


public class ServicioAdapter extends RecyclerView.Adapter<ServicioAdapter.ServicioViewHolder> {
    private ArrayList<Servicio> servicios;
    private Context context;

    public ServicioAdapter(ArrayList<Servicio> servicios, Context context) {
        this.servicios = servicios;
        this.context = context;
    }

    @Override
    public ServicioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_servicio, parent, false);
        return new ServicioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ServicioViewHolder holder, int position) {
        Servicio servicio = servicios.get(position);
        holder.codigoServicioTextView.setText(servicio.getCodigoServ());
        holder.nombreServicioTextView.setText(servicio.getNombreServ());
        holder.precioServicioTextView.setText("$" + servicio.getPrecioActual());


        holder.btnEditar.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditarServicio.class);
            intent.putExtra("posicion", position);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return servicios.size();
    }

    public static class ServicioViewHolder extends RecyclerView.ViewHolder {
        TextView codigoServicioTextView;
        TextView nombreServicioTextView;
        TextView precioServicioTextView;
        Button btnEditar;
        Button btnAgregar;

        public ServicioViewHolder(View itemView) {
            super(itemView);
            codigoServicioTextView = itemView.findViewById(R.id.tvCodigoServicio);
            nombreServicioTextView = itemView.findViewById(R.id.tvNombreServicio);
            precioServicioTextView = itemView.findViewById(R.id.tvPrecioServicio);
            btnAgregar = itemView.findViewById(R.id.btnAgregarServicio);
            btnEditar = itemView.findViewById(R.id.btnEditarServicio);
        }
    }


}
