package com.example.bookingpersonalizado;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Menu extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    Button SignOutButton;
    private FirebaseAuth myAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_hotel);


        myAuth = FirebaseAuth.getInstance();
        SignOutButton = findViewById(R.id.signOutButton);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy", Locale.getDefault());
        Date date = new Date();
        String fecha = dateFormat.format(date);
        Toast.makeText(this, ""+ date, Toast.LENGTH_SHORT).show();

        SignOutButton.setOnClickListener(new View.OnClickListener() {
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

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, (android.view.Menu) menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.reserva:
                return true;
            case R.id.ayuda:
                return true;
            case R.id.signOutButton:
                return true;
                default:
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }



}
