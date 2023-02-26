package com.example.foodmenu_v2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.foodmenu_v2.Models.Product;
import com.example.foodmenu_v2.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public static ArrayList<Product> orderList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ToDo: here you can modify night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        // -------------------------------------
        // ToDo: here is the code which keep the list
        orderList = new ArrayList<>();

        // ---------------------------------------

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_dashboard, R.id.navigation_home, R.id.navigation_notifications, R.id.navigation_dessert)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mybutton) {
            Intent i = new Intent(getApplicationContext(), Start_Activity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }


    public static void NavigateToFinishCommand(Context appContext) {
        Intent i = new Intent(appContext, ResumeOfOrder.class);
        i.putParcelableArrayListExtra("orderList", orderList);
        appContext.startActivity(i);
    }

    // Receive to parameter - new product = product which will be put in list,
    //                      - increase = true -> will increase and false -> will decrease.
    public static boolean ModifyListProduct(Product manipulatedProduct, boolean increase) {
        if (increase)
            if (!IncreaseProduct(manipulatedProduct)) {
                manipulatedProduct.setAmount(1);
                orderList.add(manipulatedProduct);
                return true;
            } else
                return true;
        else {
            for (Product product : orderList) {
                if (product.getProduct_name().equals(manipulatedProduct.getProduct_name())) {
                    product.setAmount((product.getAmount() - 1));
                    return true;
                }
            }
            return false;
        }
    }

    private static boolean IncreaseProduct(Product newProduct) {
        for (Product product : orderList) {
            if (product.getProduct_name().equals(newProduct.getProduct_name())) {
                product.setAmount(newProduct.getAmount() + 1);
                return true;
            }
        }

        return false;
    }

    public static void ResetOrderList() {
        orderList = new ArrayList<>();
    }
}