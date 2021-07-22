package com.example.wallpaperapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button b;
    View sv;
    int[] wallpaper;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b =(Button)findViewById(R.id.button2);
        sv=(View)findViewById(R.id.myscreen);
        wallpaper=new int[]{R.drawable.w1,R.drawable.w2,R.drawable.w3,R.drawable.w4,R.drawable.w5,R.drawable.w6,R.drawable.w7,R.drawable.w8};
        handler=new Handler();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postDelayed(runnable,0);
                b.setVisibility(View.GONE);


            }
        });
    }

    public Runnable runnable=new Runnable() {
        @Override
        public void run() {

            int arlength=wallpaper.length;   //gives length of array
            Random random=new Random();   //object creation of random class
            int rn=random.nextInt(arlength);   //generate a random number

            sv.setBackground(ContextCompat.getDrawable(getApplicationContext(),wallpaper[rn]));

            handler.postDelayed(this,10000);
        }
    };

}