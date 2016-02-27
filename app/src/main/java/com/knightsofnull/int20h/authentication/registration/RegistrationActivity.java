package com.knightsofnull.int20h.authentication.registration;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.knightsofnull.int20h.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String NAME = "Name";
    public static final String SURNAME = "Surname";
    public static final String EMAIL = "Email";
    public static final String PASSWORD = "Password";

    public static final String USER = "user";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ((Button) findViewById(R.id.btnRegistration)).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        String name = ((TextView) findViewById(R.id.nameRegistration)).getText().toString();
        String surname = ((TextView) findViewById(R.id.surnameRegistration)).getText().toString();
        String email = ((TextView) findViewById(R.id.emailRegistration)).getText().toString();
        String password = ((TextView) findViewById(R.id.passwordRegistration)).getText().toString();

        if (name == null || surname == null || email == null || password == null) {
            Toast toast = Toast.makeText(this, "Input all fields", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

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

        Toast toast = Toast.makeText(this, "Save", Toast.LENGTH_SHORT);
        toast.show();

    }
}
