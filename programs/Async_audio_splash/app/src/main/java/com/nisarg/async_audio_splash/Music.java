package com.nisarg.async_audio_splash;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Music extends AppCompatActivity {
    private Button bt;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        bt = findViewById(R.id.play);
        mp = MediaPlayer.create(Music.this,R.raw.ekadantaya);
        final boolean[] flag = {true};

        bt.setOnClickListener(view -> {
            if(flag[0]){
                play();
                flag[0] = false;
            }else{
                pause();
                flag[0] = true;
            }
        });
    }
    @Override
    public void onBackPressed(){
       if(mp != null){
           pause();
       }
       super.onBackPressed();
    }

    protected void play(){
        if(mp != null){
            mp.start();
            bt.setText("Pause");
        }
    }
    protected void pause(){
        if(mp!=null){
            mp.pause();
            bt.setText("Play");
        }
    }
}