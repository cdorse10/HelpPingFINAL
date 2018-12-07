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
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });

        /*LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        final double longitude = location.getLongitude();
        final double latitude = location.getLatitude();*/

        eBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Push Location of user to database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myLoc = database.getReference("Position");

                LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();





                MarkerOptions mp = new MarkerOptions();



                mp.title("My Position");
                //myLoc.setValue(mp.position(new LatLng(latitude, longitude)));

                myLoc.setValue(new LatLng(latitude, longitude));
                //myLoc.setValue("hi");

                //myLoc.setValue(mp.position(new LatLng(35.30525, -80.73682)));
                //myLoc.setValue( new LatLng(35.30703, -80.73574));

                //Show alert letting user now help is on the way
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Help is on the way!");

                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        };
                alertDialog.show();

            }
        });
    }
}


