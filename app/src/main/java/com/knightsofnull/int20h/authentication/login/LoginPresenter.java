package com.knightsofnull.int20h.authentication.login;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class LoginPresenter {

    public static final String USER = "user";

    void onLogin(Context context, String login, String password) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        Map<String, ?> allEntries = preferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {

            //Toast t = Toast.makeText(context, jsonObject)
        }

    }

    void onRegistration() {
        //transition to Registration onRegistration()
    }

}
