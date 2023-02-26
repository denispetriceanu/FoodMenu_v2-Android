package com.example.foodmenu_v2.ui.theMainDish;

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
import com.example.foodmenu_v2.ProductAdapter;
import com.example.foodmenu_v2.R;
import com.example.foodmenu_v2.databinding.FragmentTheMainDishBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class TheMainDishFragment extends Fragment {

    private FragmentTheMainDishBinding binding;
    List<Product> MainDishListData;
    public View onCreateView(@NonNull LayoutInflater inflater,

                             ViewGroup container, Bundle savedInstanceState) {
               binding = FragmentTheMainDishBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // This add clickListener to order button from fragment
        Button SendOrder = root.findViewById(R.id.OrderEntrees);
        SendOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.NavigateToFinishCommand(root.getContext());

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        GetDataFromDb();
    }

    private void GetDataFromDb(){
        MainDishListData = new ArrayList<Product>();
        // Get reference to DB Firebase
        DatabaseReference databaseRef = FirebaseDatabase
                .getInstance("https://foodmenu-v2-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("FoodOptions");
        Query query = databaseRef.orderByChild("category").equalTo("mainDishes");
        // Add a listener to get info from DB
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // make a foreach in returning object
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HashMap<String, String> map = (HashMap<String, String>) snapshot.getValue();
                    String id_product = snapshot.getKey();
                    assert map != null;
                    // search if in orderList exist this product
                    Product newProduct = new Product(id_product, map.get("name"), map.get("time"), map.get("description"), map.get("category"), Integer.parseInt(Objects.requireNonNull(map.get("price"))), 0);

                    for (Product product : MainActivity.orderList)
                        if (product.getProduct_name().equals(map.get("name"))) {
                            newProduct.setAmount(product.getAmount());
                            break; // for efficiency
                        }
                    MainDishListData.add(newProduct);
                }
                if(MainDishListData.size() == 0)
                    Toast.makeText(getContext(), ":| Nu s-a returnat nici un produs in aceasta categorie! :(", Toast.LENGTH_SHORT).show();

                RecyclerView recyclerView = (RecyclerView) requireActivity().findViewById(R.id.recyclerView);
                ProductAdapter adapter = new ProductAdapter(MainDishListData, getContext());
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), ":( Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}