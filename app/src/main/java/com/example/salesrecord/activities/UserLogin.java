package com.example.salesrecord.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salesrecord.R;
import com.example.salesrecord.controller.UserDbController;

import java.util.regex.Pattern;

public class UserLogin extends AppCompatActivity {
    private EditText emailInput, passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
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
                    Intent intent = new Intent(UserLogin.this, SalesActivity.class);
                    startActivity(intent);
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

    public boolean validateInputs(String email, String pass) {
        String regex = "^(.+)@(\\S+)$";
        boolean result = false;

        if (email.length() == 0) {
            emailInput.requestFocus();
            emailInput.setError("Seu e-mail não pode ser vazio ");
        } else if (!Pattern.compile(regex).matcher(email).matches()) {
            emailInput.requestFocus();
            emailInput.setError("Utilize um e-mail válido (exemplo@email.com)");
        } else if(pass.length() == 0) {
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

}