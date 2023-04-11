package com.example.practice3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practice3.R;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private TextView mLoginLink;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginLink = findViewById(R.id.login_link);
        relativeLayout = findViewById(R.id.main_layout);
        mLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean signingOut = getIntent().getBooleanExtra(getResources().getString(R.string.signing_out_extra), false);

        if (signingOut) {
            //Toast.makeText(this, getResources().getString(R.string.sign_out_msg), Toast.LENGTH_SHORT).show();
            Snackbar.make(relativeLayout, getResources().getString(R.string.sign_out_msg), Snackbar.LENGTH_LONG).show();
        }
    }
}