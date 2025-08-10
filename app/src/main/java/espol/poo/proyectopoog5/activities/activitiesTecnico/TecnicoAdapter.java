package espol.poo.proyectopoog5.activities.activitiesTecnico;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.modelo.Tecnico;

public class TecnicoAdapter extends RecyclerView.Adapter<TecnicoAdapter.TecnicoViewHolder>{

    private ArrayList<Tecnico> tecnicos;
    private Context context;
    public TecnicoAdapter(ArrayList<Tecnico> tecnicos, Context context) {
        this.tecnicos = tecnicos;
        this.context = context;
    }

    @Override
    public TecnicoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tecnico, parent, false);
        return new TecnicoViewHolder(view);
    }

    public void onBindViewHolder(TecnicoViewHolder holder, int position) {
        Tecnico tecnico = tecnicos.get(position);
        holder.idTecnicoTextView.setText(tecnico.getIdPersona());
        holder.nombreTecnicoTextView.setText(tecnico.getNombrePersona());
        holder.telfTecnicoTextView.setText(tecnico.getTelfPersona());
        holder.espTecnicoTextView.setText(tecnico.getEspTecnico());
        holder.btnEliminarTecnico.setOnClickListener(v -> {
            mostrarConfirmacion(tecnico, position);
        });
    }

    private void mostrarConfirmacion(Tecnico tecnico, int position) {
        new AlertDialog.Builder(context).setTitle("Eliminar registro").setMessage("¿Está seguro que desea eliminar el registro?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    eliminarTecnico(tecnico, position);
                })
                .setNegativeButton("No", (dialog, which) -> {
                    dialog.dismiss();
                })
                .show();
    }
    private void eliminarTecnico(Tecnico tecnico, int position) {
        tecnicos.remove(position);
        try {
            Tecnico.guardarLista(context, tecnicos);
            Toast.makeText(context, "Técnico eliminado exitosamente", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Error al eliminar el técnico", Toast.LENGTH_SHORT).show();
            Log.e("AppTecnicos", "Error al guardar lista", e);
        }

        notifyItemRemoved(position); // Notifica al adaptador del cambio
        notifyItemRangeChanged(position, tecnicos.size()); // Actualiza los índices
    }

    @Override
    public int getItemCount() {
        return tecnicos.size();
    }

    public static class TecnicoViewHolder extends RecyclerView.ViewHolder {
        TextView idTecnicoTextView;
        TextView nombreTecnicoTextView;
        TextView telfTecnicoTextView;
        TextView espTecnicoTextView;
        Button btnEliminarTecnico;

        public TecnicoViewHolder(View itemView) {
            super(itemView);
            idTecnicoTextView = itemView.findViewById(R.id.tvIdTecnico);
            nombreTecnicoTextView = itemView.findViewById(R.id.tvNombreTecnico);
            telfTecnicoTextView = itemView.findViewById(R.id.tvTelfTecnico);
            espTecnicoTextView = itemView.findViewById(R.id.tvEspecialidad);
            btnEliminarTecnico = itemView.findViewById(R.id.btnEliminarTecnico);

        }
    }
}
