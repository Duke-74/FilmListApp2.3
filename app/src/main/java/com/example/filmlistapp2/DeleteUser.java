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
import com.google.firebase.firestore.FirebaseFirestore;

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
        confirmPass = (EditText) findViewById(R.id.ConfirmPass);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirmPass.getText().toString().equals("admin")) {
                    db.collection("users").document(loginToDelete.getText().toString())
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

                    Intent intent = new Intent(DeleteUser.this, AdminField.class);
                    startActivity(intent);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteUser.this, AdminField.class);
            }
        });
    }
}