package com.oscar.study.android.oscar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class OneActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private static final String TAG = "myLogs";
    Intent intent;

    DBHelper dbHelper;
    Context context;
    SQLiteDatabase database;

    TextView textView;

    HelperMetodClass helperMetodClass;

    private static final int CM_DELETE_ID = 1;
    ListView listView;
    DB db;
    SimpleCursorAdapter simpleCursorAdapter;
    Cursor cursor;

    String key = "";
    String razdel = "";

    String nameDB = "OscarList.db";
    String SQLZapros = "create table if not exists mytable (_id integer primary key autoincrement, _razdel text);";
    String SQLZapros1 = "create table if not exists mytable1 (_id integer primary key autoincrement, _punkt text, _key text);";
    String SQLZapros2 = "create table if not exists mytable2 (_id integer primary key autoincrement, _punkt text, _key text);";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        textView = findViewById(R.id.textView);

        // открываем подключение к БД
        db = new DB(this);
        db.open();

        intent = getIntent();

        key = intent.getStringExtra("tek_id");

        razdel = intent.getStringExtra("tek_razdel");

        //Log.d(TAG, "tek_razdel = " + intent.getStringExtra("tek_razdel"));
        //Log.d(TAG, "tek_id = " + intent.getStringExtra("tek_id"));

        textView.setText(intent.getStringExtra("tek_razdel"));

        // получаем курсор
        cursor = db.getAllData(key);
        startManagingCursor(cursor);

        // формируем столбцы сопоставления
        String[] from = new String[] { DB.COLUMN_TXT };
        int[] to = new int[] { R.id.textView };

        // создааем адаптер и настраиваем список
        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.my_list, cursor, from, to);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(simpleCursorAdapter);
        listView.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Log.d(TAG, "position " + position);
        Log.d(TAG, "id " + id);

        intent = new Intent(OneActivity.this, TwoActivity.class);

        intent.putExtra("id", id + "");

        TextView text = (TextView) view.findViewById(R.id.textView);

        String name = text.getText().toString();

        intent.putExtra("name", name + "");

        intent.putExtra("razdel", razdel + "");

        startActivity(intent);

    }
}
