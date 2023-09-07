package com.nisarg.frag_demo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Bfrag extends Fragment {


    public Bfrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View v =  inflater.inflate(R.layout.fragment_bfrag, container, false);
     TextView txtv = v.findViewById(R.id.bfrag1);
     return v;
    }
}