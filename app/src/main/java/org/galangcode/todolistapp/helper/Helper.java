package org.galangcode.todolistapp.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Helper extends SQLiteOpenHelper {
    private static  final int DATABASE_VERSION = 2;
    static final String DATABASE_NAME = "TodoListApp";

    public Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // This will run only once
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE = "CREATE TABLE list (id INTEGER PRIMARY KEY autoincrement, title TEXT NOT NULL, description VARCHAR NOT NULL, date TEXT NOT NULL)";
        db.execSQL(SQL_CREATE_TABLE);
    }

    // This will run when the DATABASE_VERSION changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS list");
        onCreate(db);
    }

    public ArrayList<HashMap<String, String>> getAll() {
        ArrayList<HashMap<String, String>> data = new ArrayList<>();
        String QUERY = "Select * from list";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("title", cursor.getString(1));
                map.put("description", cursor.getString(2));
                map.put("date", cursor.getString(3));
                data.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }

    public void addList(String title, String description, String date) {
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "INSERT INTO list (title, description) VALUES('"+title+"', '"+description+"', '"+date+"')";
        database.execSQL(QUERY);
    }

    public void updateList(int id,String title, String description, String date) {
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "UPDATE list set title ='"+title+"', description ='"+description+"', date = '"+date+"', WHERE id ="+id;
        database.execSQL(QUERY);
    }

    public void removeList(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "DELETE from list WHERE id ="+id;
        database.execSQL(QUERY);
    }
}
