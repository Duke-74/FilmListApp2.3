package com.example.filmlistapp2.recyclerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmlistapp2.Film;
import com.example.filmlistapp2.R;

import java.util.ArrayList;
import java.util.List;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.FilmsViewHolder> {

    private List<Film> FilmList = new ArrayList<>();

    public void setItems(List<Film> filmList){
        FilmList.addAll(filmList);
        notifyDataSetChanged();
    }

    public void clearItems(){
        FilmList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilmsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new FilmsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmsViewHolder holder, int position) {
        holder.name.setText("Name");
        holder.description.setText("Test descryption");
    }

    @Override
    public int getItemCount() {
        return 5; //FilmList.size();
    }

    public class FilmsViewHolder extends RecyclerView.ViewHolder {
        private TextView name, description;
        private ImageView poster;
        public FilmsViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.FilmName);
            description = itemView.findViewById(R.id.FilmDescription);
            poster = itemView.findViewById(R.id.FilmField);
        }
    }
}
