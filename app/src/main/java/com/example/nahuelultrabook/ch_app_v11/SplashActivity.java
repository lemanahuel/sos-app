package com.example.nahuelultrabook.ch_app_v11;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    private final int duration = 3000;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
            Intent mainIntent = new Intent(SplashActivity.this, SigninActivity.class);
            SplashActivity.this.startActivity(mainIntent);
            SplashActivity.this.finish();
            }
        }, duration);
    }
}
