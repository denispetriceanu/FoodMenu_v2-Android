package com.example.foodmenu_v2.ui.drinks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodmenu_v2.MainActivity;
import com.example.foodmenu_v2.Models.Product;
import com.example.foodmenu_v2.R;
import com.example.foodmenu_v2.databinding.FragmentDrinksBinding;
import com.example.foodmenu_v2.ProductAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DrinksFragment extends Fragment {

    private FragmentDrinksBinding binding;
    List<Product> DrinksListData;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        DrinksListData = new ArrayList<Product>();

        binding = FragmentDrinksBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // This add clickListener to order button from fragment
        Button SendOrder = root.findViewById(R.id.OrderEntrees);
        SendOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.NavigateToFinishCommand(root.getContext());

            }
        });

        // Get reference to DB Firebase
        DatabaseReference databaseRef = FirebaseDatabase
                .getInstance("https://foodmenu-v2-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("FoodOptions");
        Query query = databaseRef.orderByChild("category").equalTo("drinks");
        // Add a listener to get info from DB
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // make a foreach in returning object
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HashMap<String, String> map = (HashMap<String, String>) snapshot.getValue();
                    assert map != null;
                    Product newProduct = new Product(map.get("name"), map.get("time"), map.get("description"), map.get("category"), map.get("price"), 0);
                    DrinksListData.add(newProduct);
                }
                if(DrinksListData.size() == 0)
                    Toast.makeText(getContext(), ":| Nu s-a returnat nici un produs in aceasta categorie! :(", Toast.LENGTH_SHORT).show();

                RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
                ProductAdapter adapter = new ProductAdapter(DrinksListData);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), ":( Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}