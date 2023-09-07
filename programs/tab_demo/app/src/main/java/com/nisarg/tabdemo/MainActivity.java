package com.nisarg.tabdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager vpg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tab = findViewById(R.id.tabs);
        vpg = findViewById(R.id.viewPager);
        vpgAdapter obj = new vpgAdapter(getSupportFragmentManager());
        vpg.setAdapter(obj);
        tab.setupWithViewPager(vpg);
    }
}