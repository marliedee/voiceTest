package com.example.voicedumy;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AudioDataBase extends SQLiteOpenHelper {
    private static AudioDataBase audioDataBaseInstance;
    private static final String TABLE_NAME = "AudioList";
    private static final String DATABASE_NAME = "Audio.db";
    private static final int SCHEMA_VERSION = 1;
    private static Context context;
   private AudioDataBase db;


    public AudioDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }
    public static synchronized AudioDataBase getInstance(Context context1) {
        if (audioDataBaseInstance == null) {
            audioDataBaseInstance = new AudioDataBase(context1);
        }
        return audioDataBaseInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
            db.execSQL("CREATE TABLE " + TABLE_NAME +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "filename TEXT);");
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void addAudio(Audiomemos audiomemos) {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE filename = '" +
                audiomemos.getFilename() + "';", null);

        if (cursor.getCount() == 0) {
            getWritableDatabase().execSQL("INSERT INTO " + TABLE_NAME +
                    "(filename) VALUES('" +
                    audiomemos.getFilename() + "');");
        }
        cursor.close();
    }

    public List<Audiomemos> audiomemosList() {
        List<Audiomemos> audiolist = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME + ";", null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Audiomemos audiomemo = new Audiomemos(
                            cursor.getString(cursor.getColumnIndex("filename")));
                    audiolist.add(audiomemo);
                } while (cursor.moveToNext());}
        }
        return audiolist;
    }
}