# Audio Player

```Java
package com.nisarg.av_player;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        mp = new MediaPlayer();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mp.isPlaying()){
                    pauseMusic();
                } else {
                    playMusic();
                }
            }
        });
        Button btn2 = findViewById(R.id.getMusic);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               pickMusic();
            }
        });
    }
    public void pickMusic() {
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i,1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            if (requestCode == 1) {
                Uri selectedMusicUri = data.getData();
                if (selectedMusicUri != null) {
                    String pathFromUri = getRealPathFromURI(this, selectedMusicUri);
                    try {
                        mp.setDataSource(this, Uri.parse(pathFromUri));
                        mp.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    private String getRealPathFromURI(Context context, Uri contentUri) {
        String[] projection = { MediaStore.Audio.Media.DATA };
        CursorLoader loader = new CursorLoader(context, contentUri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    private void pauseMusic(){
        if(mp!=null){
            mp.pause();
            btn.setText("Play");
        }
    }
    private  void playMusic(){
        if(mp!=null){
            mp.start();
            btn.setText("Pause");
        }
    }
}
```


## XML file
```XML
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    >

    <Button
        android:id="@+id/btn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="100dp"
        android:text="Play" />

    <Button
        android:id="@+id/getMusic"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignLeft="@+id/btn"
        android:text="Get File"
        android:layout_marginLeft="-114dp" />
</RelativeLayout>
```


### Note: first press button "Get File" and then press "Play"