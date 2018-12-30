package com.example.joelc.prova_genius;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import static com.example.joelc.prova_genius.DatabaseHelper.COL_JOGADOR;
import static com.example.joelc.prova_genius.DatabaseHelper.TB_JOGO;

public class JogoDB {

    private SQLiteDatabase database;
    private DatabaseHelper dbhelper;


    public JogoDB(Context context){
        dbhelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException{
        database = dbhelper.getWritableDatabase();
    }

    public Long salvarJogo(String jogador) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_JOGADOR, jogador);
        Long data = db.insert(TB_JOGO, null, values);
        db.close();
        return data;
    }

private String cursorToJogador(Cursor cursor) {

    String jogador = cursor.getString(0);

    Log.i("Cursor jogador", "");
    return jogador;
}

    public ArrayList<String> listaJogadores(){
        ArrayList<String> jogadores = new ArrayList<String>();
        String query = "SELECT jogador FROM ranking";
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                String jogador = cursorToJogador(cursor);

                    Log.i("ranking","jogador" +jogador);
                jogadores.add(jogador);
            } while (cursor.moveToNext());
        }


        return jogadores;
    }
}
