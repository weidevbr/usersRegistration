package com.example.usersregistration;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListUser extends AppCompatActivity {
    SearchView searchView;
    MyAdapter customAdapter;
    private RecyclerView recyclerView;
    private UserAccess access;
    private List<User> users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        getSupportActionBar().setTitle("Lista de Usuários");
        access = new UserAccess(this);
        users = access.getAllUsers();

        if (users.isEmpty()){
            Toast.makeText(this, "Nenhum Usuário Cadastrado!", Toast.LENGTH_SHORT).show();
        }

        recyclerView = findViewById(R.id.usersList);
        searchView =  findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), users));

    }

}