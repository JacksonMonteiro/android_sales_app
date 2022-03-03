package com.example.salesrecord.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.salesrecord.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SalesActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        // Assign variables
        navigationView = findViewById(R.id.bottom_navigation);
        Button insertSaleButton = findViewById(R.id.create_sale_button);

        // Button Methods
        insertSaleButton.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), SalesRegister.class)));

        // Navigation Menu

        // set selected item
        navigationView.setSelectedItemId(R.id.home);
        // Perform selection list
        navigationView.setOnNavigationItemSelectedListener(item -> {
            switch(item.getItemId()) {
                case R.id.home:
                    return true;
                case R.id.config:
                    Intent intent = new Intent(getApplicationContext(), ConfigActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
            }
            return false;
        });
    }

    @Override
    protected void onRestart() {
        navigationView.setSelectedItemId(R.id.home);
        super.onRestart();
    }

}