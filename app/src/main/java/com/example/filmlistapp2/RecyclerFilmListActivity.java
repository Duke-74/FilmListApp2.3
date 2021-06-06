package com.example.filmlistapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.filmlistapp2.recyclerAdapter.FilmsAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class RecyclerFilmListActivity extends AppCompatActivity {

    ImageView imagePoster;
    static final String TAG = "MyLog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_film_list);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<Film> filmList = new ArrayList<>();


        db.collection("films")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Toast.makeText(getApplicationContext(), document.getId(), Toast.LENGTH_SHORT).show();
                                if(document.getData().get("poster").toString() != null
                                        || document.getData().get("description").toString() != null
                                        || document.getData().get("poster").toString() != null) {
                                    Film film = new Film(document.getData().get("name").toString(),
                                            document.getData().get("description").toString(),
                                            document.getData().get("poster").toString());
                                    Log.d("MyLog", "" + film.getName());
                                    filmList.add(film);
                                }

                            }

                            FilmsAdapter filmsAdapter = new FilmsAdapter(filmList, getApplicationContext());
                            Log.d("MyLog", "Длина: " + filmList.size());
                            RecyclerView recyclerView = findViewById(R.id.RecyclerList);
                            recyclerView.setAdapter(filmsAdapter);

                        } else {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        imagePoster = (ImageView) findViewById(R.id.FilmField);



        // Create a Cloud Storage reference from the app
        //FirebaseStorage storage = FirebaseStorage.getInstance();
        //StorageReference storageRef = storage.getReference().child("756038770746465.jpg");

        //Glide.with(RecyclerFilmListActivity.this).load(storageRef).into(imagePoster);

    }
}