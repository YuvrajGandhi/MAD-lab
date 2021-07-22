package com.example.callandsave;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button one,two,three,four,five,six,seven,eight,nine,zero,star,hash,save,call,del;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);
        star = findViewById(R.id.star);
        hash = findViewById(R.id.hash);
        save = findViewById(R.id.save);
        call = findViewById(R.id.call);
        del = findViewById(R.id.back);

        display = findViewById(R.id.editTextPhone);

        zero.setOnClickListener(this::setValue);
        one.setOnClickListener(this::setValue);
        two.setOnClickListener(this::setValue);
        three.setOnClickListener(this::setValue);
        four.setOnClickListener(this::setValue);
        five.setOnClickListener(this::setValue);
        six.setOnClickListener(this::setValue);
        seven.setOnClickListener(this::setValue);
        eight.setOnClickListener(this::setValue);
        nine.setOnClickListener(this::setValue);
        star.setOnClickListener(this::setValue);
        hash.setOnClickListener(this::setValue);

        call.setOnClickListener(this::onClick);
        save.setOnClickListener(this::onClick);
        del.setOnClickListener(this::onClick);

    }

    private void setValue(View v) {
        if (save.getVisibility() == View.GONE)
            save.setVisibility(View.VISIBLE);
        display.setText(display.getText() + ((Button)v).getText().toString());
        System.out.println(((Button)v).getText());
        System.out.println(display.getText());
    }


    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                if (display.length() == 0) return; // Don't do anything when input is empty
                display.setText(display.getText().subSequence(0, display.length()-1));
                if (display.length() == 0)
                    save.setVisibility(View.GONE);
                break;
            case R.id.call:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE }, 1);
                } else {
                    String phoneNo = display.getText().toString();
                    Intent intentCall = new Intent(Intent.ACTION_CALL);
                    intentCall.setData(Uri.parse("tel:"+phoneNo));
                    startActivity(intentCall);
                }
                break;
            case R.id.save:
                if (display.length() == 0) return; // Don't do anything when input is empty
                String contact = display.getText().toString();
                Intent intentSave = new Intent(ContactsContract.Intents.SHOW_OR_CREATE_CONTACT, Uri.parse("tel:"+contact));
                startActivity(intentSave);
                break;
        }
    }
}