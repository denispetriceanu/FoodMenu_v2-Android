package com.example.foodmenu_v2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Description extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        TextView title = (TextView) findViewById(R.id.textView5);
        TextView price = (TextView) findViewById(R.id.textView6);
        TextView description = (TextView) findViewById(R.id.textView7);


        Intent intent = getIntent();
        if (intent.hasExtra("description"))
            description.setText(intent.getStringExtra("description"));
        if (intent.hasExtra("price"))
            price.setText(intent.getStringExtra("price"));
        System.out.println("Error: "  + intent.getStringExtra("price"));
        if (intent.hasExtra("name"))
            title.setText(intent.getStringExtra("name"));

        // Code for navbar
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            // Activează butonul de navigare înapoi
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Verificăm dacă utilizatorul a făcut clic pe butonul de navigare înapoi
        if (item.getItemId() == android.R.id.home) {
            // Termină activitatea curentă și revino la activitatea anterioară
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}