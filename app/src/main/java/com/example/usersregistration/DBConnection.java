package com.example.usersregistration;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBConnection extends SQLiteOpenHelper {

    private static final String name = "database";
    private static final int version = 1;

    public DBConnection(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user (_id integer primary key autoincrement,"+"nome varchar(255),usuario varchar(255)," +
                "senha varchar(255),endereco varchar(255),email varchar(255),datanasc varchar(255),sexo varchar(255)," +
                "tipo varchar(255),idNum varchar(255))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
