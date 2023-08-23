# Using Old Camera Method

### Basic Application for capturing image and displaying it.

- For Capturing Images Using Camera first we need to have read and write permission.
```xml  
    <uses-feature android:name="android.hardware.camera" android:required="true" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"  android:maxSdkVersion="28"/>
    <!-- We Added attribute maxSdkVersion because the permission is no longer required(after sdk version 28) as the directory is not accessible by other apps -->
```


### XML Code for app
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center_horizontal"
    tools:context=".MainActivity">

    <ImageView
        android:id="@+id/ImgV"
        android:layout_width="400dp"
        android:layout_height="400dp"
        android:src="#CF1717"
        android:scaleType="fitXY"
        android:layout_marginTop="5dp"
        />
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Open Cam"
        android:layout_marginTop="5dp"
        android:id="@+id/opnCam"
        android:layout_below="@+id/ImgV"
        android:layout_marginStart="70dp"
        />
</RelativeLayout>
```

### Java Code for App
```java
package com.example.cam_dem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.*;


public class MainActivity extends AppCompatActivity {
    private ImageView img;
    private final int  CAMERA_REQ_CODE=100;
    private Button opnCam;
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.ImgV);
        opnCam = (Button) findViewById(R.id.opnCam);
        opnCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iCam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(iCam, CAMERA_REQ_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int reqCode, int resCode, @Nullable Intent data){
        super.onActivityResult(reqCode,resCode,data);
        if(resCode == RESULT_OK){
            if(reqCode==CAMERA_REQ_CODE){
                try {
                    Object b = data.getExtras().get("data");
                    img.setImageBitmap((Bitmap) b);
                    MediaStore.Images.Media.insertImage(getContentResolver(), (Bitmap)b,"Image1","info");
                }catch(Exception e){
                    Log.v("exception","Error");
                }
            }
        }
    }
}
```



## For Selection Image From Gallery and displaying it.

### Changes in XML File
```xml
  <Button
        android:id="@+id/btnGal"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Open Gallery"
        android:layout_toRightOf="@+id/opnCam"
        android:layout_alignBaseline="@+id/opnCam"
        android:layout_marginStart="10dp"
        />
```
### Changes to be made in Java File
```java
    private final int GALLERY_REQ_CODE=101;
    Button opnGal;
    //Inside onCreate
    opnGla = (Button) findViewById(R.id.btnGal);
        opnGla.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent iGal = new Intent(Intent.ACTION_PICK);
                iGal.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGal,GALLERY_REQ_CODE);
            }
        });



// Inside onActivityResult
//after if of camera_req_code
else if (reqCode==GALLERY_REQ_CODE) img.setImageURI(data.getData());
```




## Complete Code
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center_horizontal"
    tools:context=".MainActivity">

    <ImageView
        android:id="@+id/ImgV"
        android:layout_width="400dp"
        android:layout_height="400dp"
        android:src="#CF1717"
        android:scaleType="fitXY"
        android:layout_marginTop="5dp"
        />
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Open Cam"
        android:layout_marginTop="5dp"
        android:id="@+id/opnCam"
        android:layout_below="@+id/ImgV"
        android:layout_marginStart="70dp"
        />
    <Button
        android:id="@+id/btnGal"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Open Gallery"
        android:layout_toRightOf="@+id/opnCam"
        android:layout_alignBaseline="@+id/opnCam"
        android:layout_marginStart="10dp"
        />
</RelativeLayout>
```

```Java
package com.example.cam_dem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.*;


public class MainActivity extends AppCompatActivity {
    private ImageView img;
    private final int  CAMERA_REQ_CODE=100;
    private final int GALLERY_REQ_CODE=101;
    private Button opnCam,opnGla;
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.ImgV);
        opnCam = (Button) findViewById(R.id.opnCam);
        opnCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iCam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(iCam, CAMERA_REQ_CODE);
            }
        });
        opnGla = (Button) findViewById(R.id.btnGal);
        opnGla.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent iGal = new Intent(Intent.ACTION_PICK);
                iGal.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGal,GALLERY_REQ_CODE);
            }
        });

    }
    @Override
    protected void onActivityResult(int reqCode, int resCode, @Nullable Intent data){
        super.onActivityResult(reqCode,resCode,data);
        if(resCode == RESULT_OK){
            if(reqCode==CAMERA_REQ_CODE){
                try {
                    Object b = data.getExtras().get("data");
                    img.setImageBitmap((Bitmap) b);
                    MediaStore.Images.Media.insertImage(getContentResolver(), (Bitmap)b,"Image1","info");
                }catch(Exception e){
                    Log.v("exception","Error");
                }
            } else if (reqCode==GALLERY_REQ_CODE) img.setImageURI(data.getData());
        }
    }
}
```

### Android Manifest
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">
    <uses-feature android:name="android.hardware.camera"
        android:required="true" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"  android:maxSdkVersion="28"/>
    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.Cam_dem"
        tools:targetApi="31">
        <provider
            android:name="androidx.core.content.FileProvider"
            android:authorities="com.example.android.fileprovider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/file_paths"></meta-data>
        </provider>
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
```


#### For Camera2 API [Link](https://www.freecodecamp.org/news/android-camera2-api-take-photos-and-videos/)