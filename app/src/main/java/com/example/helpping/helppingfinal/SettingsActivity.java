package com.example.helpping.helppingfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    SwitchCompat switch_2, switch_3, switch_4;

    Button switch_1;

    Boolean stateSwitch2, stateSwitch3, stateSwitch4;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        preferences = getSharedPreferences("PREFS", 0);
        stateSwitch2 = preferences.getBoolean("switch2", false);
        stateSwitch3 = preferences.getBoolean("switch3", false);
        stateSwitch4 = preferences.getBoolean("switch4", false);

        switch_1 = (Button) findViewById(R.id.switch_1);
        switch_2 = (SwitchCompat) findViewById(R.id.switch_2);
        switch_3 = (SwitchCompat) findViewById(R.id.switch_3);
        switch_4 = (SwitchCompat) findViewById(R.id.switch_4);

        switch_2.setChecked((stateSwitch2));
        switch_3.setChecked((stateSwitch3));
        switch_4.setChecked((stateSwitch4));

        switch_1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(login);
            }
        });

        switch_2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                stateSwitch2 = !stateSwitch2;
                switch_2.setChecked(stateSwitch2);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("switch2", stateSwitch2);
                editor.apply();
            }
        });
        switch_3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                stateSwitch3 = !stateSwitch3;
                switch_3.setChecked(stateSwitch3);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("switch3", stateSwitch3);
                editor.apply();
            }
        });
        switch_4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                stateSwitch4 = !stateSwitch4;
                switch_4.setChecked(stateSwitch4);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("switch4", stateSwitch4);
                editor.apply();
            }
        });
    }
}
