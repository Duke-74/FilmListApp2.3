package com.example.filmlistapp2.recyclerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmlistapp2.Film;
import com.example.filmlistapp2.R;
import com.example.filmlistapp2.User;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
    private List<User> UserList = new ArrayList<>();

    public void setItems(List<User> userList){
        UserList.addAll(userList);
        notifyDataSetChanged();
    }

    public UsersAdapter(List<User> userList){
        this.UserList = userList;
    }

    public void clearItems(){
        UserList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        holder.login.setText(UserList.get(position).getLogin());
        holder.password.setText(UserList.get(position).getPassword());
    }

    @Override
    public int getItemCount() {
        return this.UserList.size();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder {
        private TextView login, password;
        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            login = itemView.findViewById(R.id.UserLogin);
            password = itemView.findViewById(R.id.UserPassword);
        }
    }
}
