package com.example.battery_otter_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // Ensure you set the correct layout

        // Load fade-in animation
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        View splashView = findViewById(R.id.splash_view); // Replace with your splash view ID
        splashView.startAnimation(fadeIn);

        // Use a Handler to delay the transition
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Load fade-out animation
                Animation fadeOut = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fade_out);
                splashView.startAnimation(fadeOut);

                fadeOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {}

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // Start MainActivity
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Call finish to remove SplashActivity from the back stack
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {}
                });
            }
        }, 3000); // 3000 milliseconds (3 seconds) delay
    }
}
