package com.example.bookingpersonalizado.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.bookingpersonalizado.R;


public class FragmentHistorial extends Fragment {


    ImageView imageView;

    public FragmentHistorial() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_historial, container, false);

        imageView = view.findViewById(R.id.fg_historia_img);



        return view;
    }

}