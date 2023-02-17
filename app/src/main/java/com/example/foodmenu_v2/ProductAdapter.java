package com.example.foodmenu_v2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.foodmenu_v2.Models.Product;

import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    private List<Product> list_data;

    // RecyclerView recyclerView;
    public ProductAdapter(List<Product> list_data) {
        this.list_data = list_data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.product_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Product myListData = list_data.get(position);
        holder.name.setText(list_data.get(position).getName());
        holder.amount.setText(String.valueOf(list_data.get(position).getAmount()));
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: " + myListData.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myListData.setAmount(myListData.getAmount()+1);
                Toast.makeText(view.getContext(),"Amount increase by 1.",Toast.LENGTH_SHORT).show();
                holder.amount.setText(String.valueOf(myListData.getAmount()));
            }
        });
        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myListData.setAmount(myListData.getAmount()-1);
                Toast.makeText(view.getContext(),"Amount decrease by 1.",Toast.LENGTH_SHORT).show();
                holder.amount.setText(String.valueOf(myListData.getAmount()));
            }
        });
    }


    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout relativeLayout;
        public TextView name, amount;
        public Button increase, decrease;
        public ViewHolder(View itemView) {
            super(itemView);
            this.amount = (TextView) itemView.findViewById(R.id.amount_data);
            this.name = (TextView) itemView.findViewById(R.id.product_name);
            this.increase = (Button) itemView.findViewById(R.id.increase_button);
            this.decrease = (Button) itemView.findViewById(R.id.decrease_button);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayoutItem);
        }
    }
}