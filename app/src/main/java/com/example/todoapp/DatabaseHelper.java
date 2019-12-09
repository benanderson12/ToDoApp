package com.example.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME;
    private static final String COL1 = "task";

    public DatabaseHelper(Context context, String setName) {
        super(context, setName, null, 1);
        TABLE_NAME = setName;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" + COL1 + " TEXT)";
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
        db.insert(TABLE_NAME, null, contentValues);
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
}
