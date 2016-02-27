package com.knightsofnull.int20h.authentication.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.knightsofnull.int20h.R;
import com.knightsofnull.int20h.authentication.registration.RegistrationActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        ((Button) findViewById(R.id.btnRegistration)).setOnClickListener(this);
        ((Button) findViewById(R.id.btnLogin)).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnRegistration: {
                Intent intent = new Intent(this, RegistrationActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btnLogin: {
                String email = (String) ((TextView) findViewById(R.id.email)).getText();
                String password = (String) ((TextView) findViewById(R.id.password)).getText();

                if (email == null || password == null) {
                    Toast toast = Toast.makeText(this, "Input all fields", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                //check in base

                break;
            }
        }


    }
}
