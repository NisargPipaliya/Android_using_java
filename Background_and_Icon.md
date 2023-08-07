# Setting a gradient Color as background 
- Create a xml file inside res>drawable. name it accordingly and then in your application you can set background as 
`android:background = "@drawable/your_file_name`
```xml
<?xml version="1.0" encoding="utf-8"?>
<shape
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval">
    <gradient
        android:startColor="#678390"
        android:endColor="#7d8abc"
        android:centerColor="#97A4EA"
        android:angle="60"/>
</shape>
```
