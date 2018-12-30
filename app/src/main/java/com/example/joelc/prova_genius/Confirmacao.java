package com.example.joelc.prova_genius;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.joelc.prova_genius.Jogo.pontos;

public class   Confirmacao extends MenuActivity {

    private Button btConfirma;
    private EditText etnome;
    private JogoDB DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacao);
        DB = new JogoDB(this);

        btConfirma = (Button) findViewById(R.id.btConfirma);
        etnome = (EditText) findViewById(R.id.etNome);

        btConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = getIntent();
                String jogador = etnome.getText().toString();
                String jogador1 = jogador+" " + pontos;
                DB.open();
                DB.salvarJogo(jogador1);
                Toast.makeText(Confirmacao.this, "Resultado salvo.", Toast.LENGTH_SHORT).show();
                Intent iM  = new Intent(Confirmacao.this, MainActivity.class);
                startActivity(iM);
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
