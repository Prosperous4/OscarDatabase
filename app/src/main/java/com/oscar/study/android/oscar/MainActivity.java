package com.oscar.study.android.oscar;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "myLogs";

    TextView textView1, textView2, textView3, textView4, textView5, textView6;

    DBHelper dbHelper;
    SQLiteDatabase database;
    Cursor cursor;
    ContentValues contentValues;
    HelperMetodClass helperMetodClass = new HelperMetodClass();
    String nameDB = "OscarList.db";
    String SQLZapros = "create table if not exists mytable (_id integer primary key autoincrement, _razdel text);";
    String SQLZapros1 = "create table if not exists mytable1 (_id integer primary key autoincrement, _punkt text, _key text);";
    String SQLZapros2 = "create table if not exists mytable2 (_id integer primary key autoincrement, _punkt text, _key text);";

    Intent intent;

    int tekString = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);textView1.setOnClickListener(this);
        textView2 = findViewById(R.id.textView2);textView2.setOnClickListener(this);
        textView3 = findViewById(R.id.textView3);textView3.setOnClickListener(this);
        textView4 = findViewById(R.id.textView4);textView4.setOnClickListener(this);
        textView5 = findViewById(R.id.textView5);textView5.setOnClickListener(this);
        textView6 = findViewById(R.id.textView6);textView6.setOnClickListener(this);

        helperMetodClass.onCreateDBAPK(this, nameDB, SQLZapros, SQLZapros1, SQLZapros2);

        textView1.setText(onReadDataFromCellTable(nameDB, "mytable", "_razdel", "0"));
        textView2.setText(onReadDataFromCellTable(nameDB, "mytable", "_razdel",  "1"));
        textView3.setText(onReadDataFromCellTable(nameDB, "mytable", "_razdel", "2"));
        textView4.setText(onReadDataFromCellTable(nameDB, "mytable", "_razdel", "3"));
        textView5.setText(onReadDataFromCellTable(nameDB, "mytable", "_razdel", "4"));
        textView6.setText(onReadDataFromCellTable(nameDB, "mytable", "_razdel", "5"));

        intent = new Intent(this, OneActivity.class);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textView1:
                intent.putExtra("tek_razdel", onReadDataFromCellTable(nameDB, "mytable", "_razdel", "0"));
                intent.putExtra("tek_id", onReadDataFromCellTable(nameDB, "mytable", "_id", "0"));
                startActivity(intent);
                break;
            case R.id.textView2:
                intent.putExtra("tek_razdel", onReadDataFromCellTable(nameDB, "mytable", "_razdel", "1"));
                intent.putExtra("tek_id", onReadDataFromCellTable(nameDB, "mytable", "_id", "1"));
                startActivity(intent);
                break;
            case R.id.textView3:
                intent.putExtra("tek_razdel", onReadDataFromCellTable(nameDB, "mytable", "_razdel", "2"));
                intent.putExtra("tek_id", onReadDataFromCellTable(nameDB, "mytable", "_id", "2"));
                startActivity(intent);
                break;
            case R.id.textView4:
                intent.putExtra("tek_razdel", onReadDataFromCellTable(nameDB, "mytable", "_razdel", "3"));
                intent.putExtra("tek_id", onReadDataFromCellTable(nameDB, "mytable", "_id", "3"));
                startActivity(intent);
                break;
            case R.id.textView5:
                intent.putExtra("tek_razdel", onReadDataFromCellTable(nameDB, "mytable", "_razdel", "4"));
                intent.putExtra("tek_id", onReadDataFromCellTable(nameDB, "mytable", "_id", "4"));
                startActivity(intent);
                break;
            case R.id.textView6:
                intent.putExtra("tek_razdel", onReadDataFromCellTable(nameDB, "mytable", "_razdel", "5"));
                intent.putExtra("tek_id", onReadDataFromCellTable(nameDB, "mytable", "_id", "5"));
                startActivity(intent);
                break;
        }

    }

    //  считать данные из ячейки в таблице

    String onReadDataFromCellTable(String nameDB, String nameTableDB, String nameField ,String numberString)  {
        Log.d(TAG, "onReadDataFromCellTable");

        database = openOrCreateDatabase(nameDB, SQLiteDatabase.CREATE_IF_NECESSARY, null);
        int version = database.getVersion();
        database.close();

        dbHelper = new DBHelper(this, nameDB, null, null, null, null);
        try{database = dbHelper.getWritableDatabase();}
        catch (SQLiteException e) {database = dbHelper.getReadableDatabase();}

        String str = "";
        cursor = database.query(nameTableDB, null, null, null, null, null, null);
        if (cursor.moveToPosition(Integer.parseInt(numberString))){
            str = str + cursor.getString(cursor.getColumnIndex(nameField));
        }
        cursor.close(); dbHelper.close(); database.close();
        return str;
    }

}
