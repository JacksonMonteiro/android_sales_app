package com.example.salesrecord.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.salesrecord.R;
import com.example.salesrecord.controller.SessionController;
import com.example.salesrecord.model.Session;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ConfigActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private SessionController controller;
    private Button mngUsersBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        // Disable Night Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // Assing Variables
        mngUsersBtn = findViewById(R.id.managa_users_button);

        navigationView = findViewById(R.id.bottom_navigation);
        controller = new SessionController(new Session(getApplicationContext()));

        // Button Methods
        mngUsersBtn.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), UsersActivity.class));
        });

        // set selected item
        navigationView.setSelectedItemId(R.id.config);
        // Perform selection list
        navigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    Intent intent = new Intent(getApplicationContext(), SalesActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                case R.id.config:
                    return true;
                case R.id.exit:
                    controller.removeSession();
                    startActivity(new Intent(getApplicationContext(), UserLogin.class));
                    finish();
                    return true;
            }
            return false;
        });
    }

    @Override
    protected void onRestart() {
        navigationView.setSelectedItemId(R.id.config);
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkSession();
    }

    public void checkSession() {
        String userEmail = controller.getSession();

        if (userEmail == null) {
            Intent intent = new Intent(getApplicationContext(), UserLogin.class);
            startActivity(intent);
        }
    }
}