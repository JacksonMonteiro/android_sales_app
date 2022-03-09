package com.example.salesrecord.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.salesrecord.R;
import com.example.salesrecord.controller.SessionController;
import com.example.salesrecord.controller.UserDbController;
import com.example.salesrecord.model.Session;
import com.example.salesrecord.model.User;

import java.util.regex.Pattern;

public class UserLogin extends AppCompatActivity {
    private EditText emailInput, passwordInput;
    private SessionController sessionController;

    // System Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        // Disable Night Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // Assing Variables
        Session session = new Session(getApplicationContext());
        sessionController = new SessionController(session);

        // Edit Texts
        emailInput = findViewById(R.id.email_login);
        passwordInput = findViewById(R.id.password_login);

        // Buttons
        Button registerButton = findViewById(R.id.register_button);
        Button loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(view -> {
            UserDbController controller = new UserDbController(getBaseContext());

            String email = emailInput.getText().toString();
            String pass = passwordInput.getText().toString();

            if (validateInputs(email, pass)) {
                if (controller.isCredentialsOk(email, pass)) {
                    User user = new User();

                    user.setEmail(email);
                    sessionController.saveSession(user);

                    Intent intent = new Intent(UserLogin.this, SalesActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "E-mail ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerButton.setOnClickListener(view -> {
            Intent registerIntent = new Intent(UserLogin.this, UserRegister.class);
            startActivity(registerIntent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkSession();
    }

    // My Methods
    public boolean validateInputs(String email, String pass) {
        String regex = "^(.+)@(\\S+)$";
        boolean result = false;

        if (email.length() == 0) {
            emailInput.requestFocus();
            emailInput.setError("Seu e-mail não pode ser vazio ");
        } else if (!Pattern.compile(regex).matcher(email).matches()) {
            emailInput.requestFocus();
            emailInput.setError("Utilize um e-mail válido (exemplo@email.com)");
        } else if (pass.length() == 0) {
            passwordInput.requestFocus();
            passwordInput.setError("Sua senha não pode ser vazia");
        } else if (pass.length() < 4) {
            passwordInput.requestFocus();
            passwordInput.setError("Sua senha precisa ter no mínimo 4 caracteres");
        } else {
            result = true;
        }

        return result;
    }

    public void checkSession() {
        String userEmail = sessionController.getSession();

        if (userEmail != null) {
            Intent intent = new Intent(getApplicationContext(), SalesActivity.class);
            startActivity(intent);
        }
    }
}