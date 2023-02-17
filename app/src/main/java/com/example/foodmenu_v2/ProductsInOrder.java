package com.example.foodmenu_v2;

public class ProductsInOrder{
    int amount;
    String name_product;

    public ProductsInOrder(int amount, String name_product) {
        this.amount = amount;
        this.name_product = name_product;
    }

    public int getAmount() {
        return amount;
    }

    public String getName_product() {
        return name_product;
    }

    public void increaseAmount(){
        this.amount = this.amount+1;
    }

    public void decreaseAmount(){
        this.amount = this.amount-1;
    }
}