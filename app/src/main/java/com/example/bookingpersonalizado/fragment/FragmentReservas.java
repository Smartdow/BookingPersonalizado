package com.example.bookingpersonalizado.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.bookingpersonalizado.R;

public class FragmentReservas extends Fragment {
    
    ImageView img;

    private ImgTouch touch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_reservas, container, false);

        img = v.findViewById(R.id.fgResImg);
        touch = (ImgTouch) v.getContext();
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (touch!=null)
                    touch.onDetalleTouch("Reservassss");
            }
        });
        
        return v;
    }

    public interface ImgTouch{
        void onDetalleTouch(String s);
    }

    public void setImgTouch(ImgTouch touche){
        touch = touche;
    }

}
