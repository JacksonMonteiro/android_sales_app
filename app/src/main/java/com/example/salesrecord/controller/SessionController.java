package com.example.salesrecord.controller;
import android.content.SharedPreferences;

import com.example.salesrecord.model.Session;
import com.example.salesrecord.model.User;

public class SessionController {
    private Session savedSession;
    private SharedPreferences prefs;
    private SharedPreferences.Editor edit;
    private static final String EMAIL_KEY = "email_key";

    public SessionController(Session s) {
        this.savedSession = s;
        this.prefs = s.getSharedPreferences();
        this.edit = s.getEditor();
    }

    public void saveSession(User user) {
        String email = user.getEmail();
        this.edit.putString(EMAIL_KEY, email).commit();
    }

    public String getSession() {
        return this.prefs.getString(EMAIL_KEY, null);
    }

    public void removeSession() {
        edit.putString(EMAIL_KEY, null).commit();
    }
}
