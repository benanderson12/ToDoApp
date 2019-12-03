package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginToDailyReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_to_daily_report);

        TextView mainLabel = findViewById(R.id.mainLabel);
        mainLabel.setText("Welcome to chekc your daily report! \n " +
                "input \"CS125\" for ID and \"Illinois\" for password");
    }

    public void sendMessage(View view) {
        EditText editText1 = findViewById(R.id.textBox1);
        String id = editText1.getText().toString();
        EditText editText2 = findViewById(R.id.textBox2);
        String password = editText2.getText().toString();
        if (id.equals("CS125") && password.equals("Illinois")) {
            Intent intent = new Intent(this, DisplayRecord.class);
            startActivity(intent);
        } else {
            TextView mainLabel = findViewById(R.id.mainLabel);
            mainLabel.setText("Incorrect ID and password. \"CS125\" for ID and \"Illinois\" for password");
            return;
        }
    }
}
