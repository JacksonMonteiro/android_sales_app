package com.example.salesrecord.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDB extends SQLiteOpenHelper {
    protected static final String DB_NAME = "salesrecord";
    protected static final int VERSION = 1;

    // User table
    protected static final String USER_TABLE = "user";
    protected static final String USER_ID = "id";
    protected static final String USERNAME = "username";
    protected static final String EMAIL = "email";
    protected static final String PASSWORD = "password";

    public CreateDB(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // User Table Database
        String Usersql = "CREATE TABLE " + USER_TABLE + "(" +
                USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                USERNAME + " TEXT," +
                EMAIL + " TEXT," +
                PASSWORD + " TEXT)";
        db.execSQL(Usersql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);

    }
}
