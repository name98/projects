package com.flowerworld.connections;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.flowerworld.items.UserItem;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "auth";
    public static final String TABLE_NAME = "user";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String TELEPHONE = "telephone";
    public static final String KEY = "id";
    public static final String USER_NAME = "user_name";


    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(" +
                KEY + " integer primary key," +
                EMAIL + " text," +
                PASSWORD + " text," +
                TELEPHONE + " text," +
                USER_NAME + " text" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public void add(UserItem user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMAIL, user.getUserEmail());
        contentValues.put(PASSWORD, user.getUserPassword());
        contentValues.put(KEY, user.getUserId());
        contentValues.put(TELEPHONE, user.getTelephone());
        contentValues.put(USER_NAME, user.getUserName());
        db.insert(TABLE_NAME, null, contentValues);
    }

    public String getLogin() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("Select " + EMAIL + " from " + TABLE_NAME + " where " + KEY + "=1;", null);
        String str = "";
        if (cr.moveToFirst()) {
            do {
                str += cr.getString(0);
            } while (cr.moveToNext());
        }
        return str;
    }

    public String getId() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("Select " + KEY + " from " + TABLE_NAME + ";", null);
        String str = "";
        if (cr.moveToFirst()) {
            do {
                str += cr.getString(0);
            } while (cr.moveToNext());
        }
        return str;
    }

    public String get(String column) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("Select " + column + " FROM " + TABLE_NAME + ";", null);
        String str = "";
        if (cr.moveToFirst()) {
            do {
                str += cr.getString(0);
            } while (cr.moveToNext());
        }
        return str;
    }

    public void remove() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " ;");
    }

    public boolean isEmpty() {
        SQLiteDatabase db = getWritableDatabase();
        String count = "SELECT count(*) FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(count, null);
        cursor.moveToFirst();
        int bool = cursor.getInt(0);
        return bool < 1;
    }


}
