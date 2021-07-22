package com.example.signuplogin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class login_activity extends AppCompatActivity {

    EditText username, password;
    int count = 0; //Count the number of login attempts

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String usrName = getIntent().getStringExtra("username"); //gets the username from previous intent
        String pwd = getIntent().getStringExtra("password"); //gets the password from previous intent
        Button login = findViewById(R.id.btnlogin);

        username = findViewById(R.id.lusername);
        password =  findViewById(R.id.lpassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = username.getText().toString(); //get username from login page
                String pas = password.getText().toString(); //get password from login page

                //check if username from signup page and login page are same, as well as for password

                if(uname.equals(usrName)&&pas.equals(pwd)){
                    Intent intent = new Intent(login_activity.this, welcome_activity.class);
                    intent.putExtra("name",uname);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Username and Password didn't match, Please try again!", Toast.LENGTH_SHORT).show();
                    count++;

                    if(count>2){
                        //check if login fails more than 2 times
                        login.setEnabled(false);
                    }
                }
            }
        });
    }
}