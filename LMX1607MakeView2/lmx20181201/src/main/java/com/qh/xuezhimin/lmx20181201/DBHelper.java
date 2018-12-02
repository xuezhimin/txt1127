package com.qh.xuezhimin.lmx20181201;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private final static String TABLE_NAME = "search";
    private final SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, "flow.db", null, 1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(_id integer primary key autoincrement,"
                + "names text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //添加数据
    public void insert(String names) {
        ContentValues values = new ContentValues();
        values.put("names", names);
        db.insert(TABLE_NAME, null, values);

    }

    //删除数据
    public void delete() {
        db.delete(TABLE_NAME, null, null);
    }

    //查询数据
    public List<String> query() {
        Cursor cursor = db.query(TABLE_NAME, null, null,
                null, null, null, null);
        List<String> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String names = cursor.getString(cursor.getColumnIndex("names"));
            list.add(names);
        }
        return list;
    }


}
