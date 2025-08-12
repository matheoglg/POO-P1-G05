package espol.poo.proyectopoog5.activities.activitiesOrdenServicio;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.modelo.OrdenServicio;

public class OrdenServicioAdapter extends RecyclerView.Adapter<OrdenServicioAdapter.OrdenViewHolder> {

    private ArrayList<OrdenServicio> listaOrdenes;
    private Context context;

    public OrdenServicioAdapter(ArrayList<OrdenServicio> listaOrdenes, Context context) {
        this.listaOrdenes = listaOrdenes;
        this.context = context;
    }

    @Override
    public OrdenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orden_servicio, parent, false);
        return new OrdenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrdenViewHolder holder, int position) {
        OrdenServicio orden = listaOrdenes.get(position);

        holder.tvCliente.setText(orden.getCliente().getNombrePersona());
        holder.tvFecha.setText(orden.getFecha().toString());
        holder.tvPlaca.setText(orden.getVehiculo().getNumPlaca());
        holder.tvTotal.setText("$" + String.format("%.2f", orden.getTotal()));

        holder.btnDetalles.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetalleOrdenServicioActivity.class);
            intent.putExtra("posicion", position);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaOrdenes.size();
    }

    public static class OrdenViewHolder extends RecyclerView.ViewHolder {
        TextView tvCliente, tvFecha, tvPlaca, tvTotal;
        Button btnDetalles;

        public OrdenViewHolder(View itemView) {
            super(itemView);
            tvCliente = itemView.findViewById(R.id.tvNombreClienteOrdenServicio);
            tvFecha = itemView.findViewById(R.id.tvFechaOrdenServicio);
            tvPlaca = itemView.findViewById(R.id.tvPlacaOrdenServicio);
            tvTotal = itemView.findViewById(R.id.tvTotalOrdenServicio);
            btnDetalles = itemView.findViewById(R.id.btnDetallesOrdenServicio);
        }
    }
}
