package com.nisarg.async_audio_splash;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {

    private Button b,nxt;
    private EditText edt;
    private int time;
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = findViewById(R.id.btn);
        edt = findViewById(R.id.edttim);
        nxt = findViewById(R.id.btnNxt);
        nxt.setOnClickListener(view->{
            Intent i = new Intent(Welcome.this,Music.class);
            startActivity(i);
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = Integer.parseInt(edt.getText().toString());
                new MyTaskRunner().execute(time);
            }
        });
    }
    private class MyTaskRunner extends AsyncTask<Integer,String,String> {
        private String response;
        ProgressDialog progressDialog;

        @Override
        public void onPreExecute(){
            progressDialog =  ProgressDialog.show(Welcome.this,"Hacking...","Wait for "+time+"Seconds");

        }
        public void onProgressUpdate(String...progress){
            progressDialog.setMessage(progress[0]);
        }
        @Override
        public String doInBackground(Integer...params){
            publishProgress("Sleeping....");
            try{
//                int timeInMilliSeconds = timeInSeconds*1000;
                for(int i = 0; i < time; i++){
                    Thread.sleep(1000);
                    response = "Slept for "+(i+1)+ " of " + time +" Seconds";
                    publishProgress(response);
                }
            }catch (Exception e){
                response = e.getMessage();
                Log.v("in background",e.toString());
            }
            return response;
        }
        @Override
        public void onPostExecute(String res){
            Toast.makeText(Welcome.this,"Hacked",Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
    }
}