package com.example.mypasswords;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mypasswords.Models.DataModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "passwords.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "myTable";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABLE_NAME + "(id integer primary key autoincrement, " +
                "title text, " +
                "email text, " +
                "username text, " +
                "password text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "drop table if exists " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    public long insertData(String title, String email, String username, String password) {
        SQLiteDatabase database = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("email", email);
        values.put("username", username);
        values.put("password", password);
        long id = database.insert(TABLE_NAME, null, values);
        return id;
    }

    public ArrayList<DataModel> getData() {
        ArrayList<DataModel> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                DataModel model = new DataModel();
                model.setId(cursor.getInt(0));
                model.setTitle(cursor.getString(1));
                model.setEmail(cursor.getString(2));
                model.setUsername(cursor.getString(3));
                model.setPassword(cursor.getString(4));
                list.add(model);
            }
        }
        return list;
    }

    public Cursor returnDataById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where id = " + id, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public long updateData(String title, String email, String username, String password,int id) {
        SQLiteDatabase database = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("email", email);
        values.put("username", username);
        values.put("password", password);
        long result = database.update(TABLE_NAME,values," id = "+id,null);
        return result;
    }
    public void deleteById(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME,"id = "+id,null);
    }
}
