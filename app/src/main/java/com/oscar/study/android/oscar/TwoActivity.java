package com.oscar.study.android.oscar;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.io.IOException;

public class TwoActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    int soundIDJava, soundIDRes;
    SoundPool soundPool;
    AssetManager assetManager;
    AssetFileDescriptor assetFileDescriptor = null;

    String nameDB = "OscarList.db";
    Intent intent;
    String key = "";
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11;
    HelperMetodClass helperMetodClass;

    DBHelper dbHelper;
    SQLiteDatabase database;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        textView8 = findViewById(R.id.textView8);
        textView9 = findViewById(R.id.textView9);
        textView10 = findViewById(R.id.textView10);
        textView11 = findViewById(R.id.textView11);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.alphaprozrachn);
        Animation animation0 = AnimationUtils.loadAnimation(this,R.anim.alphaprozrachn0);
        Animation animation1 = AnimationUtils.loadAnimation(this,R.anim.alphaprozrachn1);
        Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.alphaprozrachn2);
        Animation animation3 = AnimationUtils.loadAnimation(this,R.anim.alphaprozrachn3);

        intent = getIntent();

        key = intent.getStringExtra("id");

        Log.d(TAG, "id TwoActivity = " + key);

        textView2.setText(intent.getStringExtra("name"));

        textView1.setText(intent.getStringExtra("razdel"));

        textView3.setText(onReadDataFromCellTable(nameDB, "mytable2", "_punkt", "0", key));
        textView4.setText(onReadDataFromCellTable(nameDB, "mytable2", "_punkt", "1", key));
        textView5.setText(onReadDataFromCellTable(nameDB, "mytable2", "_punkt", "2", key));
        textView6.setText(onReadDataFromCellTable(nameDB, "mytable2", "_punkt", "3", key));
        textView7.setText(onReadDataFromCellTable(nameDB, "mytable2", "_punkt", "4", key));
        textView8.setText(onReadDataFromCellTable(nameDB, "mytable2", "_punkt", "5", key));
        textView9.setText(onReadDataFromCellTable(nameDB, "mytable2", "_punkt", "6", key));
        textView10.setText(onReadDataFromCellTable(nameDB, "mytable2", "_punkt", "7", key));
        textView11.setText(onReadDataFromCellTable(nameDB, "mytable2", "_punkt", "8", key));

        textView7.startAnimation(animation3);
        textView6.startAnimation(animation2);
        textView5.startAnimation(animation1);
        textView4.startAnimation(animation0);
        textView3.startAnimation(animation);

    }

    String onReadDataFromCellTable(String nameDB, String nameTableDB, String nameField ,String numberString, String key)  {
        Log.d(TAG, "onReadDataFromCellTable");

        database = openOrCreateDatabase(nameDB, SQLiteDatabase.CREATE_IF_NECESSARY, null);
        int version = database.getVersion();
        database.close();
        //Log.d(TAG, "version = " + version);

        dbHelper = new DBHelper(this, nameDB,null,null,null, null);
        try{database = dbHelper.getWritableDatabase();}
        catch (SQLiteException e) {database = dbHelper.getReadableDatabase();}

        String str = "";
        cursor = database.query(nameTableDB, null, "_key=="+key, null, null, null, null);
        if (cursor.moveToPosition(Integer.parseInt(numberString))){
            str = str + cursor.getString(cursor.getColumnIndex(nameField));
        }
        //Log.d(TAG, "Ячейка содержит = " + str);
        cursor.close(); dbHelper.close(); database.close();
        return str;
    }


}
