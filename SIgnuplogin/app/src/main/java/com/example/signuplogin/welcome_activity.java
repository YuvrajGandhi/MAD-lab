package com.example.signuplogin;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class welcome_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        String name = getIntent().getStringExtra("name"); //get the username
        TextView welcomeText = findViewById(R.id.welcome_text);
        welcomeText.setText("Hello, Welcome " + name); //display welcome message
    }
}