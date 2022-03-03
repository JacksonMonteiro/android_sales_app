package com.example.salesrecord.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.salesrecord.R;
import com.example.salesrecord.utils.CpfMaskUtil;

import java.text.NumberFormat;

public class SalesRegister extends AppCompatActivity {
    private EditText buyerInput, cpfInput, descInput, valueInput, paidValueInput, currencyInput;
    private String current = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_register);

        // Assign Variables
        buyerInput = findViewById(R.id.buyer);
        cpfInput = findViewById(R.id.cpf);
        descInput = findViewById(R.id.sale_desc);
        valueInput = findViewById(R.id.sale_value);
        paidValueInput = findViewById(R.id.paid_value);
        currencyInput = findViewById(R.id.currency);

        // Set CPF input mask to input
        cpfInput.addTextChangedListener(CpfMaskUtil.cpfMasker(cpfInput, CpfMaskUtil.FORMAT_CPF));
    }
}