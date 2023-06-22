package huyndph30375.fpoly.huyndph30375_assignment.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import huyndph30375.fpoly.huyndph30375_assignment.databinding.ItemNewsBinding;
import huyndph30375.fpoly.huyndph30375_assignment.models.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<News> newsList;

    public NewsAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNewsBinding binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = newsList.get(position);
        if (news == null) {
            return;
        }
        holder.binding.newsPic.setImageBitmap(news.getResourceId());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemNewsBinding binding;

        public ViewHolder(@NonNull ItemNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
