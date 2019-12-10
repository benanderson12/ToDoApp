package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayRecord extends AppCompatActivity {
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_record);
        String table = getIntent().getStringExtra("tableName");
        databaseHelper = new DatabaseHelper(this, table);
        updateList();

        //Button for clear the listView
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            clearListView();
        });
    }

    public void updateList() {
        Cursor data = databaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            if (data.getInt(1) == 1) {
                listData.add(data.getString(0));
            }
        }
        ListView activities = findViewById(R.id.ToDoList);
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        activities.setAdapter(adapter);

        //Pop up picture and comment condition
        TextView label = findViewById(R.id.label);
        int count = activities.getAdapter().getCount();
        if (count >= 5) {
            Toast.makeText(getApplicationContext(), "Great! You made more than " +
                    count + " comments!", Toast.LENGTH_LONG).show();
            label.setText("Great! Keep making CS diary! Geoff is with you!");
        }
        if (count < 5) {
            Toast.makeText(getApplicationContext(), "Keep getting more comments! ", Toast.LENGTH_LONG).show();
        }
        if (count < 5) {
            ImageView image = findViewById(R.id.imageGeoff);
            image.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Clear the ListView.
     * @param listView current listView
     */
    public void clearListView() {
        databaseHelper.clearCompleted();
        updateList();
    }
}
