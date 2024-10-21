package com.example.tictactoe;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        TextView resultTextView = findViewById(R.id.resultTextView);

        // Get the result from the intent
        String result = getIntent().getStringExtra("RESULT");
        resultTextView.setText("Result: " + result); // Display result
    }
}