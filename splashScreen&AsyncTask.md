# Splash Screen and Async Task

## Android Mainfest File

```xml
<!-- Splash Screen Settings-->
<activity
	android:name=".splashScreen"
	android:exported="true" >
	<intent-filter>
		<action android:name="android.intent.action.MAIN" />
		<category android:name="android.intent.category.LAUNCHER" />
	</intent-filter>
</activity>
<activity
	android:name=".MainActivity"
	android:exported="false"/>
```
# Dependencies

```gradle
dependencies {
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.5.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'pl.droidsonroids.gif:android-gif-drawable:1.2.19'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
}
```

# Splash Screen Layout

## activity_splash_Screen.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".splashScreen">

<!-- Image Logo -->
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_above="@+id/imagelogo"
        android:text="Welcome to Jethala World"
        android:layout_centerInParent="true"
        android:textSize="30dp"
        android:textStyle="bold"/>

<!-- GIF Image Setup -->
    <pl.droidsonroids.gif.GifImageView
        android:id="@+id/imagelogo"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:paddingLeft="20dp"
        android:paddingRight="20dp"
        android:src="@drawable/animation"/>
</RelativeLayout>
```

## splashscreen.java

```java
package com.example.asynctask;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.*;
import android.view.WindowManager;
import android.widget.*;

@SuppressLint("CustomSplashScreen")
public class splashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setting FullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        // Object Binding
        ImageView image=findViewById(R.id.imagelogo);
        image.setImageResource(R.drawable.animation);

        // Handler Function to Change Intent from one Activity to another activity 
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(splashScreen.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },3000);
    }
}
```
# Main Activity Layout

## activity_main.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

<RelativeLayout
    android:id="@+id/innerlayout1"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_centerHorizontal="true"
    android:layout_marginTop="40dp">

    <TextView
        android:id="@+id/text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/enter_sleep_time"
        android:textStyle="bold"
        android:textSize="20sp"
        />

    <EditText
        android:id="@+id/text1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="-5dp"
        android:layout_toEndOf="@+id/text"
        android:layout_alignBaseline="@+id/text"
        android:textAlignment="center"
        android:ems="5" />
</RelativeLayout>

    <Button
        android:id="@+id/btnSubmit"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/innerlayout1"
        android:layout_centerInParent="true"
        android:layout_marginTop="10dp"
        android:text="Submit" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:layout_marginTop="20dp"
        android:textSize="22sp"
        android:layout_below="@+id/btnSubmit"
        android:id="@+id/textView1"
        android:textAlignment="center"/>

</RelativeLayout>
```

## MainActivity.java
```java
package com.example.asynctask;
import androidx.appcompat.app.AppCompatActivity;

import android.app.*;
import android.os.*;
import android.view.View;
import android.widget.*;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText text;
    TextView et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Object Binding
        text=findViewById(R.id.text1);
        btn=(Button) findViewById(R.id.btnSubmit);

        // Button Listener
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Async Task Code
                AsyncTaskRunner runner=new AsyncTaskRunner();
                String sleepTime=text.getText().toString();
                runner.execute(sleepTime);
            }
        });
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {
        private String res;
        ProgressDialog pg;
        TextView et=findViewById(R.id.textView1);

        @Override
        protected void onPreExecute(){
            pg=ProgressDialog.show(MainActivity.this,"Progress Dialog","Wait For "+ text.getText().toString()+" seconds");
        }

        @Override
        protected void onProgressUpdate(String... text){
            et.setText(text[0]);
        }

        @Override
        protected String doInBackground(String... strings) {
            publishProgress("Sleeping..");
            try{
                int sec=Integer.parseInt(strings[0]);
                int milli=sec*1000;
                for(int i=0;i<sec;i++){
                    Thread.sleep(1000);
                    res="Slept for "+(i+1)+" of "+sec+" seconds";
                    publishProgress(res);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            return res;
        }


        @Override
        protected void onPostExecute(String result){
            pg.dismiss();
            et.setText(result);
        }
    }
}
```


# Output
<div style="text-align: center;">
    <img src="https://github.com/bhuvisanathra/Android_using_java/assets/68009290/232eebb8-96b7-423f-b601-7e37479bffcf" alt="Centered Image" style="max-width: 100%; height: auto;">
</div>





