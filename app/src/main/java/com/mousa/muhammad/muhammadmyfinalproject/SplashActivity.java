package com.mousa.muhammad.muhammadmyfinalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private ImageView ivWelcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ivWelcome=(ImageView)findViewById(R.id.ivWelcome);
        tvWelcome=(TextView) findViewById(R.id .tvWelcome) ;
    }
    @Override
    protected void onResume() {
        MyThread myThread=new MyThread();
        myThread.start();
        super.onResume();
    }

    public  class MyThread extends Thread
    {
        @Override
        public void run() {

            try {
                sleep(3000);
                Intent i=new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

