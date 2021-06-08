package com.example.filmlistapp2;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SignIn extends AppCompatActivity {


    Button singInButton, authorizationBack;
    EditText login, password;
    boolean isExist = false, isAdmin = false;
    //List<Film> newFilmList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //Intent intent = getIntent();
        //newFilmList = intent.getParcelableArrayExtra("filmList");

        singInButton = (Button) findViewById(R.id.SignInButton);
        login = (EditText) findViewById(R.id.Login);
        password = (EditText) findViewById(R.id.Pass);
        authorizationBack = (Button) findViewById(R.id.AuthorizationBack);
        List<User> userList = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("MyLog", document.getId() + " => " + document.getData().get("login"));
                                User user = new User(document.getData().get("login").toString(), document.getData().get("password").toString(), document.getId());
                                userList.add(user);
                            }
                        } else {
                            Log.w("MyLog", "Error getting documents.", task.getException());
                        }
                    }
                });

        singInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (User user:userList) {
                    if (login.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                        Intent intent = new Intent(SignIn.this, AdminField.class);
                        startActivity(intent);
                        isAdmin = true;
                        break;
                    }
                    if(login.getText().toString().equals(user.getLogin()) && password.getText().toString().equals(user.getPassword())) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    Intent intent = new Intent(SignIn.this, UserField.class);
                    startActivity(intent);
                }
                if(!isExist && !isAdmin){
                    Toast.makeText(getApplicationContext(), "Логин или пароль не верны", Toast.LENGTH_LONG).show();
                }
            }
        });

        authorizationBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}