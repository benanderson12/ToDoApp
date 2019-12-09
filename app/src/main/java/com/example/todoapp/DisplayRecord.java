package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

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

        //int record = 0;

        //if (record < 5) {
            //ImageView image = (ImageView)findViewById(R.id.imageGeoff);
            //image.setVisibility(View.INVISIBLE);
        //}


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
    }
}
