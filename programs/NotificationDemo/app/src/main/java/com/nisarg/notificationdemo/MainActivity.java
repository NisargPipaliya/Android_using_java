package com.nisarg.notificationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.*;
import android.widget.*;
import android.view.*;

public class MainActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID=1;
    private static final String CHANNEL_ID="my_channel";
    private static boolean flag = false;
    private Button btn;
    private EditText etdtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btnSend);
        etdtxt = findViewById(R.id.edtmes);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = etdtxt.getText().toString();
                sendNotification(message);
            }
        });
    }
    private void sendNotification(String mes){
        if(!flag){
            createNotificationChannel();
        }
        Intent in = new Intent(this,DisplayNotification.class);
        in.putExtra("message",mes);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // PendingIntent Requires one of the Following flag:
//       PendingIntent.FLAG_IMMUTABLE or FLAG_MUTABLE
        PendingIntent pin = PendingIntent.getActivity(this,0,in,PendingIntent.FLAG_MUTABLE|PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.mipmap.modi)
                .setContentTitle("Heyyyy there Open this")
                .setContentText(mes)
                .setContentIntent(pin)
                .setAutoCancel(true);
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);//NOTIFICATION_SERVICE
        nm.notify(NOTIFICATION_ID,builder.build());
    }
    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            flag = true;
            CharSequence channelName = "demo_notify";
            String channelDescription = "This is a dummy Channel";
            NotificationChannel nc = new NotificationChannel(CHANNEL_ID,channelName,NotificationManager.IMPORTANCE_DEFAULT);
            nc.enableLights(true);
            nc.enableVibration(true);
            nc.setDescription(channelDescription);

            NotificationManager nm = (NotificationManager) getSystemService(NotificationManager.class);//NOTIFICATION_SERVICE
            nm.createNotificationChannel(nc);

        }
    }
}