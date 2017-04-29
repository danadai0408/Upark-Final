package com.example.dannadai.uparking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by kikii on 4/26/2017.
 */

public class SQLiteDBHelper2 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "info.db";
    private static final int DATABASE_VERSION = 4;

    public static final String TABLE_NAME = "post";
    public static final String COLUMN_SID =  "spotid";
    public static final String COLUMN_ADD =  "add";
    public static final String COLUMN_CONTACTNAME =  "contactname";
    public static final String COLUMN_CONTACTPHONE =  "contactphone";

    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_SID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ADD + " TEXT, "+
                    COLUMN_CONTACTNAME + " TEXT, " +
                    COLUMN_CONTACTPHONE + " TEXT " + ");";

    //modified constructor
    public SQLiteDBHelper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("SQLite", "onCreate: " + CREATE_TABLE_QUERY);
        sqLiteDatabase.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion >= newVersion) return;

        Log.d("SQLite", "onUpgrade: Version = " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}