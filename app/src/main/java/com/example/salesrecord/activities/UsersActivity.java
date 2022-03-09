package com.example.salesrecord.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.salesrecord.R;
import com.example.salesrecord.adapter.SalesAdapter;
import com.example.salesrecord.adapter.UserAdapter;
import com.example.salesrecord.controller.SessionController;
import com.example.salesrecord.controller.UserDbController;
import com.example.salesrecord.model.Session;

import java.util.ArrayList;

public class UsersActivity extends AppCompatActivity {
    private ArrayList<String> ids, emails;
    private RecyclerView recycler;
    private UserAdapter adapter;
    private UserDbController controller;
    private SessionController sessionController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        // Disable Night Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // Assing Variables
        ids = new ArrayList<>();
        emails = new ArrayList<>();

        recycler = findViewById(R.id.user_recycler);

        controller = new UserDbController(getBaseContext());
        sessionController = new SessionController(new Session(getApplicationContext()));

        Button backButton = findViewById(R.id.back_button);
        ImageButton returnButton = findViewById(R.id.return_button);


        // Fill data
        fillArrayList();
        attachAdapter();

        backButton.setOnClickListener(view -> finish());
        returnButton.setOnClickListener(view -> finish());
    }

    @Override
    protected void onStart() {
        super.onStart();
        clearData();
        fillArrayList();
        attachAdapter();
        checkSession();
    }

    public void fillArrayList() {
        Cursor cursor = controller.readUsers();
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                ids.add(cursor.getString(0));
                emails.add(cursor.getString(1));
            }
        } else {
            Toast.makeText(this, "Não há dados para exibir", Toast.LENGTH_SHORT).show();
        }
    }

    public void attachAdapter() {
        adapter = new UserAdapter(getApplicationContext(), ids, emails);
        recycler.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(manager);
    }

    public void clearData() {
        adapter.clearData();
    }

    public void checkSession() {
        String userEmail = sessionController.getSession();

        if (userEmail == null) {
            Intent intent = new Intent(getApplicationContext(), UserLogin.class);
            startActivity(intent);
        }
    }
}