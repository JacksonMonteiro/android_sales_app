package com.example.salesrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.salesrecord.activities.UserLogin;

public class MainActivity extends AppCompatActivity {
    // Splash Screen Timeout
    private static final int SPLASH_SCREEN_TIMEOUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(() -> {
            Intent startIntent = new Intent(MainActivity.this, UserLogin.class);
            startActivity(startIntent);
            finish();
        }, SPLASH_SCREEN_TIMEOUT);
    }
}