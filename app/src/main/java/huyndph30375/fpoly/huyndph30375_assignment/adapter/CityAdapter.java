package huyndph30375.fpoly.huyndph30375_assignment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {
    private List<String> filteredCities;

    public CityAdapter(List<String> filteredCities) {
        this.filteredCities = filteredCities;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(filteredCities.get(position));
        holder.itemView.setOnClickListener(view -> {
            String selectedCity = filteredCities.get(position);
            // do something with the selected city
        });
    }

    @Override
    public int getItemCount() {
        return filteredCities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
