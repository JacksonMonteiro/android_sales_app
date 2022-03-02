package com.example.salesrecord.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.salesrecord.database.CreateDB;

public class UserDbController extends CreateDB {
    private SQLiteDatabase db;

    public UserDbController(Context context) {
        super(context);
    }

    public String createUser(String username, String email, String password) {
        ContentValues values = new ContentValues();
        long result = 0;

        if (!userExits(email)) {
            db = this.getWritableDatabase();
            values.put(CreateDB.USERNAME, username);
            values.put(CreateDB.EMAIL, email);
            values.put(CreateDB.PASSWORD, password);

            result = db.insert(CreateDB.USER_TABLE, null, values);
            db.close();

            if (result != -1) {
                return "Conta criada com sucesso";
            } else {
                return "Erro ao criar conta";
            }
        } else {
            return null;
        }
    }

    public boolean userExits(String email) {
        String query = "SELECT * FROM " + CreateDB.USER_TABLE + " WHERE " + CreateDB.EMAIL + " = ?";
        String whereArgs[] = {email};
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count >= 1;
    }

    public boolean isCredentialsOk(String email, String password) {
        String query = "SELECT * FROM " + CreateDB.USER_TABLE + " WHERE " + CreateDB.EMAIL + " = ? AND " + CreateDB.PASSWORD + " = ?";
        String whereArgs[] = {email, password};
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count >= 1;
    }
}
