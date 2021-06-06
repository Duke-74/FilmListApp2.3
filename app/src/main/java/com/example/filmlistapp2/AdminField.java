package com.example.filmlistapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminField extends AppCompatActivity {

    Button adminBack, toUsersList, deleteUser, addFilm, deleteFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_field);

        adminBack = (Button) findViewById(R.id.AdminBack);
        toUsersList = (Button) findViewById(R.id.UsersList);
        deleteUser = (Button) findViewById(R.id.DeleteUser);
        addFilm = (Button) findViewById(R.id.AddFilm);
        deleteFilm = (Button) findViewById(R.id.DeleteFilm);

        adminBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminField.this, MainActivity.class);
                startActivity(intent);
            }
        });

        toUsersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminField.this, RecyclerUserListActivity.class);
                startActivity(intent);
            }
        });

        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminField.this, DeleteUser.class);
                startActivity(intent);
            }
        });

        addFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminField.this, AddFilm.class);
                startActivity(intent);
            }
        });

        deleteFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminField.this, DeleteFilm.class);
                startActivity(intent);
            }
        });
    }
}