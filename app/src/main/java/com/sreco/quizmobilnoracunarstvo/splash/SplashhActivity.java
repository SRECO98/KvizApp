package com.sreco.quizmobilnoracunarstvo.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.sreco.quizmobilnoracunarstvo.MainActivity;
import com.sreco.quizmobilnoracunarstvo.R;

public class SplashhActivity extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    private static final int SPLASH_TIME_OUT=5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        lottieAnimationView = findViewById(R.id.animationView);
        lottieAnimationView.setMinAndMaxFrame(10, 100);
        nextScreen();

    }

    private void nextScreen() {
        new Handler().postDelayed(()-> {
            Intent intent = new Intent(SplashhActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_TIME_OUT);
    }
}