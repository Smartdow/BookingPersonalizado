package com.example.bookingpersonalizado;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInApi;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;

public class Menu extends AppCompatActivity  {

    ImageView img;
    private FirebaseAuth myAuth;
    GoogleApiClient googleApiClient;

    GoogleSignInApi googleSignInApi;

    int RC_SIGN_IN = 9001;

    //SignInButton signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        img = findViewById(R.id.menuImg);
        myAuth = FirebaseAuth.getInstance();

        String fechaS = "1565586000000"; // Agosto 12 2019
        long f = Long.parseLong(fechaS);
        Date mDate = new Date(f);
        Toast.makeText(this, ""+mDate, Toast.LENGTH_SHORT).show();

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAuth.signOut();
                Intent i = new Intent(Menu.this,MainActivity.class);
                startActivity(i);
                Toast.makeText(Menu.this, "Sesion Finalizada", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

}
