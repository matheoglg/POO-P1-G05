package espol.poo.proyectopoog5.activities.activitiesCliente;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.modelo.Cliente;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>{

    private ArrayList<Cliente> clientes;
    private Context context;
    public ClienteAdapter(ArrayList<Cliente> clientes, Context context) {
        this.clientes = clientes;
        this.context = context;
    }

    @Override
    public ClienteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cliente, parent, false);
        return new ClienteViewHolder(view);
    }

    public void onBindViewHolder(ClienteViewHolder holder, int position) {
        Cliente cliente = clientes.get(position);
        holder.idClienteTextView.setText(cliente.getIdPersona());
        holder.nombreClienteTextView.setText(cliente.getNombrePersona());
        holder.dirClienteTextView.setText(cliente.getDirCliente());
        holder.telfClienteTextView.setText(cliente.getTelfPersona());
        holder.tipoClienteTextView.setText(cliente.getTipoCliente().toString());

    }
    @Override
    public int getItemCount() {
        return clientes.size();
    }

    public static class ClienteViewHolder extends RecyclerView.ViewHolder {
        TextView idClienteTextView;
        TextView nombreClienteTextView;
        TextView dirClienteTextView;
        TextView telfClienteTextView;
        TextView tipoClienteTextView;

        public ClienteViewHolder(View itemView) {
            super(itemView);
            idClienteTextView = itemView.findViewById(R.id.tvIdCliente);
            nombreClienteTextView = itemView.findViewById(R.id.tvNombreCliente);
            dirClienteTextView = itemView.findViewById(R.id.tvDirCliente);
            telfClienteTextView = itemView.findViewById(R.id.tvTelfCliente);
            tipoClienteTextView = itemView.findViewById(R.id.tvTipoCliente);

        }
    }
}
