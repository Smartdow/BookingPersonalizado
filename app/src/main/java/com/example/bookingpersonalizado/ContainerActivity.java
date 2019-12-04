package com.example.bookingpersonalizado;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.bookingpersonalizado.fragment.FragmentHistorial;
import com.example.bookingpersonalizado.fragment.FragmentPerfil;
import com.example.bookingpersonalizado.fragment.FragmentReservas;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.bookingpersonalizado.fragment.FragmentHistorial;
import com.example.bookingpersonalizado.fragment.FragmentPerfil;
import com.example.bookingpersonalizado.fragment.FragmentReservas;

public class ContainerActivity extends AppCompatActivity implements FragmentReservas.ImgTouch {

    BottomNavigationView bottomNav;

    FrameLayout frameLayout;

    FragmentReservas fragmentReservas;

    String msj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        fragmentReservas = new FragmentReservas();
        fragmentReservas.setImgTouch(this);

        bottomNav = findViewById(R.id.container_nav);
        frameLayout = findViewById(R.id.container_frame);

        final FragmentPerfil fragmentPerfil = new FragmentPerfil();
        final FragmentHistorial fragmentHistorial = new FragmentHistorial();
        final FragmentReservas fragmentReservas = new FragmentReservas();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_frame,fragmentReservas)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_historial:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container_frame,fragmentHistorial)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .commit();
                        menuItem.setChecked(true);
                        break;
                    case R.id.menu_perfil:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container_frame,fragmentPerfil)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .commit();
                        menuItem.setChecked(true);
                        break;
                    case R.id.menu_reservas:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container_frame,fragmentReservas)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .commit();
                        menuItem.setChecked(true);
                        break;
                }
                return false;
            }
        });

        frameLayout.setOnTouchListener(new OnSwipeTouch(ContainerActivity.this){

            @Override
            public void onSwipeRight() {
                Toast.makeText(ContainerActivity.this, "Der", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeBottom() {
                super.onSwipeBottom();
                Toast.makeText(ContainerActivity.this, "Abajo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDetalleTouch(String s) {
        msj = s;
        if (s!=null){
            Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show();
        }
    }
}
