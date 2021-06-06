package com.example.filmlistapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.sql.BatchUpdateException;

public class DeleteFilm extends AppCompatActivity {

    Button deleteFilm, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_film);

        deleteFilm = (Button) findViewById(R.id.DeleteFilm);
        back = (Button) findViewById(R.id.DeleteFilmBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteFilm.this, UserField.class);
            }
        });


    }
}