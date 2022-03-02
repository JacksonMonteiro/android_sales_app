package com.example.salesrecord.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.salesrecord.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SalesActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        navigationView = findViewById(R.id.bottom_navigation);
    }
}