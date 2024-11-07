package com.example.sordo_15;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Rolas  extends AppCompatActivity {

    //crear herramientas
    ImageView producir_pausa;
    MediaPlayer mp;
    MediaPlayer vectormp [] = new MediaPlayer[8];
    int posicion = 0;
    int pause = 0;

    //definir la actividad como tambien levantar la interfaz

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_musiquita);

        producir_pausa = (ImageView)findViewById(R.id.producir);

        //lista de canciones
        vectormp[0] = MediaPlayer.create(this, R.raw.music1p);
        vectormp[1] = MediaPlayer.create(this, R.raw.music2p);
        vectormp[2] = MediaPlayer.create(this, R.raw.music3p);
        vectormp[3] = MediaPlayer.create(this, R.raw.music4p);
        vectormp[4] = MediaPlayer.create(this, R.raw.music5p);
        vectormp[5] = MediaPlayer.create(this, R.raw.music6p);
        vectormp[6] = MediaPlayer.create(this, R.raw.music7p);
        vectormp[7] = MediaPlayer.create(this, R.raw.music8p);

    }

    //metodo producir_pausa
    public void PlayPause(View view) {
        producir_pausa.setBackgroundResource(R.drawable.reproducir);
        if (vectormp[posicion].isPlaying()) {
            vectormp[posicion].pause();
            producir_pausa.setBackgroundResource(R.drawable.reproducir);
        } else {
            vectormp[posicion].start();
            producir_pausa.setBackgroundResource(R.drawable.pausa);
        }
    }


    public void stop(View view){
        if(vectormp[posicion] != null){
            vectormp[posicion].stop();

            vectormp[0] = MediaPlayer.create(this, R.raw.music1p);
            vectormp[1] = MediaPlayer.create(this, R.raw.music2p);
            vectormp[2] = MediaPlayer.create(this, R.raw.music3p);
            vectormp[3] = MediaPlayer.create(this, R.raw.music4p);
            vectormp[4] = MediaPlayer.create(this, R.raw.music5p);
            vectormp[5] = MediaPlayer.create(this, R.raw.music6p);
            vectormp[6] = MediaPlayer.create(this, R.raw.music7p);
            vectormp[7] = MediaPlayer.create(this, R.raw.music8p);
            posicion = 0;
            producir_pausa.setBackgroundResource(R.drawable.reproducir);
        }
    }


    //metodo para saltar a la siguiente rola
    public void elotro(View view){
        if(posicion < vectormp.length -1){

            if(vectormp[posicion].isPlaying()){
                vectormp[posicion].stop();
                posicion++;
                vectormp[posicion].start();

            }

        }else{
            Toast.makeText(this,"ya no ahi ma bro", Toast.LENGTH_SHORT).show();
        }
    }


    //metodo para regresar al otro
    public void volver(View view){
        if(posicion >=1){

            if(vectormp[posicion].isPlaying()){
                vectormp[posicion].stop();
                vectormp[0] = MediaPlayer.create(this, R.raw.music1p);
                vectormp[1] = MediaPlayer.create(this, R.raw.music2p);
                vectormp[2] = MediaPlayer.create(this, R.raw.music3p);
                vectormp[3] = MediaPlayer.create(this, R.raw.music4p);
                vectormp[4] = MediaPlayer.create(this, R.raw.music5p);
                vectormp[5] = MediaPlayer.create(this, R.raw.music6p);
                vectormp[6] = MediaPlayer.create(this, R.raw.music7p);
                vectormp[7] = MediaPlayer.create(this, R.raw.music8p);
                posicion --;

                vectormp[posicion].start();

            }

        } else {
            Toast.makeText(this, "no ai ma", Toast.LENGTH_SHORT).show();

        }

    }




    //si tocas la imagen Beatiful Nightmare(no funciona por ahora)
    public void music1(View view)
    {
        if (mp == null)
        {
            vectormp[posicion].stop();
            vectormp[0] = MediaPlayer.create(this, R.raw.music1p);
            mp.start();

        } else if (!mp.isPlaying())
        {
            mp.seekTo(pause);
            mp.start();
        }
    }



























}














