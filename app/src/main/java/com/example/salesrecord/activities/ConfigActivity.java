package com.example.salesrecord.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.salesrecord.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ConfigActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        // Assing Variables
        navigationView = findViewById(R.id.bottom_navigation);
        Button exit = findViewById(R.id.exit_button);

        // Button Methods
        exit.setOnClickListener(view -> finish());

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
            }
            return false;
        });
    }

    @Override
    protected void onRestart() {
        navigationView.setSelectedItemId(R.id.config);
        super.onRestart();
    }
}