package com.neutrinostruo.signintest;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SignIn extends AppCompatActivity {

    private LinearLayout signInContainer;
    private TextView signUpActivityLink;
    private EditText emailField;
    private EditText passwordField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_in);
        signInContainer = (LinearLayout) findViewById(R.id.signinContainer);
        signInContainer.getBackground().setAlpha(150);
        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        emailField.setTextColor(Color.WHITE);
        passwordField.setTextColor(Color.WHITE);
        emailField.getBackground().setAlpha(100);
        passwordField.getBackground().setAlpha(100);

        signUpActivityLink = (TextView) findViewById(R.id.signUpActivityLink);

        signUpActivityLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this,signUp.class);
                startActivity(intent);
            }
        });
    }
}
