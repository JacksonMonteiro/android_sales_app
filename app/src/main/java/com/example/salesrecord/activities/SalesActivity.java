package com.example.salesrecord.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.salesrecord.R;
import com.example.salesrecord.adapter.SalesAdapter;
import com.example.salesrecord.controller.SalesDbController;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SalesActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private RecyclerView recycler;
    private ArrayList<String> buyers, values, ids;
    private SalesDbController controller;
    private SalesAdapter adapter;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        // Assign variables
        navigationView = findViewById(R.id.bottom_navigation);
        Button insertSaleButton = findViewById(R.id.create_sale_button);
        recycler = findViewById(R.id.recyclerView);
        controller = new SalesDbController(getBaseContext());

        buyers = new ArrayList<>();
        values = new ArrayList<>();
        ids = new ArrayList<>();

        fillArrayList();
        attachAdapter();

        // Button Methods
        insertSaleButton.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), SalesRegister.class)));

        // set selected item
        navigationView.setSelectedItemId(R.id.home);
        // Perform selection list
        navigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
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
        super.onRestart();

        navigationView.setSelectedItemId(R.id.home);

        clearData();
        fillArrayList();
        attachAdapter();
    }

    public void fillArrayList() {
        Cursor cursor = controller.readSales();
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                ids.add(cursor.getString(0));
                buyers.add(cursor.getString(1));
                values.add(cursor.getString(2));
            }
        } else {
            Toast.makeText(this, "Não há dados para exibir", Toast.LENGTH_SHORT).show();
        }
    }

    public void attachAdapter() {
        adapter = new SalesAdapter(getApplicationContext(), buyers, values, ids);
        recycler.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(manager);
    }

    public void clearData() {
        adapter.clearData();
    }
}