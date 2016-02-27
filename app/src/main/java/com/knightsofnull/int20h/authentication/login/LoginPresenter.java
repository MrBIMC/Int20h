package com.knightsofnull.int20h.authentication.login;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.util.Map;

public class LoginPresenter {

    public static final String USER = "user";

    void onLogin(Context context, String login, String password) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        Map<String, ?> allEntries = preferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {

            String st = entry.getValue().toString();

            Toast t = Toast.makeText(context, st, Toast.LENGTH_SHORT);
            t.show();
        }

    }

    void onRegistration() {
        //transition to Registration onRegistration()
    }

}
