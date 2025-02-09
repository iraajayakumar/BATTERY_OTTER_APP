package com.example.battery_otter_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Predictor predictor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            predictor = new Predictor(this); // Attempt to load the model
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Button predictButton = findViewById(R.id.predictButton);
        predictButton.setOnClickListener(this::onClick);
    }

    private void onClick(View v) {
        // Test with a preset battery drain value (e.g., 2578)
        String mode = predictor.makePrediction(2500);

        // Redirect to the activity based on the mode
        Intent intent;
        switch (mode) {
            case "Balanced":
                intent = new Intent(MainActivity.this, Balanced_Page.class);
                break;
            case "Battery over Performance":
                intent = new Intent(MainActivity.this, Battery_over_Performance_Page.class);
                break;
            case "Performance over Battery":
                intent = new Intent(MainActivity.this, Performance_over_Battery_Page.class);
                break;
            default:
                return; // Do nothing if mode is unknown
        }
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (predictor != null) {
            predictor.close(); // Release model resources
        }
    }
}
