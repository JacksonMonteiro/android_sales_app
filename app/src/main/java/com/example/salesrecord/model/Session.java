package com.example.salesrecord.model;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    protected SharedPreferences sharedPreferences;
    protected SharedPreferences.Editor editor;

    private static final String SHARED_PREF_NAME = "session";

    public Session(Context context) {
        this.sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        this.editor = this.sharedPreferences.edit();
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public SharedPreferences.Editor getEditor() {
        return this.editor;
    }

}
