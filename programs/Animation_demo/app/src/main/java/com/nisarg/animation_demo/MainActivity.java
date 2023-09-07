package com.nisarg.animation_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private ImageView img;
    private Button btn;
    private RadioGroup rg;
    private Animation ani,up,down,left,right;
    boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.img);
        rg = findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = findViewById(i);
                String btxt = (String) rb.getText();
                if(btxt.equals("Slide Down")){
                    ani = AnimationUtils.loadAnimation(MainActivity.this,R.anim.slide_down);
                    flag = false;
                }else if(btxt.equals("Slide Up")){
                    ani = AnimationUtils.loadAnimation(MainActivity.this,R.anim.slide_up);
                    flag = true;
                }else if(btxt.equals("Slide Left")){
                    ani = AnimationUtils.loadAnimation(MainActivity.this,R.anim.slide_left);
                    flag = true;
                }else{
                    ani = AnimationUtils.loadAnimation(MainActivity.this,R.anim.slide_right);
                    flag = true;
                }
                img.startAnimation(ani);
                ani.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // TODO Auto-generated method stub
                        img.setAlpha(1.0f);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // TODO Auto-generated method stub
                        if(flag)
                            img.setAlpha(1.0f);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // Pass the Intent to switch to other Activity
                        if(flag)
                            img.setAlpha(0.0f);
                    }
                });
            }
        });
//        ani = AnimationUtils.loadAnimation(MainActivity.this,R.anim.slide_down);
//        img.setAlpha(0.0f);

    }
}