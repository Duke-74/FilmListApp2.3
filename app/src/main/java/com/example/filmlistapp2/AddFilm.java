package com.example.filmlistapp2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AddFilm extends AppCompatActivity {

    Button addFilm, addFilmBack, addImage;
    EditText filmName, filmDescription;
    ImageView filmPoster;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
//    String path = "";
//    File file;



    ActivityResultLauncher<String> mGetContent = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
//                file = new File(getRealPathFromURI(uri.));
                filmPoster.setImageURI(uri);
//                path = file.getPath();
                // Create a Cloud Storage reference from the app
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();
                // Create a reference to "mountains.jpg"
                StorageReference mountainsRef = storageRef.child(uri.getPath());
                mountainsRef.putFile(uri);
            });




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_film);

        addFilm = (Button) findViewById(R.id.AddFilm);
        addFilmBack = (Button) findViewById(R.id.AddFilmBack);
        addImage = (Button) findViewById(R.id.AddFilmPoster);
        filmPoster = (ImageView) findViewById(R.id.addFilmImage);

        filmName = (EditText) findViewById(R.id.AddFilmName);
        filmDescription = (EditText) findViewById(R.id.AddFilmDescription);

        addFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    // Create a new user with a first and last name
                    Map<String, Object> film = new HashMap<>();
                    film.put("name", filmName.getText().toString());
                    film.put("description", filmDescription.getText().toString());
                    film.put("poster", "Test.jpg"/*path*/);

                    // Add a new document with a generated ID
                    db.collection("films")
                            .add(film)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("MyLog", "film added with ID: " + documentReference.getId());
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("MyLog", "Error adding document", e);
                                }
                            });


//                    Intent intent = new Intent(AddFilm.this, AdminField.class);
//                    startActivity(intent);

            }
        });

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                mGetContent.launch("image/**");
            }
        });

        addFilmBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFilm.this, AdminField.class);
                startActivity(intent);
            }
        });

    }

//    private String getRealPathFromURI(Uri contentURI) {
//        String result;
//        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
//        if (cursor == null) { // Source is Dropbox or other similar local file path
//            result = contentURI.getPath();
//        } else {
//            cursor.moveToFirst();
//            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
//            result = cursor.getString(idx);
//            cursor.close();
//        }
//        return result;
//    }
}