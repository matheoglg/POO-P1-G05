package espol.poo.proyectopoog5.activities.activitiesFactura;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.modelo.Factura;


public class FacturaAdapter extends RecyclerView.Adapter<FacturaAdapter.FacturaViewHolder> {

    private ArrayList<Factura> listaFacturas;
    private Context context;

    public FacturaAdapter(ArrayList<Factura> listaFacturas, Context context) {
        this.listaFacturas = listaFacturas;
        this.context = context;
    }

    @Override
    public FacturaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_factura, parent, false);
        return new FacturaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FacturaViewHolder holder, int position) {
        Factura factura = listaFacturas.get(position);

        holder.tvCliente.setText(factura.getCliente().getNombrePersona());
        holder.tvFechaCreacion.setText(factura.getFechaCreacion());
        holder.tvPeriodo.setText(factura.getPeriodo());
        holder.tvTotal.setText(String.format("%.2f", factura.getTotal()));

        holder.btnDetalles.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetalleFacturaActivity.class);
            intent.putExtra("idFactura", factura.getIdFactura()); // ✅ mandamos ID único
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaFacturas.size();
    }

    public static class FacturaViewHolder extends RecyclerView.ViewHolder {
        TextView tvCliente, tvFechaCreacion, tvPeriodo, tvTotal;
        Button btnDetalles;

        public FacturaViewHolder(View itemView) {
            super(itemView);
            tvCliente = itemView.findViewById(R.id.tvNombreEmpresarial);
            tvFechaCreacion = itemView.findViewById(R.id.tvFechaEmpresarial);
            tvPeriodo = itemView.findViewById(R.id.tvPeriodoEmpresarial);
            tvTotal = itemView.findViewById(R.id.tvTotalEmpresarial);
            btnDetalles = itemView.findViewById(R.id.btnDetalleEmpresarial);
        }
    }
}


