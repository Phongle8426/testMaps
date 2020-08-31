package com.example.testmaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button getloca;
    TextView lati, longi, country, address, locality;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Anhxa();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getloca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            Location location = task.getResult();
                            if (location != null) {
                                try {
                                    Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                                    lati.setText(addresses.get(0).getLatitude() + "");
                                    longi.setText(addresses.get(0).getLongitude() + "");
                                    country.setText(addresses.get(0).getCountryName() + "");
                                    locality.setText(addresses.get(0).getLocality() + "");
                                    address.setText(addresses.get(0).getAddressLine(0) + "");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });
    }

//    private void getlocation() {
//        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
//            @Override
//            public void onComplete(@NonNull Task<Location> task) {
//                Location location = task.getResult();
//                if (location != null) {
//                    try {
//                        Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
//                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//
//                        lati.setText(addresses.get(0).getLatitude() + "");
//                        longi.setText(addresses.get(0).getLongitude() + "");
//                        country.setText(addresses.get(0).getCountryName() + "");
//                        locality.setText(addresses.get(0).getLocality() + "");
//                        address.setText(addresses.get(0).getAddressLine(0) + "");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//    }

    public void Anhxa(){
        getloca = (Button) findViewById(R.id.btn_getlocation);
        lati = (TextView) findViewById(R.id.txt_latitude);
        longi = (TextView) findViewById(R.id.txt_longitude);
        country = (TextView) findViewById(R.id.txt_country);
        address = (TextView) findViewById(R.id.txt_address);
        locality = (TextView) findViewById(R.id.txt_locality);

    }

}