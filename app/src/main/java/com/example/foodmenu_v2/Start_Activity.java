package com.example.foodmenu_v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Start_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ToDo: here you can modify night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_start);

        Button myButton = findViewById(R.id.buttonNavMeniu);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String waiter_name = ((EditText)(findViewById(R.id.waiterName))).getText().toString();
                String table = ((EditText)(findViewById(R.id.numberOfTable))).getText().toString();
                setInformation(table, waiter_name);
                Intent i = new Intent(getApplicationContext() ,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void setInformation(String table, String name){
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("waiter_name", name);
        editor.putString("table", table);
        editor.putBoolean("isSet", true);
        editor.apply();
    }
}