package com.example.filmlistapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {

    Button singInButton;
    EditText login, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        singInButton = (Button) findViewById(R.id.SignInButton);
        login = (EditText) findViewById(R.id.Login);
        password = (EditText) findViewById(R.id.Pass);

        singInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(login.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    Intent intent = new Intent(SignIn.this, UserField.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Логин или пароль не верны", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}