package com.example.salesrecord.activities;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.salesrecord.model.Session;

public class SalesManager extends AppCompatActivity {
    private TextView id, sale_buyer, sale_cpf, sale_description, sale_value, sale_paidValue, sale_currency;
    private String code;
    private SalesDbController controller;
    private SessionController sessionController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_manager);
        // Get Sale ID
        code = getIntent().getStringExtra("id");

        // Assing Variables
        controller = new SalesDbController(getBaseContext());
        sessionController = new SessionController(new Session(getApplicationContext()));

        id = findViewById(R.id.id_value);
        sale_buyer = findViewById(R.id.buyerName_value);
        sale_cpf = findViewById(R.id.cpfValue);
        sale_description = findViewById(R.id.description_value);
        sale_value = findViewById(R.id.buyValue);
        sale_paidValue = findViewById(R.id.paidValue);
        sale_currency = findViewById(R.id.currency_value);

        fillTable();

        Button backButton = findViewById(R.id.back_button);
        ImageButton returnButton = findViewById(R.id.return_button);

        // Finish Activity buttons
        backButton.setOnClickListener(view -> finish());
        returnButton.setOnClickListener(view -> finish());
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkSession();
    }

    public void fillTable() {
        Cursor cursor = controller.readSale(Integer.parseInt(code));
        if (cursor.getCount() != 0) {
            while(cursor.moveToNext()) {
                id.setText(cursor.getString(0));
                sale_buyer.setText(cursor.getString(1));
                sale_cpf.setText(cursor.getString(2));
                sale_description.setText(cursor.getString(3));
                sale_value.setText(cursor.getString(4));
                sale_paidValue.setText(cursor.getString(5));
                sale_currency.setText(cursor.getString(6));
            }
        } else {
            Toast.makeText(this, "Erro ao exibir detalhes da venda", Toast.LENGTH_SHORT).show();
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