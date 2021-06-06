package com.example.filmlistapp2.recyclerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmlistapp2.R;
import com.example.filmlistapp2.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter2 extends RecyclerView.Adapter<UserAdapter2.UserViewHolder> {

    List<User> UserList = new ArrayList<>();

    public UserAdapter2(List<User> listItems){
        this.UserList = listItems;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.login.setText(UserList.get(position).getLogin());
        holder.password.setText(UserList.get(position).getPassword());
    }

    @Override
    public int getItemCount() {
        return this.UserList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private TextView login, password;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            login = itemView.findViewById(R.id.UserLogin);
            password = itemView.findViewById(R.id.UserPassword);
        }
    }
}
