package com.example.helpping.helppingfinal;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button eBtn = (Button)findViewById(R.id.emergencyButton);
        Button mapBtn = (Button)findViewById(R.id.crimeMapbutton);
        Button settingsBtn = (Button)findViewById(R.id.settingsButton);

        final int LOCATION_PERMISSION_REQUEST_CODE = 1;

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Warning");
        alertDialog.setMessage("This app's main functionality is to be used for emergencies only");

        new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
        alertDialog.show();

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CrimeMapsActivity.class));
            }
        });


        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        final double longitude = location.getLongitude();
        final double latitude = location.getLatitude();

        eBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myLoc = database.getReference("Position");



                MarkerOptions mp = new MarkerOptions();

                mp.position(new LatLng(latitude, longitude));

                mp.title("My Position");
                myLoc.setValue(mp.position(new LatLng(latitude, longitude)));

                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Help is on the way!");

                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        };
                alertDialog.show();

                /*// Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                //DatabaseReference myLoc = database.getReference("Emergency");

                DatabaseReference myLoc = database.getReference("");
                // Get lat/long and send to database > plot on map > alert other users?
                if (ContextCompat.checkSelfPermission(View.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Permission to access the location is missing.
                    PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                            Manifest.permission.ACCESS_FINE_LOCATION, true);

                    // instantiate the location manager, note you will need to request permissions in your manifest
                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    // get the last know location from your location manager.
                    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    LatLng latLng;
                    latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    myLoc.setValue(latLng);

                //myLoc.setValue("Hello, World!");*/


            }
        });





    }

}
    /*public void onPing() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myLoc = database.getReference("");
        // Get lat/long and send to database > plot on map > alert other users?
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
            // instantiate the location manager, note you will need to request permissions in your manifest
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            // get the last know location from your location manager.
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            LatLng latLng;
            latLng = new LatLng(location.getLatitude(), location.getLongitude());
            myLoc.setValue(latLng);

        }
    }*/


