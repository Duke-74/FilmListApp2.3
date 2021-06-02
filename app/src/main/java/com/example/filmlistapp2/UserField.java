package com.example.filmlistapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserField extends AppCompatActivity {

    Button exitButton, filmsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_field);

        exitButton = (Button) findViewById(R.id.Exit);
        filmsButton = (Button) findViewById(R.id.AllFilms);

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserField.this, SignIn.class);
                startActivity(intent);
            }
        });

        filmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserField.this, RecyclerFilmListActivity.class);
                startActivity(intent);
            }
        });
    }
}