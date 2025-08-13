package espol.poo.proyectopoog5.activities.activitiesReportes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import espol.poo.proyectopoog5.R;
import espol.poo.proyectopoog5.modelo.ReporteTecnico;

public class ReporteTecnicoAdapter extends RecyclerView.Adapter<ReporteTecnicoAdapter.ViewHolder> {

    private List<ReporteTecnico> lista;

    public ReporteTecnicoAdapter(List<ReporteTecnico> lista) {
        this.lista = lista;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvTotal;
        public ViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreTecnico);
            tvTotal = itemView.findViewById(R.id.tvTotalRecaudado);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reporte_tecnico, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ReporteTecnico r = lista.get(position);
        holder.tvNombre.setText(r.getNombreTecnico());
        holder.tvTotal.setText(String.format("$ %.2f", r.getTotalRecaudado()));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}

