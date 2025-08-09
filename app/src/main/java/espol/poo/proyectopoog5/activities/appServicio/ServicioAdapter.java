package espol.poo.proyectopoog5.activities.appServicio;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.modelo.Servicio;

public class ServicioAdapter extends RecyclerView.Adapter<ServicioAdapter.ServicioViewHolder> {
    public interface OnEditClickListener {
        void onEditClick(Servicio servicio);
    }

    private List<Servicio> lista;
    private OnEditClickListener listener;

    public ServicioAdapter(List<Servicio> lista, OnEditClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ServicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_servicio, parent, false);
        return new ServicioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicioViewHolder holder, int position) {
        Servicio s = lista.get(position);
        holder.tvCodigo.setText(s.getCodigoSSS());
        holder.tvNombre.setText(s.getNombreServ());
        holder.tvPrecio.setText(String.format("$%.2f", s.getPrecioActual()));

        holder.btnEditar.setOnClickListener(v -> listener.onEditClick(s));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class ServicioViewHolder extends RecyclerView.ViewHolder {
        TextView tvCodigo, tvNombre, tvPrecio;
        Button btnEditar;

        ServicioViewHolder(View itemView) {
            super(itemView);
            tvCodigo = itemView.findViewById(R.id.tvCodigoServicio);
            tvNombre = itemView.findViewById(R.id.tvNombreServicio);
            tvPrecio = itemView.findViewById(R.id.tvPrecioServicio);
            btnEditar = itemView.findViewById(R.id.btnEditarServicio);
        }
    }
}
