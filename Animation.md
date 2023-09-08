# Basics of Animation
## Create a folder named anim
### Zoom In 
##### XML
```xml
<set xmlns:android="http://schemas.android.com/apk/res/android">
<scale
    android:duration="1000"
    android:fromXScale="2"
    android:fromYScale="2"
    android:PivotX="50%"
    android:PivotY="50%"
    android:toXScale="4"
    android:toYScale="4"
/>
</set>
```

### Zoom Out
#### XML
```xml
<set xmlns:android="http://schemas.android.com/apk/res/android">
<scale
    android:duration="1000"
    android:fromXScale="2.0"
    android:fromYScale="2.0"
    android:toXScale="0.2"
    android:toYScale="0.2"
    android:PivotX="50%"
    android:PivotY="50%"
/>
</set>
```

### Java for above animation
```Java
image = (ImageView) findViewById(R.id.image_id);
Animation ani = AnimationUtils.LoadAnimation(getApplicationContext(),R.anim.file_name);
image.startAnimation(ani);
```
