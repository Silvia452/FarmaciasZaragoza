package com.example.prueba_programacion_android_23;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.prueba_programacion_android_23.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    String nombre;
    double latitud, longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Intent intent = getIntent();
        nombre = intent.getStringExtra("nombre");
        latitud = intent.getDoubleExtra("latitud", 0);
        longitud = intent.getDoubleExtra("longitud", 0);


        setContentView(R.layout.activity_maps);
        MapView mapa = (MapView) findViewById(R.id.map);
        mapa.onCreate(savedInstanceState);
        mapa.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        LatLng zaragoza = new LatLng(41.6560600, -0.8773400);
        mMap.addMarker(new MarkerOptions().position(zaragoza).title("Marker in Zaragoza"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(zaragoza));
    }
}