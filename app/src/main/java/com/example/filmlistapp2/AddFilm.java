package com.example.filmlistapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class AddFilm extends AppCompatActivity {

    Button addFilm, addFilmBack, addImage;
    EditText filmName, filmDescription;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_film);

        addFilm = (Button) findViewById(R.id.AddFilm);
        addFilmBack = (Button) findViewById(R.id.AddFilmBack);
        addImage = (Button) findViewById(R.id.AddFilmPoster);

        filmName = (EditText) findViewById(R.id.AddFilmName);
        filmDescription = (EditText) findViewById(R.id.AddFilmDescription);

        addFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create a new user with a first and last name
                Map<String, Object> film = new HashMap<>();
                film.put("name", filmName.getText().toString());
                film.put("description", filmDescription.getText().toString());

                // Add a new document with a generated ID
                db.collection("films")
                        .add(film)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("MyLog", "film added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("MyLog", "Error adding document", e);
                            }
                        });


                Intent intent = new Intent(AddFilm.this, AdminField.class);
                startActivity(intent);
            }
        });

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        addFilmBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFilm.this, AdminField.class);
                startActivity(intent);
            }
        });

    }
}