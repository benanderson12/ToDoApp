package com.example.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME;
    private static final String COL1 = "task";
    private static final String COL2 = "completed";

    public DatabaseHelper(Context context, String setName) {
        super(context, setName, null, 2);
        TABLE_NAME = setName;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" + COL1 + " TEXT, " + COL2 + " INTEGER)";
        db.execSQL(createTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int j) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void addData(String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, task);
        contentValues.put(COL2, 0);
        db.insert(TABLE_NAME, null, contentValues);
    }
    public void completed(String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        task = "'" + task + "'";
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 + " = 1 WHERE " + COL1 + " = " + task + ";";
        db.execSQL(query);
    }
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null);
    }
    public void deleteEntry(String entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        entry = "'" + entry + "'";
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COL1 + " = " + entry + ";";
        db.execSQL(query);
    }
    public void clearCompleted() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COL2 + " = 1;";
        db.execSQL(query);
    }
}
