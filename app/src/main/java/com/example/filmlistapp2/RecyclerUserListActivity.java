package com.example.filmlistapp2;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmlistapp2.recyclerAdapter.FilmsAdapter;
import com.example.filmlistapp2.recyclerAdapter.UserAdapter2;
import com.example.filmlistapp2.recyclerAdapter.UsersAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RecyclerUserListActivity extends AppCompatActivity {

    ImageView imagePoster;
    static final String TAG = "MyLog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_film_list);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<User> userList = new ArrayList<>();


        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Toast.makeText(getApplicationContext(), document.getId(), Toast.LENGTH_SHORT).show();
                                User user = new User(document.getData().get("login").toString(),
                                        document.getData().get("password").toString());
                                Log.d("MyLog", "Длина списка юзеров " + user.getLogin());
                                userList.add(user);
                            }
                            UserAdapter2 usersAdapter = new UserAdapter2(userList); // Error
                            if (usersAdapter == null) {
                                Log.d("MyLog", "Адаптер пуст");
                            }
                            Log.d("MyLog", "Длина списка юзеров: " + userList.size());
                            RecyclerView recyclerView = findViewById(R.id.UserRecyclerList);
                            recyclerView.setAdapter(usersAdapter);

                        } else {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}