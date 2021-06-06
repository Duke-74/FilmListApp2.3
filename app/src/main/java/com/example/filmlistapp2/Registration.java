package com.example.filmlistapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {

    Button registration, registrationBack;
    EditText regLogin, pass, passRep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registration = (Button) findViewById(R.id.SignUp);
        registrationBack = (Button) findViewById(R.id.RegistrationBack);
        pass = (EditText) findViewById(R.id.RegistrationPass);
        passRep = (EditText) findViewById(R.id.RegistrationPassRep);
        regLogin = (EditText) findViewById(R.id.RegistrationLogin);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pass.getText().toString().equals(passRep.getText().toString())) {

                    // Create a new user with a first and last name
                    Map<String, Object> user = new HashMap<>();
                    user.put("login", regLogin.getText().toString());
                    user.put("password", pass.getText().toString());

                    // Add a new document with a generated ID
                    db.collection("users")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("MyTag", "DocumentSnapshot added with ID: " + documentReference.getId());
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("MyTag", "Error adding document", e);
                                }
                            });

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
                startActivity(intent);
            }
        });


    }
}