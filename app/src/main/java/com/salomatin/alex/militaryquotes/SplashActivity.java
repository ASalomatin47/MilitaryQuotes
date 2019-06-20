package com.salomatin.alex.militaryquotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

// This activity shows greeting upon application start
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent intent = new Intent(getApplicationContext(),
                MainActivity.class);
        startActivity(intent);
        finish();
    }
}
