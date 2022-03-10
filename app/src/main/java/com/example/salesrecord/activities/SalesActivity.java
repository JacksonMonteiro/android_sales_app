package com.example.salesrecord.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.salesrecord.R;
import com.example.salesrecord.adapter.SalesAdapter;
import com.example.salesrecord.controller.SalesDbController;
import com.example.salesrecord.controller.SessionController;
import com.example.salesrecord.model.Session;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SalesActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private RecyclerView recycler;
    private ArrayList<String> buyers, values, ids;
    private String admin_email;
    private SalesDbController controller;
    private SalesAdapter adapter;
    private SessionController sessionController;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        // Disable Night Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // Assign variables
        Button insertSaleButton = findViewById(R.id.create_sale_button);
        navigationView = findViewById(R.id.bottom_navigation);

        recycler = findViewById(R.id.recyclerView);

        controller = new SalesDbController(getBaseContext());
        sessionController = new SessionController(new Session(getApplicationContext()));
        admin_email = sessionController.getSession();

        buyers = new ArrayList<>();
        values = new ArrayList<>();
        ids = new ArrayList<>();

        fillArrayList();
        attachAdapter();

        // Button Methods
        insertSaleButton.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), SalesRegister.class)));

        // set selected item and perform selection list
        navigationView.setSelectedItemId(R.id.home);
        navigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    return true;
                case R.id.config:
                    Intent intent = new Intent(getApplicationContext(), ConfigActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                case R.id.exit:
                    sessionController.removeSession();
                    startActivity(new Intent(getApplicationContext(), UserLogin.class));
                    finish();
                    return true;
            }
            return false;
        });

        // Admin Acess
        isAdminAccess();
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkSession();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        navigationView.setSelectedItemId(R.id.home);

        clearData();
        fillArrayList();
        attachAdapter();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        DividerItemDecoration divider = new DividerItemDecoration(recycler.getContext(), manager.getOrientation());
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(manager);
        recycler.addItemDecoration(divider);
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

    public void isAdminAccess() {
        if (!admin_email.equals("admin@registra.com")) {
            MenuItem item = navigationView.getMenu().findItem(R.id.config);
            item.setVisible(false);
        }
    }
}