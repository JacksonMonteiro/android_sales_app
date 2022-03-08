package com.example.salesrecord.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.salesrecord.database.CreateDB;

public class SalesDbController  {
    private SQLiteDatabase db;
    private CreateDB database;

    public SalesDbController(Context context) {
        database = new CreateDB(context);
    }

    public String createSale(String buyer, String cpf, String desc, String value, String paidValue, String currency) {
        ContentValues values = new ContentValues();
        long result;

        db = database.getWritableDatabase();
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

    public Cursor readSales() {
        String query = "SELECT " + CreateDB.BUYER + ", " + CreateDB.VALUE + " FROM " + CreateDB.SALES_TABLE;
        db = database.getReadableDatabase();
        Cursor cursor = null;

        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }
}
