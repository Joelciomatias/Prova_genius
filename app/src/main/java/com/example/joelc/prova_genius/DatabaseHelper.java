package com.example.joelc.prova_genius;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final int DB_VERSION = 2 ;
    static final String DB_NAME = "game";
    static final String COL_ID ="id";
    static final String COL_JOGADOR = "jogador";
    static final String TB_JOGO = "ranking";
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TB_JOGO+"("+COL_ID+" INTEGER PRIMARY KEY ,"+ COL_JOGADOR+" TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
