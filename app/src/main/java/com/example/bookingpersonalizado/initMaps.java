package com.example.bookingpersonalizado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class initMaps extends AppCompatActivity {
    //Hacemos referencia a los componentes graficos del layout activity_main.xml
    EditText etLatitudDes, etLongitudDes;
    Button btnOtenerCoordenadas;
    RecyclerView recyclerViewHotel;

    //Generamos la lista de tipo Lugar
    ArrayList<lugar> listLugares;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_maps);


        //Inicializamos los componentes graficos del layout activity_main.xml
        etLatitudDes = findViewById(R.id.et_latitudDes);
        etLongitudDes = findViewById(R.id.et_longitudDes);
        btnOtenerCoordenadas = findViewById(R.id.btn_obtenerCoordenadas);

        //Llamamos al metodo objetoRealm() para crear los objetos de tipo Lugar
        objetoRealm();


        //Asignamos el onClickListener al btnOtenerCoordenadas para enviar la accion como tambien la latitud y longitud
        //a la actividad o clase de GoogleMaps
        btnOtenerCoordenadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latituDes = etLatitudDes.getText().toString();
                String longitudDes = etLongitudDes.getText().toString();
                if (!latituDes.isEmpty() & !longitudDes.isEmpty()) {


                    Intent intent = new Intent(initMaps.this, GoogleMaps.class);
                    Bundle b = new Bundle();
                    b.putString("accionUser", "clickBtnBuscar");
                    b.putString("latitudDes", latituDes);
                    b.putString("longitudDes", longitudDes);
                    intent.putExtras(b);
                    startActivity(intent);
                } else {
                    Toast.makeText(initMaps.this, "Error - campos vacios.", Toast.LENGTH_SHORT).show();
                }

            }
        });


        listLugares = new ArrayList<>();
        recyclerViewHotel = findViewById(R.id.amRvHoteles);
        recyclerViewHotel.setLayoutManager(new LinearLayoutManager(this));

        //Llamamos al metodo llenarLugares() ya que es el encargado de alimentar nuestra lista lugares
        llenarLugares();

        //Creamos una instancia de la clase AdapterHoteles y le pasamos la lista
        AdapterHoteles adapter = new AdapterHoteles(listLugares);


        //Despues de haber creado el metodo setOnClickListener en la clase AdapterHoteles ya la podemos
        //asignarle el evento onClickListener
        //Al ocurrir el evento se envia el id del lugar seleccionado a la actividad o clase de GoogleMaps
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(initMaps.this, GoogleMaps.class);
                Bundle b = new Bundle();
                b.putString("accionUser", "clickItemRecyclerViewHotel");
                b.putInt("idLugar", listLugares.get(recyclerViewHotel.getChildAdapterPosition(view)).getLugId());
                intent.putExtras(b);
                startActivity(intent);
            }
        });


        //Asigamos el adapter al recycleView
        recyclerViewHotel.setAdapter(adapter);
    }



    //Validamos si hay registros, si no hay registros Creamos los objetos de tipo Lugar esto ya que cada
    // vez que se ejecutaba la Aplicacion duplicaba los registros.
    public void objetoRealm() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<lugar> results = realm.where(lugar.class).findAll();

        if(results.size()==0){
            realm.executeTransaction(new Realm.Transaction() {
                @Override

                public void execute(Realm realm) {
                    lugar Lugar = realm.createObject(lugar.class);
                    Lugar.setLugId(1);
                    Lugar.setLugNombre("Camino Real");
                    Lugar.setLugDireccion("Popayan");
                    Lugar.setLugTelefono("018000555");
                    Lugar.setLugCorreo("caminoreal@gmail.com");
                    Lugar.setLugLatitud("2.451906");
                    Lugar.setLugLongitud("-76.607501");
                    Lugar.setLugDescripcion("Hotel Camino Real Centro Popayan");
                    Lugar.setTipoLugar("Hotel");
                    Lugar.setImagenHotel(R.drawable.balloon);


                    lugar Lugar2 = realm.createObject(lugar.class);
                    Lugar2.setLugId(2);
                    Lugar2.setLugNombre("San Martin");
                    Lugar2.setLugDireccion("Popayan");
                    Lugar2.setLugTelefono("018000555");
                    Lugar2.setLugCorreo("sm@gmail.com");
                    Lugar2.setLugLatitud("2.455283");
                    Lugar2.setLugLongitud("-76.597127");
                    Lugar2.setLugDescripcion("Hotel San Martin");
                    Lugar2.setTipoLugar("Hotel");
                    Lugar2.setImagenHotel(R.drawable.beach);

                    lugar Lugar3 = realm.createObject(lugar.class);
                    Lugar3.setLugId(3);
                    Lugar3.setLugNombre("Monasterio");
                    Lugar3.setLugDireccion("Popayan");
                    Lugar3.setLugTelefono("018000555");
                    Lugar3.setLugCorreo("m@gmail.com");
                    Lugar3.setLugLatitud("2.443449");
                    Lugar3.setLugLongitud("-76.609363");
                    Lugar3.setLugDescripcion("Hotel Monasterio");
                    Lugar3.setTipoLugar("Hotel");
                    Lugar3.setImagenHotel(R.drawable.talin);



                    lugar Lugar4 = realm.createObject(lugar.class);
                    Lugar4.setLugId(4);
                    Lugar4.setLugNombre("La Plazuela");
                    Lugar4.setLugDireccion("Popayan");
                    Lugar4.setLugTelefono("018000555");
                    Lugar4.setLugCorreo("lp@gmail.com");
                    Lugar4.setLugLatitud("2.442077");
                    Lugar4.setLugLongitud("-76.608129");
                    Lugar4.setLugDescripcion("Hotel la Plazuela");
                    Lugar4.setTipoLugar("Hotel");
                    Lugar4.setImagenHotel(R.drawable.mountain);

                }

            });

        }

    }


    //El metodo llenarLugares Es el encargado de alimentar nuestra lista listLugares

    public void llenarLugares() {

        //Quemamos la latitud y longitud  de la Ubicacion Cali a los editext
        etLatitudDes.setText("3.449555");
        etLongitudDes.setText("-76.532526");


        Realm realm = Realm.getDefaultInstance();
        RealmResults<lugar> results = realm.where(lugar.class).findAll();
        for (lugar Lugar : results) {
            listLugares.add(new lugar(Lugar.getLugId(), Lugar.getLugNombre(), Lugar.getLugDireccion(), Lugar.getLugTelefono(),
                    Lugar.getLugCorreo(), Lugar.getLugLatitud(), Lugar.getLugLongitud(), Lugar.getLugDescripcion(),
                    Lugar.getTipoLugar(), Lugar.getImagenHotel()));

        }

    }

}