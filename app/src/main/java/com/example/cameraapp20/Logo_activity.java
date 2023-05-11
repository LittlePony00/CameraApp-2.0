package com.example.cameraapp20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class Logo_activity extends AppCompatActivity {
    TextView textView;
    LottieAnimationView lottieAnimationView;
    SharedPreferences sharedPreferences;
    boolean nightMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        mode();

        textView = findViewById(R.id.logo_text);
        lottieAnimationView = findViewById(R.id.lottie);

        textView.animate().translationY(-1500).setDuration(2600).setStartDelay(0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 3000);
    }


    private void mode() {
        sharedPreferences = getSharedPreferences("MODE", MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("night", false);

        if (nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }


//    private void setText() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                textView.setText("C");
//            }
//        }, 700);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                textView.append("a");
//            }
//        }, 1000);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                textView.append("m");
//            }
//        }, 1100);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                textView.append("e");
//            }
//        }, 1300);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                textView.append("r");
//            }
//        }, 1400);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                textView.append("a");
//            }
//        }, 1500);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                textView.append("A");
//            }
//        }, 1600);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                textView.append("p");
//            }
//        }, 1700);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                textView.append("p");
//            }
//        }, 1800);
//    }
}