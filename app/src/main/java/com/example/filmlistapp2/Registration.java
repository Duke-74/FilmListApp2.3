package com.example.filmlistapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    Button registration, registrationBack;
    EditText pass, passRep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registration = (Button) findViewById(R.id.SignUp);
        registrationBack = (Button) findViewById(R.id.RegistrationBack);
        pass = (EditText) findViewById(R.id.RegistrationPass);
        passRep = (EditText) findViewById(R.id.RegistrationPassRep);

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pass.getText().toString().equals(passRep.getText().toString())) {
                    Intent intent = new Intent(Registration.this, UserField.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registrationBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, MainActivity.class);
            }
        });


    }
}