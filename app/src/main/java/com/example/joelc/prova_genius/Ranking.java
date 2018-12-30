package com.example.joelc.prova_genius;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Ranking extends MenuActivity {
        private ListView ranking;
        private JogoDB DB;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        DB = new JogoDB(this);
        ranking = (ListView) findViewById(R.id.ltRanking);
        DB.open();

        ArrayList<String> listaRanking =  DB.listaJogadores();


       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
               android.R.layout.simple_list_item_1, listaRanking);
       ranking.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
