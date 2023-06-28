package com.example.foodmenu_v2;

import android.content.Intent;
import android.os.Bundle;

import com.example.foodmenu_v2.Models.Product;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodmenu_v2.databinding.ActivityCheckSecurityBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

public class CheckSecurity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityCheckSecurityBinding binding;

    EditText pass;
    Button checkBtn;
    String dbPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_security);

        pass = (EditText) findViewById(R.id.editTextTextPassword);
        checkBtn = (Button) findViewById(R.id.button);

        // Get reference to DB Firebase
        DatabaseReference databaseRef = FirebaseDatabase
                .getInstance("https://foodmenu-v2-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("TempPass");
        // Add a listener to get info from DB
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String, String> map = (HashMap<String, String>) dataSnapshot.getValue();
                assert map != null;
                dbPass = String.valueOf(map.get("password"));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), ":( Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(dbPass);
                System.out.println(!pass.getText().toString().equals(dbPass));
                if(pass.getText().toString().length() < 3)
                    Toast.makeText(getApplicationContext(), "Parola este prea scurtă!" + pass.getText().toString().length(), Toast.LENGTH_SHORT).show();
                else
                    if(!pass.getText().toString().equals(dbPass))
                        Toast.makeText(getApplicationContext(), "Parola este gresita!", Toast.LENGTH_SHORT).show();
                    else{
                        Intent i = new Intent(getApplicationContext(), Start_Activity.class);
                        startActivity(i);
                    }
            }
        });
    }


}