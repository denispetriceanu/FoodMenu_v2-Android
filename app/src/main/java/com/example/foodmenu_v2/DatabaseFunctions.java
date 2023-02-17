package com.example.foodmenu_v2;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseFunctions {
    void InsertProduct(){
        DatabaseReference reference = FirebaseDatabase
                .getInstance("https://foodmenu-v2-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("FoodOptions");

        reference.child("1").setValue("Water");
    }


}
