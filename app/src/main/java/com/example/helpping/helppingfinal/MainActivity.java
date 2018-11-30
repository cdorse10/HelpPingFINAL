package com.example.helpping.helppingfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button eBtn = (Button)findViewById(R.id.emergencyButton);
        Button mapBtn = (Button)findViewById(R.id.crimeMapbutton);
        Button settingsBtn = (Button)findViewById(R.id.settingsButton);

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CrimeMapsActivity.class));
            }
        });

        eBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
}
