package com.example.salesrecord.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.salesrecord.R;
import com.example.salesrecord.utils.CpfMaskUtil;
import com.example.salesrecord.utils.CurrencyMaskUtil;

import java.util.Locale;

public class SalesRegister extends AppCompatActivity {
    private final Locale mLocale = new Locale("pt", "BR");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_register);

        // Assign Variables
        EditText buyerInput = findViewById(R.id.buyer);
        EditText cpfInput = findViewById(R.id.cpf);
        EditText descInput = findViewById(R.id.sale_desc);
        EditText valueInput = findViewById(R.id.sale_value);
        EditText paidValueInput = findViewById(R.id.paid_value);
        EditText currencyInput = findViewById(R.id.currency);
        Button backButton = findViewById(R.id.back_button);
        ImageButton returnButton = findViewById(R.id.return_button);

        // Set CPF input mask to input
        cpfInput.addTextChangedListener(CpfMaskUtil.cpfMasker(cpfInput, CpfMaskUtil.FORMAT_CPF));

        // Price input mask
        valueInput.addTextChangedListener(new CurrencyMaskUtil(valueInput, mLocale));
        paidValueInput.addTextChangedListener(new CurrencyMaskUtil(paidValueInput, mLocale));
        currencyInput.addTextChangedListener(new CurrencyMaskUtil(currencyInput, mLocale));

        // Finish Activity Methods
        backButton.setOnClickListener(view -> finish());
        returnButton.setOnClickListener(view -> finish());
    }
}