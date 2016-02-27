package com.knightsofnull.int20h.authentication.registration;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Registration {

    public static final String NAME = "Name";
    public static final String SURNAME = "Surname";
    public static final String EMAIL = "Email";
    public static final String PASSWORD = "Password";

    public static final String USER = "user";

    SharedPreferences sharedpreferences;

    void onRegistration(Context context, String name, String surname, String email, String password) {

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        try {
            jsonObject.put(NAME, name);
            jsonObject.put(SURNAME, surname);
            jsonObject.put(EMAIL, email);
            jsonObject.put(PASSWORD, password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        jsonArray.put(jsonArray);

        String jsonText = jsonArray.toString();

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(USER, jsonText);
        editor.commit();
    }
}
