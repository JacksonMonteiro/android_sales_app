package com.example.salesrecord.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.salesrecord.database.CreateDB;

public class SalesDbController extends CreateDB {
    private SQLiteDatabase db;

    public SalesDbController(Context context) {
        super(context);
    }

    public String createSale(String buyer, String cpf, String desc, String value, String paidValue, String currency) {
        ContentValues values = new ContentValues();
        long result;

        db = this.getWritableDatabase();
        values.put(CreateDB.BUYER, buyer);
        values.put(CreateDB.CPF, cpf);
        values.put(CreateDB.DESC, desc);
        values.put(CreateDB.VALUE, value);
        values.put(CreateDB.PAID_VALUE, paidValue);
        values.put(CreateDB.CURRENCY, currency);

        result = db.insert(CreateDB.SALES_TABLE, null, values);
        db.close();

        if (result != -1) {
            return "Venda registrada com sucesso";
        } else {
            return "Falha ao registrar a venda";
        }
    }
}
