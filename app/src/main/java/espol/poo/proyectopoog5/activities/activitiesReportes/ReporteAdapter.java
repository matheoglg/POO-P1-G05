package espol.poo.proyectopoog5.activities.activitiesReportes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;

public class ReporteAdapter extends RecyclerView.Adapter<ReporteAdapter.ViewHolder> {

    private List<Map.Entry<String, Double>> datos;

    public ReporteAdapter(Map<String, Double> mapa) {
        this.datos = new ArrayList<>(mapa.entrySet());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Map.Entry<String, Double> item = datos.get(position);
        holder.txtLinea1.setText(item.getKey());
        holder.txtLinea2.setText(String.format(Locale.getDefault(), "%.2f USD", item.getValue()));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtLinea1, txtLinea2;
        public ViewHolder(View itemView) {
            super(itemView);
            txtLinea1 = itemView.findViewById(android.R.id.text1);
            txtLinea2 = itemView.findViewById(android.R.id.text2);
        }
    }
}
