package com.example.sordo_15;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jetbrains.annotations.Nullable;


public class DataHelper extends SQLiteOpenHelper{
    public  DataHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE cliente(numero INTEGER PRIMARY KEY, nombre TEXT, cel TEXT, rango TEXT, nota INTERGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS alumno");
        db.execSQL("CREATE TABLE cliente(numero INTEGER PRIMARY KEY, nombre TEXT, cel TEXT, rango TEXT, nota INTERGER)");
    }
}
