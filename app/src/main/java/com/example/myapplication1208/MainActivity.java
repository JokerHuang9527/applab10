package com.example.myapplication1208;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication1208.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults
    ){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1)
            for(int result : grantResults)
                if(result != PackageManager.PERMISSION_GRANTED)
                    finish();
                else
                    initMap();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        else
            initMap();
    }
    private void initMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if(mapFragment != null)
            mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap){
        if(ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                    this,
                Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        googleMap.setMyLocationEnabled(true);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position (new LatLng(25.033611, 121.565000));
        markerOptions.title("台北 101");
        markerOptions.draggable(true);
        googleMap.addMarker (markerOptions);
        markerOptions.position (new LatLng(25.047924, 121.517081));
        markerOptions.title("台北車站");
        markerOptions.draggable(true);
        googleMap.addMarker(markerOptions);
// 繪製線段
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.add(new LatLng(25.033611, 121.565000));
        polylineOptions.add(new LatLng(25.032728, 121.564137));
        polylineOptions.add(new LatLng(25.047924, 121.517081));
        polylineOptions.color(Color.RED);
        Polyline polyline = googleMap.addPolyline (polylineOptions);
        polyline.setWidth(10);
// 初始化地圖中心點及size
        googleMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                        new LatLng(25.034,  121.545),  13)
        );
    }
}