package com.nisarg.wifi_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button on, off;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        on = findViewById(R.id.wifiBtn);
        off = findViewById(R.id.wifiOff);
        WifiManager wm = getSystemService(WifiManager.class);
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        
        on.setOnClickListener(v->{
            wm.setWifiEnabled(true);
            connManager.setNetworkPreference(ConnectivityManager.TYPE_WIFI);
            Toast.makeText(this, "Turned On", Toast.LENGTH_SHORT).show();
        });
        off.setOnClickListener(v->{
           wm.setWifiEnabled(false);
            connManager.setNetworkPreference(ConnectivityManager.TYPE_MOBILE);
            Toast.makeText(this, "Turned Off", Toast.LENGTH_SHORT).show();
        });
    }
}