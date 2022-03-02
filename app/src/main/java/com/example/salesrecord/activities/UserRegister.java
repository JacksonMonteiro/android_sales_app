package com.example.salesrecord.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.salesrecord.R;
import com.example.salesrecord.controller.UserDbController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRegister extends AppCompatActivity {
    private ImageButton returnButton;
    private Button backButton, createUserButton;
    private EditText usernameInput, emailInput, passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        // Set Views
        usernameInput = findViewById(R.id.username);
        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);

        returnButton = findViewById(R.id.return_button);

        backButton = findViewById(R.id.back_button);
        createUserButton = findViewById(R.id.create_user_button);

        // Create User Action
        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDbController controller = new UserDbController(getBaseContext());
                String usr = usernameInput.getText().toString();
                String email = emailInput.getText().toString();
                String pass = passwordInput.getText().toString();

                if (validateInputs(usr, email, pass)) {
                    if (!controller.userExits(email)) {
                        String queryResult;
                        queryResult = controller.createUser(usr, email, pass);
                        if (queryResult.equals("Conta criada com sucesso")) {
                            Toast.makeText(getApplicationContext(), queryResult, Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), queryResult, Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "E-mail já cadastrado! Utilize outro para cadastrar-se", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Finish Activity Buttons
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public boolean validateInputs(String usr, String email, String pass) {
        String EmailRegEX = "^(.+)@(\\S+)$";
        boolean result = false;

        if (usr.length() == 0) {
            usernameInput.requestFocus();
            usernameInput.setError("Seu nome de usuário não pode ser vazio ");
        } else if (email.length() == 0) {
            emailInput.requestFocus();
            emailInput.setError("Seu e-mail não pode ser vazio ");
        } else if (!patternMatch(email, EmailRegEX)) {
            emailInput.requestFocus();
            emailInput.setError("Utilize um e-mail válido (exemplo@email.com)");
        } else if (pass.length() < 4) {
            passwordInput.requestFocus();
            passwordInput.setError("Sua senha precisa ter no mínimo 4 caracteres");
        } else {
            result = true;
        }

        return result;
    }

    public boolean patternMatch(String email, String regex) {
        return Pattern.compile(regex).matcher(email).matches();
    }
}
