package com.example.salesrecord.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.salesrecord.R;
import com.example.salesrecord.controller.SalesDbController;
import com.example.salesrecord.utils.CpfMaskUtil;
import com.example.salesrecord.utils.MoneyTextWatcher;

import java.text.NumberFormat;

public class SalesRegister extends AppCompatActivity {
    private EditText buyerInput, cpfInput, descInput, valueInput, paidValueInput;
    private Button backButton, registerSaleButton;
    private ImageButton returnButton;

    // Variables
    float valueFloat = 0;
    float paidValueFloat = 0;

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
        backButton = findViewById(R.id.back_button);
        returnButton = findViewById(R.id.return_button);
        registerSaleButton = findViewById(R.id.create_sale_button);

        // Set CPF and Currency input mask to inputs
        cpfInput.addTextChangedListener(CpfMaskUtil.cpfMasker(cpfInput, CpfMaskUtil.FORMAT_CPF));
        valueInput.addTextChangedListener(new MoneyTextWatcher(valueInput));
        paidValueInput.addTextChangedListener(new MoneyTextWatcher(paidValueInput));

        // Finish Activity Methods
        backButton.setOnClickListener(view -> finish());
        returnButton.setOnClickListener(view -> finish());

        // Register Sale Method
        registerSaleButton.setOnClickListener(view -> {
            SalesDbController controller = new SalesDbController(getBaseContext());
            String currency = "";

            String buyer_name = buyerInput.getText().toString();
            String cpf = cpfInput.getText().toString();
            String sale_desc = descInput.getText().toString();
            String value = valueInput.getText().toString();
            String paidValue = paidValueInput.getText().toString();

            if (validate(buyer_name, cpf, value, paidValue)) {
                Toast.makeText(this, "Validado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean validate(String buyer, String cpf, String value, String paidValue) {
        boolean result = false;

        if (buyer.length() == 0) {
            buyerInput.requestFocus();
            buyerInput.setError("O nome do comprador não pode estar vazio");
        } else if (cpf.length() == 0) {
            cpfInput.requestFocus();
            cpfInput.setError("O CPF do comprador não pode estar vazio");
        } else if (cpf.length() < 11) {
            cpfInput.requestFocus();
            cpfInput.setError("O CPF do comprador precisa conter 11 digitos");
        } else if (value.length() == 0) {
            valueInput.requestFocus();
            valueInput.setError("O valor da compra não pode estar vazio");
        } else if (paidValue.length() == 0) {
            paidValueInput.requestFocus();
            paidValueInput.setError("O valor pago não pode estar vazio");
        } else {
            String formattedValue = valueInput.getText().toString().substring(2);
            String formattedPaidValue = paidValueInput.getText().toString().substring(2);

            if (formattedValue.length() > 6) {
                valueFloat = Float.parseFloat(formattedValue.
                        replaceAll("\\.", "")
                        .replaceAll(",", ".")
                        .replaceAll("\\s+", ""));
            } else {
                valueFloat = Float.parseFloat(formattedValue
                        .replaceAll(",", ".")
                        .replaceAll("\\s+", ""));
            }

            if (formattedPaidValue.length() > 6) {
                paidValueFloat = Float.parseFloat(formattedPaidValue
                        .replaceAll("\\.", "")
                        .replaceAll(",", ".")
                        .replaceAll("\\s+", ""));
            } else {
                paidValueFloat = Float.parseFloat(formattedPaidValue
                        .replaceAll(",", ".")
                        .replaceAll("\\s+", ""));
            }

            if (valueFloat > paidValueFloat) {
                paidValueInput.requestFocus();
                paidValueInput.setError("Valor insuficiente para pagar a compra");
            } else {
                result = true;
            }
        }

        return result;
    }
}