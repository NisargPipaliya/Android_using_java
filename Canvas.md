## Canvas

#### When you need to draw something on screen, you need a canvas, but unlike java you are not suppose to use java's Canvas class, as here we are going to extend view in user defined class, and then going to use the canvas object from there.

`In the Below example i have made a class drawing which extends view, and override it's default constructor and onDraw method.`

[Link to article](https://medium.com/over-engineering/getting-started-with-drawing-on-the-android-canvas-621cf512f4c7)



## Mainactivity.java

```Java
package com.example.mycanvas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Drawing d = new Drawing(this);
        setContentView(d);
    }
}
```

---
## Drawing.java
```Java
package com.example.mycanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

public class Drawing extends View {
    Paint brush;
    Paint redBrush;
    LinearGradient lgr;
    RadialGradient rdr;
    SweepGradient sgr;
    public Drawing(Context context) {
        super(context);
        init();
    }

// Other Constructors
//    public Drawing(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public Drawing(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    public Drawing(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
    public void init(){
        brush = new Paint(Paint.ANTI_ALIAS_FLAG);

        // A -> Alpha -> 1 to 1000
        // R -> Red   -> 1 to 1000
        // G -> Green -> 1 to 1000
        // B -> Blue  -> 1 to 1000

        brush.setARGB(100,390,690,540);
        redBrush = new Paint(Paint.ANTI_ALIAS_FLAG);
        redBrush.setColor(Color.RED);
        lgr = new LinearGradient(0,0,100,100,Color.RED,Color.BLUE, Shader.TileMode.MIRROR);
        rdr = new RadialGradient(70,10,100,Color.RED,Color.GREEN, Shader.TileMode.REPEAT);
        sgr = new SweepGradient(40,100,Color.BLUE,Color.RED);
    }
    @Override
   protected void onDraw(Canvas c){
        c.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,90f,brush);

        brush.setShader(lgr);
        c.drawRect(10,90,getMeasuredWidth()-200,30,brush);

        brush.setShader(rdr);
        c.drawOval(300,200,500,300,brush);

        brush.setShader(sgr);
//        brush.setStrokeWidth(40F);
        c.drawArc(200,300,400,600,45,125,false,brush);
        
        redBrush.setTextSize(90f);
        c.drawText("Hello World",70,300,redBrush);

//        brush.setShader(lgr);
//        c.drawPaint(brush);

        c.save();
        super.onDraw(c);  /// Most imp, this method should always be at last, as it will render the changes we did in this method.
    }
}


```


## But wait, there's one question how onDraw method is called??
`When you implement the method you are given a Canvas object on which you can draw your view. This method will be called when the system decides that your view should be rendered onto the screen, i.e. when we do setContentView(new Drawing(this)) in mainactivity file`