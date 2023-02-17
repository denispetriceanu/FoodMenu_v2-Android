package com.example.foodmenu_v2.ui.entrees;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodmenu_v2.MainActivity;
import com.example.foodmenu_v2.ProductListData;
import com.example.foodmenu_v2.R;
import com.example.foodmenu_v2.databinding.FragmentDrinksBinding;
import com.example.foodmenu_v2.databinding.FragmentEntreesBinding;
import com.example.foodmenu_v2.ProductAdapter;

public class EntreesFragment extends Fragment {
  

    private FragmentEntreesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEntreesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // This add clickListener to order button from fragment
        Button SendOrder = root.findViewById(R.id.OrderEntrees);
        SendOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.NavigateToFinishCommand(root.getContext());

            }
        });


        ProductListData[] DrinksListData = new ProductListData[]{
                new ProductListData("Aperitiv cu ciuperci, branza si salta verde de la Suceava", 3, 100),
                new ProductListData("Aperitiv cu ciuperci, branza si salta verde", 3, 100),
                new ProductListData("Aperitiv cu ciuperci, branza si salta verde", 3, 100),
                new ProductListData("Aperitiv cu ciuperci, branza si salta verde", 3, 100),
                new ProductListData("Aperitiv cu ciuperci, branza si salta verde", 3, 100),
                new ProductListData("Aperitiv cu ciuperci, branza si salta verde", 3, 100),
                new ProductListData("Aperitiv cu ciuperci, branza si salta verde", 3, 100),
                new ProductListData("Aperitiv cu ciuperci, branza si salta verde", 3, 100),
                new ProductListData("Aperitiv cu ciuperci, branza si salta verde", 3, 100),
                new ProductListData("Aperitiv cu ciuperci, branza si salta verde", 3, 100),
                new ProductListData("Aperitiv cu ciuperci, branza si salta verde", 3, 100),
                new ProductListData("Aperitiv cu ciuperci, branza si salta verde", 3, 100),
                new ProductListData("Aperitiv cu ciuperci, branza si salta verde", 3, 100),
                new ProductListData("Aperitiv cu ciuperci, branza si salta verde", 3, 100),
                new ProductListData("Aperitiv cu ciuperci, branza si salta verde", 3, 100),
                new ProductListData("Aperitiv cu ciuperci, branza si salta verde", 3, 100),
                new ProductListData("Aperitiv cu ciuperci, branza si salta verde", 3, 100),
                new ProductListData("Aperitiv cu ciuperci, branza si salta verde", 3, 100),
        };

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        ProductAdapter adapter = new ProductAdapter(DrinksListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}