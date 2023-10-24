package com.example.Advices.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Advices.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationActivity extends AppCompatActivity {

    FusedLocationProviderClient fusedLocationProviderClient;
    TextView country, city, address, longitude, latitude;
    Button btnLocation, btnReturn, btnSeeLocation;
    private final static int REQUEST_CODE = 100;
    private boolean check = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);


        country = findViewById(R.id.country);
        city = findViewById(R.id.city);
        address = findViewById(R.id.address);
        longitude = findViewById(R.id.longitude);
        latitude = findViewById(R.id.latitude);
        btnLocation = findViewById(R.id.btnLocation);
        btnReturn = findViewById(R.id.btnReturn);
        btnSeeLocation = findViewById(R.id.btnSeeLocation);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLastLocation();
                check = true;
            }
        });
        btnSeeLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check == false)
                    Toast.makeText(LocationActivity.this, "Bạn chưa nhấn Get Location", Toast.LENGTH_SHORT).show();
                else
                {
                    Intent i = new Intent(LocationActivity.this, MapsActivity.class);
                    i.putExtra("positionlat", latitude.getText());
                    i.putExtra("positionlong", longitude.getText());
                    i.putExtra("positionname", address.getText());
                    startActivity(i);
                }
            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LocationActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void getLastLocation() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if(location != null){
                                Geocoder geocoder = new Geocoder(LocationActivity.this, Locale.getDefault());
                                List<Address> addresses = null;
                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                                    latitude.setText(String.valueOf(addresses.get(0).getLatitude()));
                                    longitude.setText(String.valueOf(addresses.get(0).getLongitude()));
                                    country.setText(addresses.get(0).getCountryName());
                                    city.setText(addresses.get(0).getLocality());
                                    address.setText(addresses.get(0).getAddressLine(0));

                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                            }
                        }
                    });




        }else {
            askPermission();
        }
    }
    private void askPermission() {
        ActivityCompat.requestPermissions(LocationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }else{
                Toast.makeText(this, "Required Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }
}