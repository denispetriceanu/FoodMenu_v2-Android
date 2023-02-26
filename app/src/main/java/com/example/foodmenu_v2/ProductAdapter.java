package com.example.foodmenu_v2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodmenu_v2.Models.Product;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    private List<Product> list_data;
    private Context mContext;
    // RecyclerView recyclerView;
    public ProductAdapter(List<Product> list_data, Context context) {
        this.mContext = context;
        this.list_data = list_data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.product_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Product myListData = list_data.get(position);
        holder.name.setText(list_data.get(position).getProduct_name());
        holder.amount.setText(String.valueOf(list_data.get(position).getAmount()));
        holder.price.setText("Pret: " + String.valueOf(list_data.get(position).getPrice()) + "lei");

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(mContext, Description.class);
                newIntent.putExtra("name", myListData.getProduct_name());
                newIntent.putExtra("price", String.valueOf(myListData.getPrice()));
                newIntent.putExtra("description", myListData.getDescription());
                mContext.startActivity(newIntent);
            }
        });
        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean response = MainActivity.ModifyListProduct(myListData, true);
                if (response){
                    DynamicToast.makeSuccess(view.getContext(), "Amount increase by 1.", 100).show();
                    System.out.println(MainActivity.orderList.toString());
                    holder.amount.setText(String.valueOf(Integer.parseInt((String) holder.amount.getText())+1));
                }
            }
        });
        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt((String) holder.amount.getText()) > 0) {
                    MainActivity.ModifyListProduct(myListData, false);
                    DynamicToast.makeError(view.getContext(), "Amount decrease by 1.").show();
                    holder.amount.setText(String.valueOf(Integer.parseInt((String) holder.amount.getText()) - 1));
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout relativeLayout;
        public TextView name, amount, price;
        public Button increase, decrease;
        public ViewHolder(View itemView) {
            super(itemView);
            this.amount = (TextView) itemView.findViewById(R.id.amount_data);
            this.price = (TextView) itemView.findViewById(R.id.textView3);
            this.name = (TextView) itemView.findViewById(R.id.product_name);
            this.increase = (Button) itemView.findViewById(R.id.increase_button);
            this.decrease = (Button) itemView.findViewById(R.id.decrease_button);
            relativeLayout = (ConstraintLayout)itemView.findViewById(R.id.relativeLayoutItem);
        }
    }
}