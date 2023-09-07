package com.nisarg.notificationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DisplayNotification extends AppCompatActivity {

    private TextView txtv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_notification);
        txtv = findViewById(R.id.txtMes);
        handleIntent(getIntent());
    }

//    @Override
//    public void onBackPressed() {
//        moveTaskToBack(true);
//    }
    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(this,MainActivity.class);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }
    private void handleIntent(Intent in){
        if(in!=null){
            String mes = in.getStringExtra("message");
            if(mes != null){
                txtv.setText(mes);
            }
        }
    }
}