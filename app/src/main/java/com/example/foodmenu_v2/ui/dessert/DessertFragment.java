package com.example.foodmenu_v2.ui.dessert;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.foodmenu_v2.MainActivity;
import com.example.foodmenu_v2.R;
import com.example.foodmenu_v2.databinding.FragmentDessertBinding;

public class DessertFragment extends Fragment {
    private FragmentDessertBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDessertBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // This add clickListener to order button from fragment
        Button SendOrder = root.findViewById(R.id.OrderDessert);
        SendOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.NavigateToFinishCommand(root.getContext());

            }
        });

//        Product.ProductListData[] DrinksListData = new Product.ProductListData[]{
//                new Product.ProductListData("Clatite cu branza si smantana", 3, 100),
//                new Product.ProductListData("Clatite cu branza si smantana", 3, 100),
//                new Product.ProductListData("Clatite cu branza si smantana", 3, 100),
//                new Product.ProductListData("Clatite cu branza si smantana", 3, 100),
//                new Product.ProductListData("Clatite cu branza si smantana", 3, 100),
//                new Product.ProductListData("Clatite cu branza si smantana", 3, 100),
//                new Product.ProductListData("Clatite cu branza si smantana", 3, 100),
//                new Product.ProductListData("Clatite cu branza si smantana", 3, 100),
//                new Product.ProductListData("Clatite cu branza si smantana", 3, 100),
//                new Product.ProductListData("Clatite cu branza si smantana", 3, 100),
//                new Product.ProductListData("Clatite cu branza si smantana", 3, 100),
//                new Product.ProductListData("Clatite cu branza si smantana", 3, 100),
//                new Product.ProductListData("Clatite cu branza si smantana", 3, 100),
//                new Product.ProductListData("Clatite cu branza si smantana", 3, 100),
//                new Product.ProductListData("Clatite cu branza si smantana", 3, 100),
//                new Product.ProductListData("Clatite cu branza si smantana", 3, 100),
//                new Product.ProductListData("Clatite cu branza si smantana", 3, 100),
//                new Product.ProductListData("Clatite cu branza si smantana", 3, 100),
//        };
//
//        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
//        ProductAdapter adapter = new ProductAdapter(DrinksListData);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
