package com.example.joelc.prova_genius;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class MenuActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selecionado = item.getItemId();
        if(selecionado == R.id.aluno && !getIntent().toString().contains("Aluno")){
            Intent intent = new Intent(getApplicationContext(),Aluno.class);
            startActivity(intent);
        } else if(selecionado == R.id.ranking && !getIntent().toString().contains("Ranking")){
            Intent intentEnviar = new Intent(getApplicationContext(), Ranking.class);
            startActivity(intentEnviar);
        } else if(selecionado == R.id.home && !getIntent().toString().contains("MainActivity")){
            Intent intentEnviar = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intentEnviar);
        }

        return true;
    }

}
