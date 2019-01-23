package com.oscar.study.android.oscar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;

class HelperMetodClass extends AppCompatActivity {

    private static final String TAG = "myLogs";
    DBHelper dbHelper;
    SQLiteDatabase database;
    Cursor cursor;
    ContentValues contentValues;

    void onCreateDBAPK(Context context, String nameDB, String SQLZapros, String SQLZapros1, String SQLZapros2) {
        Log.d(TAG,"onCreateAPK");

        dbHelper = new DBHelper(context, nameDB, null, null, null, null);
        try{dbHelper.createDataBase();} catch (IOException e) {
            throw new Error("Unable to create database");
        }
        try {
            dbHelper.openDataBase();
        } catch (SQLException sqle){ throw sqle;}
        dbHelper.close();

    }

}



