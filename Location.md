# Android Location

## Android Mainfest File

```xml
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
```
## Dependencies

```gradle
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.5.0'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
    implementation 'com.google.android.gms:play-services-location:19.0.1'
```
### activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/heading"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Control Center"
        android:layout_above="@+id/Controllayout"
        android:layout_centerHorizontal="true"
        android:textSize="50dp"
        android:textColor="@color/white"
        android:fontFamily="cursive"
        android:layout_marginBottom="20dp"/>


    <RelativeLayout
        android:id="@+id/Controllayout"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true">

    <TextView

        android:id="@+id/heading2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Location Center"
        android:layout_below="@+id/bluetoothSwitch"
        android:layout_centerHorizontal="true"
        android:textSize="50dp"
        android:textColor="@color/white"
        android:fontFamily="cursive"
        android:layout_marginBottom="20dp"
        android:layout_marginTop="30dp"/>
    <TextView
        android:id="@+id/location"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/heading2"
        android:layout_centerHorizontal="true"
        android:textSize="15dp"
        android:text="Location Here"
        android:textColor="@color/white"
        android:textAlignment="center"/>

        <Button
            android:id="@+id/locationbtn"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerHorizontal="true"
            android:layout_below="@+id/location"
            android:text="Get Location"/>
    </RelativeLayout>
</RelativeLayout>
```

## MainActivity.java

```java
package com.example.wifibluetooth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.content.*;
import android.content.pm.PackageManager;
import android.location.*;
import android.net.wifi.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.Manifest;
import com.google.android.gms.location.*;
import com.google.android.gms.tasks.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    TextView loc;
    Button btn;
    FusedLocationProviderClient flpc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loc=findViewById(R.id.location);
        btn=findViewById(R.id.locationbtn);

        //Location

        flpc=LocationServices.getFusedLocationProviderClient(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLastLocation();
            }
        });
    }

    private void getLastLocation(){
        if(ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            flpc.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location!=null){
                        Geocoder g=new Geocoder(MainActivity.this, Locale.getDefault());
                        List<Address> address=null;
                        try{
                            address=g.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                            String answer;
                            answer="\nLatitude: "+address.get(0).getLatitude()+
                                    "\nLongitude: "+address.get(0).getLongitude()+
                                    "\nAddress: "+address.get(0).getAddressLine(0)+
                                    "\nCity: "+address.get(0).getLocality()+
                                    "\nCountry: "+address.get(0).getCountryName()+
                                    "\nCountry Code: "+address.get(0).getCountryCode();
                            loc.setText(answer);
                        }catch (Exception e){

                        }
                    }
                }
            });
        }else{
            askPermission();
        }
    }

    private void askPermission(){
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==100){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }else{
                Toast.makeText(this, "Required Permission", Toast.LENGTH_SHORT).show();
            }
        }
        
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    
}
```

## Output
<p align="center" style="margin-top: 10px;">
  <img src="https://github.com/bhuvisanathra/Android_using_java/assets/68009290/4bcc3a33-9a98-483a-9ce0-317f3062e5bb" height="400" width="400" alt="Image" style="border: 2px solid white;">
</p>


