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

    protected static final String SALES_TABLE = "sales";
    protected static final String SALES_ID = "id";
    protected static final String BUYER = "buyer";
    protected static final String CPF = "cpf";
    protected static final String DESC = "desc";
    protected static final String VALUE = "value";
    protected static final String PAID_VALUE = "paid_value";
    protected static final String CURRENCY = "currency";

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

        String SalesSQL = "CREATE TABLE " + SALES_TABLE + "(" +
                SALES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                BUYER + " TEXT," +
                CPF + " INTEGER," +
                DESC + " TEXT," +
                VALUE + " INTEGER," +
                PAID_VALUE + " INTEGER," +
                CURRENCY + " INTEGER)";

        db.execSQL(Usersql);
        db.execSQL(SalesSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SALES_TABLE);
        onCreate(db);
    }
}
