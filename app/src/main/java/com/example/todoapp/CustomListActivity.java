package com.example.todoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CustomListActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_list);
        databaseHelper = new DatabaseHelper(this, "customList");
        updateList();

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
                    String input = activity.getText().toString();
                    databaseHelper.addData(input);
                    updateList();
                }
            });
            // Cancel button
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        });

        // Handler for ListView items
        ListView todoList = findViewById(R.id.ToDoList);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        todoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                builder.setPositiveButton("Completed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String entry = todoList.getItemAtPosition(position).toString();
                        databaseHelper.completed(entry);
                        updateList();
                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String entry = todoList.getItemAtPosition(position).toString();
                        databaseHelper.deleteEntry(entry);
                        updateList();
                    }
                });

                builder.show();
            }
        });
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayRecord.class);
        intent.putExtra("tableName", "customList");
        startActivity(intent);
    }

    /**
     * Updates the ToDo List
     */
    public void updateList() {
        Cursor data = databaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            System.out.println(data.getInt(1));
            if (data.getInt(1) == 0) {
                listData.add(data.getString(0));
            }
        }
        ListView activities = findViewById(R.id.ToDoList);
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        activities.setAdapter(adapter);
    }
}