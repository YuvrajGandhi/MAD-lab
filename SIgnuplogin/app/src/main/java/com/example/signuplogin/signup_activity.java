package com.example.signuplogin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class signup_activity extends AppCompatActivity {

    String username, password;
    String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText etusername = findViewById(R.id.susername);
        EditText etpassword = findViewById(R.id.spassword);
        Button signup = findViewById(R.id.btnsignup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etusername.getText().toString(); //to get username from signup page
                password = etpassword.getText().toString(); //to get password from signup page

                if(password.matches(regex)){
                    //Intent is used to lauch applications
                    Intent intent = new Intent(signup_activity.this, login_activity.class);
                    intent.putExtra("username",username);
                    intent.putExtra("password",password);
                    startActivity(intent);
                }else{
                    //display message when password doesnt match
                    Toast.makeText(getApplicationContext(), "Your password didn't match the validation criteria",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}