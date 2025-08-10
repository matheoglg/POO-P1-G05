package espol.poo.proyectopoog5.activities.activitiesProveedor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.modelo.Proveedor;

public class ProveedorAdapter extends RecyclerView.Adapter<ProveedorAdapter.ProveedorViewHolder>{

    private ArrayList<Proveedor> proveedores;
    private Context context;
    public ProveedorAdapter(ArrayList<Proveedor> proveedores, Context context) {
        this.proveedores = proveedores;
        this.context = context;
    }

    @Override
    public ProveedorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_proveedor, parent, false);
        return new ProveedorViewHolder(view);
    }

    public void onBindViewHolder(ProveedorViewHolder holder, int position) {
        Proveedor proveedor = proveedores.get(position);
        holder.idProveedorTextView.setText(proveedor.getIdPersona());
        holder.nombreProveedorTextView.setText(proveedor.getNombrePersona());
        holder.telfProveedorTextView.setText(proveedor.getTelfPersona());
        holder.descProveedorTextView.setText(proveedor.getDescProveedor());
    }
    @Override
    public int getItemCount() {
        return proveedores.size();
    }

    public static class ProveedorViewHolder extends RecyclerView.ViewHolder {
        TextView idProveedorTextView;
        TextView nombreProveedorTextView;
        TextView telfProveedorTextView;
        TextView descProveedorTextView;

        public ProveedorViewHolder(View itemView) {
            super(itemView);
            idProveedorTextView = itemView.findViewById(R.id.tvIdProveedor);
            nombreProveedorTextView = itemView.findViewById(R.id.tvNombreProveedor);
            telfProveedorTextView = itemView.findViewById(R.id.tvTelfProveedor);
            descProveedorTextView = itemView.findViewById(R.id.tvDescripcion);

        }
    }
}
