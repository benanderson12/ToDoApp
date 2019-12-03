package com.example.todoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DailyListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_list);

        // Handler for AddItem button
        FloatingActionButton addItem = findViewById(R.id.AddItem);
        addItem.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            EditText activity = new EditText(this);
            builder.setView(activity);

            // Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // String input = activity.getText().toString();
                    // updateList(input); This method will update the to-do list
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        });
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, LoginToDailyReport.class);
        startActivity(intent);
    }
}
