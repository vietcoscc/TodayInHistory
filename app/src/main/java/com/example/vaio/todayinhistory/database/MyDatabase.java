package com.example.vaio.todayinhistory.database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.example.vaio.todayinhistory.model.Item;

import java.util.ArrayList;

/**
 * Created by vaio on 09/03/2017.
 */

public class MyDatabase {
    public static final String ID = "id";
    public static final String TYPE = "e_type";
    public static final String INFO = "e_info";
    public static final String DATE = "e_date";
    public static final String DAY = "e_day";
    public static final String MONTH = "e_month";
    public static final String YEAR = "e_year";

    public static final String BIRTHDAY = "BIRTHDAY";
    public static final String DEATH = "DEATH";
    public static final String EVENT = "EVENT";

    public static final String DB_NAME = "todayinhistory.sqlite";
    public static final String TB_NAME = "data";
    public static final String PATH = Environment.getDataDirectory() + "/data/com.example.vaio.todayinhistory/databases/" + DB_NAME;
    private Context context;
    private SQLiteDatabase database;

    public MyDatabase(Context context) {
        this.context = context;

    }

    public void openDatabase() {
        database = Database.initDatabase((Activity) context, DB_NAME);
    }

    public void closeDatabase() {
        database.close();
    }

    public void insertData(ArrayList<Item> arrItem) {
        openDatabase();
        for (int i = 0; i < arrItem.size(); i++) {
            Item item = arrItem.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(TYPE, item.getType());
            contentValues.put(INFO, item.getType());
            contentValues.put(DATE, item.getType());
            contentValues.put(DAY, item.getType());
            contentValues.put(MONTH, item.getType());
            contentValues.put(YEAR, item.getType());
            database.insert(TB_NAME, null, contentValues);
        }
        closeDatabase();
    }

    public ArrayList<Item> getData() {
        openDatabase();
        ArrayList<Item> arrItem = new ArrayList<>();
        Cursor cursor = database.query(TB_NAME, null, null, null, null, null, null);
        int typeIndex = cursor.getColumnIndex(TYPE);
        int infoIndex = cursor.getColumnIndex(INFO);
        int dateIndex = cursor.getColumnIndex(DATE);
        int dayIndex = cursor.getColumnIndex(DAY);
        int monthIndex = cursor.getColumnIndex(MONTH);
        int yearIndex = cursor.getColumnIndex(YEAR);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String type = cursor.getString(typeIndex);
            String info = cursor.getString(infoIndex);
            String date = cursor.getString(dateIndex);
            String day = cursor.getString(dayIndex);
            String month = cursor.getString(monthIndex);
            String year = cursor.getString(yearIndex);
            Item item = new Item("", type, info, date, day, month, year);
            arrItem.add(item);
            cursor.moveToNext();
        }

        closeDatabase();
        return arrItem;
    }

}
