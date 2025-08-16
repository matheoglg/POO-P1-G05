package espol.poo.proyectopoog5.activities.activitiesFactura;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Locale;
import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.modelo.DetalleServicioOS;
import espol.poo.proyectopoog5.modelo.OrdenServicio;

public class DetalleFacturaAdapter extends RecyclerView.Adapter<DetalleFacturaAdapter.VH> {

    private final ArrayList<DetalleServicioOS> data;

    public DetalleFacturaAdapter(ArrayList<DetalleServicioOS> data) {
        this.data = data;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_servicio_factura, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(VH h, int pos) {
        DetalleServicioOS d = data.get(pos);

        h.tvNombre.setText(d.getServicio().getNombreServ());
        h.tvPrecio.setText(String.format(Locale.getDefault(), "$%.2f", d.getServicio().getPrecioActual()));
        h.tvCantidad.setText(String.valueOf(d.getCantidad()));
        h.tvSubtotal.setText(String.format(Locale.getDefault(), "$%.2f", d.getSubtotal()));
        }

    @Override
    public int getItemCount() { return data.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvNombre, tvPrecio, tvCantidad, tvSubtotal, tvPlacaFactura;
        VH(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreServicioFactura);
            tvPrecio = itemView.findViewById(R.id.tvPrecioUnitarioServicioFactura);
            tvCantidad = itemView.findViewById(R.id.tvCantidadServicioFactura);
            tvSubtotal = itemView.findViewById(R.id.tvSubtotalServicioFactura);

        }
    }
}