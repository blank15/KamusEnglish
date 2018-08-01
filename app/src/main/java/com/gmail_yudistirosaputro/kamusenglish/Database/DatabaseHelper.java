package com.gmail_yudistirosaputro.kamusenglish.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "dbkamus";
    private static final int DATABASE_VERSION = 1;


    private static final String SQL_CREATE_TABLE_ENG = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_ENG,
            DatabaseContract.KamusColum._ID,
            DatabaseContract.KamusColum.KATA,
            DatabaseContract.KamusColum.KETERANGAN
    );

    private static final String SQL_CREATE_TABLE_ID = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_ID,
            DatabaseContract.KamusColum._ID,
            DatabaseContract.KamusColum.KATA,
            DatabaseContract.KamusColum.KETERANGAN
    );


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_ENG);
        db.execSQL(SQL_CREATE_TABLE_ID);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_ENG);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_ID);
        onCreate(db);
    }
}
