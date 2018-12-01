package com.example.joelc.prova_genius;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Jogo extends MenuActivity {

    public Button bt1,bt2,bt3,bt4;
    public SoundPool spaudio;
    public int beep1,beep2,beep3,beep4,error,victory,win;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);


        //soundpoll
        spaudio = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        //carregando os audios
        beep1 = spaudio.load(Jogo.this,R.raw.beep1,1);
        beep2 = spaudio.load(Jogo.this,R.raw.beep5,1);
        beep3 = spaudio.load(Jogo.this,R.raw.beep3,1);
        beep4 = spaudio.load(Jogo.this,R.raw.beep4,1);
        win = spaudio.load(Jogo.this,R.raw.win,1);
        victory = spaudio.load(Jogo.this,R.raw.victory,1);
        error = spaudio.load(Jogo.this,R.raw.error,1);


        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spaudio.play(beep1,1,1,1,0,1);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spaudio.play(beep2,1,1,1,0,1);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spaudio.play(beep3,1,1,1,0,1);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spaudio.play(beep4,1,1,1,0,1);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
