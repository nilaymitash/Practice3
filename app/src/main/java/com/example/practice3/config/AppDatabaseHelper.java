package com.example.practice3.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.practice3.dao.ProductMaintenanceDao;

public class AppDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "practice_three_db";
    private static final int DB_VERSION = 1;

    public AppDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //TODO: create user table
        //create product table
        sqLiteDatabase.execSQL(ProductMaintenanceDao.getProductTableCreateQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion < 2){
            /** Update the database to version 2.
             *  You may need to create new colums or add new tables to the database.
             */
        }
    }
}
