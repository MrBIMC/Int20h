package com.knightsofnull.int20h.authentication.registration;

import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

public class Registration {

    public static final String NAME = "Name";
    public static final String SURNAME = "Surname";
    public static final String EMAIL = "Email";
    public static final String PASSWORD = "Password";

    public static final String USER = "user";

    SharedPreferences sharedpreferences;

    void onRegistration(String name, String surname, String email, String password) {

        JSONObject obj = new JSONObject();

        try {
            obj.put(NAME, name);
            obj.put(SURNAME, surname);
            obj.put(EMAIL, email);
            obj.put(PASSWORD, password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String jsonText = obj.toString();

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(USER, jsonText);
        editor.commit();

    }
}
