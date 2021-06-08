package com.example.filmlistapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.filmlistapp2.recyclerAdapter.FilmsAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.List;

public class DeleteFilm extends AppCompatActivity {

    Button deleteFilm, back;
    EditText filmName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_film);

        deleteFilm = (Button) findViewById(R.id.Delete);
        back = (Button) findViewById(R.id.DeleteFilmBack);
        filmName = (EditText) findViewById(R.id.NameToDelete);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Log.d("MyLog", "Film name: " + filmName.getText().toString());
        List<Film> filmList = new ArrayList<Film>();


        deleteFilm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                db.collection("films")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        //Toast.makeText(getApplicationContext(), document.getId(), Toast.LENGTH_SHORT).show();
                                        if(document.getData().get("poster").toString() != null
                                                && document.getData().get("description").toString() != null
                                                && document.getData().get("poster").toString() != null) {
                                            Film film = new Film(document.getData().get("name").toString(),
                                                    document.getData().get("description").toString(),
                                                    document.getData().get("poster").toString(),
                                                    document.getId());
                                            Log.d("MyLog", "" + film.getName());
                                            filmList.add(film);
                                        }

                                    }

                                    for(Film film : filmList){
                                        if(film.getName().equals(filmName.getText().toString())){
                                            db.collection("films").document(film.getId())
                                                    .delete()
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Log.d("MyLog", "DocumentSnapshot successfully deleted!");
                                                            Toast.makeText(getApplicationContext(), "Фильм удалён из библиотеки", Toast.LENGTH_LONG).show();
                                                            Intent intent = new Intent(DeleteFilm.this, AdminField.class);
                                                            startActivity(intent);
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(getApplicationContext(), "Фильма с указанным названием не существует", Toast.LENGTH_LONG).show();
                                                            Log.w("MyLog", "Error deleting document", e);
                                                        }
                                                    });
                                        }
                                    }

                                } else {
                                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteFilm.this, AdminField.class);
                startActivity(intent);
            }
        });


    }
}