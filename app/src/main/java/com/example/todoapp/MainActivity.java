package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView label = findViewById(R.id.mainLabel);
        label.setText("Make the to-do list here! Daily list on the top and other lists on the buttom");

        Button dailyList = findViewById(R.id.DailyList);
        dailyList.setOnClickListener(v -> {
            Intent intent = new Intent(this, DailyListActivity.class);
            startActivity(intent);
        });

        Button customList = findViewById(R.id.CustomList);
        customList.setOnClickListener(v -> {
            Intent intent = new Intent(this, CustomListActivity.class);
            startActivity(intent);
        });
    }
}
