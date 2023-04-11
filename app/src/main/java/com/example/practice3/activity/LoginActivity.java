package com.example.practice3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practice3.R;

public class LoginActivity extends AppCompatActivity {

    private Button mBackButton;
    private TextView mValidationLabel;
    private EditText mUsernameInput;
    private EditText mPasswordInput;
    private Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mBackButton = findViewById(R.id.back_to_main_btn);
        mValidationLabel = findViewById(R.id.validation_label);
        mUsernameInput = findViewById(R.id.username_input);
        mPasswordInput = findViewById(R.id.password_input);
        mLoginButton = findViewById(R.id.login_btn);

        mBackButton.setOnClickListener(new LoginActivityListener());
        mLoginButton.setOnClickListener(new LoginActivityListener());
    }

    class LoginActivityListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.back_to_main_btn: navigateBack(); break;
                case R.id.login_btn: login(); break;
                default: break;
            }
        }

        private void login() {
            String username = mUsernameInput.getText().toString();
            String password = mPasswordInput.getText().toString();

            if(username.equals("admin") && password.equals("admin")) {
                mValidationLabel.setVisibility(View.GONE);
                navigateToProductSearch();
            } else {
                mValidationLabel.setVisibility(View.VISIBLE);
            }
        }

        private void navigateToProductSearch() {
            startActivity(new Intent(LoginActivity.this, SearchProductActivity.class));
        }

        private void navigateBack() {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }
}