package com.example.filmlistapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DeleteUser extends AppCompatActivity {

    Button deleteUser, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        deleteUser = (Button) findViewById(R.id.DeleteUser);
        back = (Button) findViewById(R.id.DeleteUserBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteUser.this, AdminField.class);
            }
        });
    }
}