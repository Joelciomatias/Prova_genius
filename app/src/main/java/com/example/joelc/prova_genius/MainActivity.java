package com.example.joelc.prova_genius;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends MenuActivity {


    private Button jogar,sobre,rank;
    private JogoDB DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            DB = new JogoDB(this);
            DB.open();
           DB.listaJogadores();
            Log.i("Main", "instanciou DB");
        }catch (Exception e){
            e.printStackTrace();
        }


        jogar = findViewById(R.id.btjogar);
        sobre = findViewById(R.id.btsobre);
        rank = findViewById(R.id.btrank);

        jogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Jogo.class);
                startActivity(intent);
            }
        });
        sobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Aluno.class);
                startActivity(intent);
            }
        });
        rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Ranking.class);
                startActivity(intent);
            }
        });



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
