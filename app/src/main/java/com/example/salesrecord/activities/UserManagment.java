package com.example.salesrecord.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salesrecord.R;
import com.example.salesrecord.controller.SalesDbController;
import com.example.salesrecord.controller.SessionController;
import com.example.salesrecord.controller.UserDbController;
import com.example.salesrecord.model.Session;

public class UserManagment extends AppCompatActivity {
    private TextView id, user_name, user_email;
    private String code;
    private Button deleteUserButton;
    private UserDbController controller;
    private SessionController sessionController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_managment);
        // Disable Night Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // Get Sale ID
        code = getIntent().getStringExtra("id");

        // Assing Variables
        controller = new UserDbController(getBaseContext());
        sessionController = new SessionController(new Session(getApplicationContext()));

        id = findViewById(R.id.user_id_value);
        user_name = findViewById(R.id.user_name_value);
        user_email = findViewById(R.id.user_email_value);

        fillTable();

        Button backButton = findViewById(R.id.back_button);
        deleteUserButton = findViewById(R.id.delete_user_button);
        ImageButton returnButton = findViewById(R.id.return_button);

        // Finish Activity buttons
        backButton.setOnClickListener(view -> finish());
        returnButton.setOnClickListener(view -> finish());

        deleteUserButton.setOnClickListener(view -> {
            controller.deleteUser(Integer.parseInt(id.getText().toString()));
            finish();
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        checkSession();
    }

    public void fillTable() {
        Cursor cursor = controller.readUser(Integer.parseInt(code));
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                id.setText(cursor.getString(0));
                user_name.setText(cursor.getString(1));
                user_email.setText(cursor.getString(2));
            }
        } else {
            Toast.makeText(this, "Erro ao exibir detalhes do usuário", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkSession() {
        String userEmail = sessionController.getSession();

        if (userEmail == null) {
            Intent intent = new Intent(getApplicationContext(), UserLogin.class);
            startActivity(intent);
        }
    }
}