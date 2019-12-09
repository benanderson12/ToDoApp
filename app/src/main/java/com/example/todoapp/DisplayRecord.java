package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DisplayRecord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_record);

        int record = 0;

        if (record < 5) {
            ImageView image = (ImageView)findViewById(R.id.imageGeoff);
            image.setVisibility(View.INVISIBLE);
        }

    }
}
