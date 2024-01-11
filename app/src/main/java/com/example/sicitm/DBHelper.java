package com.example.sicitm;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "registration.db";

    private static final String TABLE_NAME = "users";
    private static final String COLUMN_PAPERID = "paperid";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_ABS_TRACT = "abs_trct";
    //    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_PDF_PATH = "pdf_path";

    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_PAPERID + " TEXT," +
            COLUMN_TITLE + " TEXT," +
            COLUMN_ABS_TRACT + " TEXT," +
//            COLUMN_PASSWORD + " TEXT," +
            COLUMN_PDF_PATH + " TEXT" +

            ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the table if it exists and create a new one
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


}

