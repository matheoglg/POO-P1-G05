package espol.poo.proyectopoog5.activities.activitiesCliente;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        // Configuramos el botón de Editar
        //setOnClickListener() espera un objeto que implemente la interfaz View.OnClickListener

    }
    @Override
    public int getItemCount() {
        return clientes.size();
    }

    public static class ClienteViewHolder extends RecyclerView.ViewHolder {
        TextView idClienteTextView;

        public ClienteViewHolder(View itemView) {
            super(itemView);
            idClienteTextView = itemView.findViewById(R.id.tvIdCliente);

        }
    }
}
