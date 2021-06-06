package com.example.filmlistapp2.recyclerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.filmlistapp2.Film;
import com.example.filmlistapp2.R;
import com.example.filmlistapp2.RecyclerFilmListActivity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.FilmsViewHolder> {

    private List<Film> FilmList;
    private Context context;

    public FilmsAdapter(List<Film> filmList, Context context){
        this.FilmList = filmList;
        this.context = context;
    }

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
        holder.name.setText(FilmList.get(position).getName());
        holder.description.setText(FilmList.get(position).getDescription());

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference().child(FilmList.get(position).getPoster());

        Glide.with(context).load(storageRef).into(holder.poster);

    }

    @Override
    public int getItemCount() {
        return this.FilmList.size();
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
