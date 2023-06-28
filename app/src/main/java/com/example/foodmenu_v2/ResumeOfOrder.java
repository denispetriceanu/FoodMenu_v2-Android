package com.example.foodmenu_v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.OnReceiveContentListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodmenu_v2.Models.Order;
import com.example.foodmenu_v2.Models.Product;
import com.example.foodmenu_v2.databinding.FragmentEntreesBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResumeOfOrder extends AppCompatActivity {
    ProductAdapter adapter;
    String waiter_name, table;
    RecyclerView recyclerView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_of_order);
        Button sendOrderButton = (Button) this.findViewById(R.id.sendOrder);

        ArrayList<Product> productList = getIntent().getParcelableArrayListExtra("orderList");


        recyclerView = (RecyclerView) this.findViewById(R.id.recyclerView);
        adapter = new ProductAdapter(productList, getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);

        TextView price_calc = this.findViewById(R.id.textView4);
        price_calc.setText("Prețul total: " + calcTotalPrice(productList));

        GetSharedInformation();

        sendOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int totPrice = calcTotalPrice(productList);
                if (totPrice > 0) {
                    String timestampStr = DateTimeFormatter.ISO_INSTANT.format(Instant.now());
                    // ToDo: finish information collection
                    Order newOrder = new Order(waiter_name, timestampStr, String.valueOf(totPrice), table, productList);
                    FirebaseDatabase
                            .getInstance("https://foodmenu-v2-default-rtdb.europe-west1.firebasedatabase.app")
                            .getReference("Orders")
                            .push().
                            setValue(newOrder)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    // Clean orderList
                                    MainActivity.ResetOrderList();
                                    productList.clear();
                                    // update recyclerView
                                    adapter = new ProductAdapter(productList, getApplicationContext());
                                    recyclerView.setAdapter(adapter);
                                    // Show message success
                                    DynamicToast.makeSuccess(getApplicationContext(), "Comanda a fost plasată cu succes!!", Toast.LENGTH_SHORT).show();

                                }
                            });
                } else {
                    Toast.makeText(getApplicationContext(), "NU ai adăugat nici un produs în listă.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int calcTotalPrice(List<Product> productList) {
        int sum = 0;
        for (Product prod : productList)
            sum += prod.getPrice();
        return sum;
    }

    private void GetSharedInformation() {
        SharedPreferences sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        waiter_name = sharedPref.getString("waiter_name", "");
        table = sharedPref.getString("table", "");
        Toast.makeText(getApplicationContext(), "Waiter: " + waiter_name + " and table: " + table + " . You can set this from main menu.", Toast.LENGTH_LONG).show();
    }
}