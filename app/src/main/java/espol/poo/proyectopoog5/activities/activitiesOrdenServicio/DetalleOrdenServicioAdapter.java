package espol.poo.proyectopoog5.activities.activitiesOrdenServicio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.modelo.DetalleServicioOS;

public class DetalleOrdenServicioAdapter extends RecyclerView.Adapter<DetalleOrdenServicioAdapter.DetalleViewHolder> {

    private ArrayList<DetalleServicioOS> listaDetalles;

    public DetalleOrdenServicioAdapter(ArrayList<DetalleServicioOS> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    @Override
    public DetalleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_servicio_os, parent, false);
        return new DetalleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetalleViewHolder holder, int position) {
        DetalleServicioOS detalle = listaDetalles.get(position);

        holder.tvNombreServicio.setText(detalle.getServicio().getNombreServ());
        holder.tvCantidad.setText(String.valueOf(detalle.getCantidad()));
        holder.tvSubtotal.setText("$" + String.format("%.2f", detalle.getSubtotal()));
        holder.tvTecnico.setText(detalle.getTecnico().getNombrePersona());

        // Si item_servicio_os tiene botón eliminar, aquí lo ocultamos:
        if (holder.btnEliminar != null) {
            holder.btnEliminar.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return listaDetalles.size();
    }

    public static class DetalleViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreServicio, tvCantidad, tvSubtotal, tvTecnico;
        Button btnEliminar;

        public DetalleViewHolder(View itemView) {
            super(itemView);
            tvNombreServicio = itemView.findViewById(R.id.tvNombreServicioAgregadoOS);
            tvCantidad = itemView.findViewById(R.id.tvCantidadServicioAgregadoOS);
            tvSubtotal = itemView.findViewById(R.id.tvSubtotalServicioAgregadoOS);
            tvTecnico = itemView.findViewById(R.id.tvTecnicoServicioAgregadoOS);
            btnEliminar = itemView.findViewById(R.id.btnDescartarOS);
        }
    }
}