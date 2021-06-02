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

public class RecyclerFilmListActivity extends AppCompatActivity {

    ImageView imagePoster;
    static final String TAG = "MyLog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_film_list);
        FirebaseFirestore db = FirebaseFirestore.getInstance();



        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Toast.makeText(getApplicationContext(), "0", Toast.LENGTH_SHORT).show();
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Toast.makeText(getApplicationContext(), document.getId(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        imagePoster = (ImageView) findViewById(R.id.FilmField);

        FilmsAdapter filmsAdapter = new FilmsAdapter();
        RecyclerView recyclerView = findViewById(R.id.RecyclerList);
        recyclerView.setAdapter(filmsAdapter);

        // Create a Cloud Storage reference from the app
        //FirebaseStorage storage = FirebaseStorage.getInstance();
        //StorageReference storageRef = storage.getReference().child("756038770746465.jpg");

        //Glide.with(RecyclerFilmListActivity.this).load(storageRef).into(imagePoster);

    }
}