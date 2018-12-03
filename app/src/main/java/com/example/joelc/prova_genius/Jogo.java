package com.example.joelc.prova_genius;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Jogo extends MenuActivity {

    public TextView tvFaseAtual,tvPontos,tvVidas;
    public Button bt1,bt2,bt3,bt4;
    public SoundPool spaudio;
    public Animation animation;
    public static int index=0;
    public int vidas;
    public int etapaJogo = 1;
    public int pontos = 0;

    public int jogoAtual[] = {0,0,0,0,0,0,0,0};
    public boolean jogando = false;

    public int beep1,beep2,beep3,beep4,error,victory,win;

    public int fase1[] = {1,2,3,4,1,2,3,4};
    public int fase2[] = {1,3,1,3,1,3,1,3};
    public int fase3[] = {4,1,1,2,3,3,3,1};
    public int fase4[] = {1,2,3,4,1,2,3,4};
    public int fase5[] = {4,3,2,1,1,2,3,4};

    public int fase1Passos = 0;


    public boolean fase1On = false;
    public boolean fase2On = false;
    public boolean fase3On = false;
    public boolean fase4On = false;
    public boolean fase5On = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        vidas = 3;

        //soundpoll
        spaudio = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        //carregando os audios
        beep1 = spaudio.load(Jogo.this,R.raw.beep1,1);
        beep2 = spaudio.load(Jogo.this,R.raw.beep5,1);
        beep3 = spaudio.load(Jogo.this,R.raw.beep3,1);
        beep4 = spaudio.load(Jogo.this,R.raw.beep2,1);
        win = spaudio.load(Jogo.this,R.raw.win,1);
        victory = spaudio.load(Jogo.this,R.raw.victory,1);
        error = spaudio.load(Jogo.this,R.raw.error,1);


        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        tvFaseAtual = findViewById(R.id.tvFaseAtual);
        tvPontos = findViewById(R.id.tvPontos);
        tvVidas = findViewById(R.id.tvVida);


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                piscarBtn(bt1);
                spaudio.play(beep1,1,1,1,0,1);
                jogoAtual(1);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                piscarBtn(bt2);
                spaudio.play(beep2,1,1,1,0,1);
                jogoAtual(2);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                piscarBtn(bt3);
                spaudio.play(beep3,1,1,1,0,1);
                jogoAtual(3);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                piscarBtn(bt4);
                spaudio.play(beep4,1,1,1,0,1);
                jogoAtual(4);
            }
        });

        startButtonHandler();

    }


    public void startGenius(View v){
        fase1Passos = 0;
        etapaJogo = 1 ;
        vidas = 3;
        pontos = 200;
        playFase1();
        atualizarTela();

    }

    public void atualizarTela(){
        tvFaseAtual.setText(Integer.toString(etapaJogo));
        tvVidas.setText(Integer.toString(vidas));
        tvPontos.setText(Integer.toString(pontos));
    }

    public void jogoAtual(int botao){

        if(fase1On){
            if(fase1Passos < 8 && fase1[fase1Passos] == botao ){
                Toast.makeText(getApplicationContext(),"faseiPassosIndex: "+fase1Passos+"  fase1conteudo: "+fase1[fase1Passos]+"\n" + " botaoApertado: "+botao,Toast.LENGTH_SHORT).show();
                fase1Passos++;
                if(fase1Passos > 7){
                    spaudio.play(win,1,1,1,0,1);
                    etapaJogo = 2;
                    pontos+=100;
                    caixaDialogo("Parabéns, indo para a fase: "+etapaJogo);
                }
            } else {
                spaudio.play(error,1,1,1,0,1);
                fase1Passos = 0;
                pontos-=30;
                vidas--;
                etapaJogo = 1;
                atualizarTela();
                playFase1();
                if (vidas<0){
                    vidas = 3;
                    playFase1();
                }
            }
            atualizarTela();
        }
        if(fase2On){
            if(fase1Passos < 8 && fase2[fase1Passos] == botao ){
                Toast.makeText(getApplicationContext(),"faseiPassosIndex: "+fase1Passos+"  fase1conteudo: "+fase1[fase1Passos]+"\n" + " botaoApertado: "+botao,Toast.LENGTH_SHORT).show();
                fase1Passos++;
                if(fase1Passos > 7){
                    spaudio.play(win,1,1,1,0,1);
                    etapaJogo = 3;
                    pontos+=100;
                    caixaDialogo("Parabéns, indo para a fase: "+etapaJogo);
                }
            } else {
                spaudio.play(error,1,1,1,0,1);
                fase1Passos = 0;
                pontos-=30;
                vidas--;
                atualizarTela();
                playFase2();
                if (vidas<0){
                    vidas = 3 ;
                    playFase1();
                }
            }
        }
        if(fase3On){
            if(fase1Passos < 8 && fase3[fase1Passos] == botao ){
                Toast.makeText(getApplicationContext(),"faseiPassosIndex: "+fase1Passos+"  fase1conteudo: "+fase1[fase1Passos]+"\n" + " botaoApertado: "+botao,Toast.LENGTH_SHORT).show();
                fase1Passos++;
                if(fase1Passos > 7){
                    spaudio.play(win,1,1,1,0,1);
                    etapaJogo = 4;
                    pontos+=100;
                    caixaDialogo("Parabéns, indo para a fase: "+etapaJogo);
                }
            } else {
                spaudio.play(error,1,1,1,0,1);
                fase1Passos = 0;
                pontos-=30;
                vidas--;
                atualizarTela();
                playFase3();
                if (vidas<0){
                    vidas = 3 ;
                    playFase1();
                }
            }
        }
        if(fase4On){
            if(fase1Passos < 8 && fase4[fase1Passos] == botao ){
                Toast.makeText(getApplicationContext(),"faseiPassosIndex: "+fase1Passos+"  fase1conteudo: "+fase1[fase1Passos]+"\n" + " botaoApertado: "+botao,Toast.LENGTH_SHORT).show();
                fase1Passos++;
                if(fase1Passos > 7){
                    spaudio.play(win,1,1,1,0,1);
                    etapaJogo = 5;
                    pontos+=100;
                    caixaDialogo("Parabéns, indo para a fase: "+etapaJogo);
                }
            } else {
                spaudio.play(error,1,1,1,0,1);
                fase1Passos = 0;
                pontos-=30;
                vidas--;
                atualizarTela();
                playFase4();
                if (vidas<0){
                    vidas = 3 ;
                    playFase1();
                }
            }
        }
        if(fase5On){
            if(fase1Passos < 8 && fase1[fase1Passos] == botao ){
                Toast.makeText(getApplicationContext(),"faseiPassosIndex: "+fase1Passos+"  fase1conteudo: "+fase1[fase1Passos]+"\n" + " botaoApertado: "+botao,Toast.LENGTH_SHORT).show();
                fase1Passos++;
                if(fase1Passos > 7){
                    spaudio.play(victory,1,1,1,0,1);
                    pontos+=100;
                    etapaJogo = 1;
                    caixaDialogo("Parabéns, Voce venceu o jogo: ");
                }
            } else {
                spaudio.play(error,1,1,1,0,1);
                fase1Passos = 0;
                pontos-=30;
                vidas--;
                atualizarTela();
                playFase5();
                if (vidas<0){
                    vidas = 3 ;
                    playFase1();
                }
            }
        }


    }

    public void caixaDialogo(String text){
        AlertDialog alertDialog = new AlertDialog.Builder(Jogo.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(text);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        atualizarTela();
                        switch (etapaJogo){
                            case 1:playFase1();
                            case 2:playFase2();
                            case 3:playFase3();
                            case 4:playFase4();
                            case 5:playFase5();
                                //chamar a  tela para preenche o nome
                        }
                    }
                });
        alertDialog.show();
    }


    public void playFase1(){
        fase1Passos = 0;
        etapaJogo = 1 ;
        vidas = 3;
        pontos = 200;
        fase1On = true;
        index = 0;
        fase2On =false;
        fase3On =false;
        fase4On =false;
        fase5On =false;
        atualizarTela();
    }
    public void playFase2(){
        fase1Passos = 0;
        fase2On = true;
        index = 0;
        fase1On =false;
        fase3On =false;
        fase4On =false;
        fase5On =false;
    }
    public void playFase3(){
        fase1Passos = 0;
        fase3On = true;
        index = 0;
        fase1On =false;
        fase2On =false;
        fase4On =false;
        fase5On =false;
    }
    public void playFase4(){
        fase1Passos = 0;
        fase4On = true;
        index = 0;
        fase2On =false;
        fase3On =false;
        fase1On =false;
        fase5On =false;
    }
    public void playFase5(){
        fase1Passos = 0;
        fase5On = true;
        index = 0;
        fase2On =false;
        fase3On =false;
        fase4On =false;
        fase1On =false;
    }

    public void startButtonHandler(){

        index = 0;
        new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){

                int localIndex=0;

                if(index < 8 && fase1On){
                    localIndex = fase1[index];
                }
                if(index < 8 && fase2On){
                    localIndex = fase2[index];
                }

                if(index < 8 && fase3On){
                    localIndex = fase3[index];
                }

                if(index < 8 && fase4On){
                    localIndex = fase4[index];
                }
                if(index < 8 && fase5On){
                    localIndex = fase5[index];
                }


                if(localIndex == 1 )
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            piscarBtn(bt1);
                            spaudio.play(beep1,1,1,1,0,1);
                        }
                    });
               else if(localIndex == 2 )
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            piscarBtn(bt2);
                            spaudio.play(beep2,1,1,1,0,1);
                        }
                    });
                else if(localIndex == 3 )
                     runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            piscarBtn(bt3);
                            spaudio.play(beep3,1,1,1,0,1);
                        }
                    });
                else if(localIndex ==4){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            piscarBtn(bt4);
                            spaudio.play(beep4,1,1,1,0,1);
                        }
                    });

                } else {
                    Log.i("tag", "A Kiss every 5 seconds+"+index);
                }

                index++;
            }
        },5,600);

    }

    public void piscarBtn(Button btn) {
        animation = new AlphaAnimation(1, 0); // Altera alpha de visível a invisível
        animation.setDuration(400); // duração - meio segundo
        animation.setInterpolator(new LinearInterpolator());
        //animation.setRepeatCount(Animation.); // Repetir infinitamente
        animation.setRepeatMode(Animation.REVERSE); //Inverte a animação no final para que o botão vá desaparecendo
        btn.startAnimation(animation);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
