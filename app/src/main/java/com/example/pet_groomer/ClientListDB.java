package com.example.pet_groomer;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ClientListDB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public ClientListDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + ClientList.Client.TABLE_NAME + " (" +
            ClientList.Client._ID + " INTEGER PRIMARY KEY," +
            ClientList.Client.COLUMN_NAME_NAME + " TEXT," +
            ClientList.Client.COLUMN_NAME_BREED + " TEXT," +
            ClientList.Client.COLUMN_NAME_WEIGHT + " INTEGER," +
            ClientList.Client.COLUMN_NAME_SI + " TEXT)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ClientList.Client.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}

