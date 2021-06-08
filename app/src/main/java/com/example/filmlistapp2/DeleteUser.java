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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DeleteUser extends AppCompatActivity {

    Button deleteUser, back;
    EditText loginToDelete, confirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        deleteUser = (Button) findViewById(R.id.DeleteUser);
        back = (Button) findViewById(R.id.DeleteUserBack);
        loginToDelete = (EditText) findViewById(R.id.LoginToDelete);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<User> userList = new ArrayList<User>();


        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    db.collection("users")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            //Toast.makeText(getApplicationContext(), document.getId(), Toast.LENGTH_SHORT).show();
                                            if(document.getData().get("login").toString() != null
                                                    || document.getData().get("password").toString() != null) {
                                                User user = new User(document.getData().get("login").toString(),
                                                        document.getData().get("password").toString(),
                                                        document.getId());
                                                Log.d("MyLog", "" + user.getLogin());
                                                userList.add(user);
                                            }

                                        }

                                        for(User user : userList){
                                            if(user.getLogin().equals(loginToDelete.getText().toString())){
                                                db.collection("users").document(user.getId())
                                                        .delete()
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Log.d("MyLog", "DocumentSnapshot successfully deleted!");
                                                            }
                                                        })
                                                        .addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
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

                    Intent intent = new Intent(DeleteUser.this, AdminField.class);
                    startActivity(intent);

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteUser.this, AdminField.class);
                startActivity(intent);
            }
        });
    }
}