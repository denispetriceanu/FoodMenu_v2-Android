package com.example.foodmenu_v2.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Product implements Parcelable {
    // product -> the id of product
    // product_name -> name of product
    String product_name, time, description, category, product;
    int price, amount;

    public Product(String product, String product_name, String time, String description, String category, int price, int amount) {
        this.product_name = product_name;
        this.time = time;
        this.description = description;
        this.category = category;
        this.product = product;
        this.price = price;
        this.amount = amount;
    }

    protected Product(Parcel in) {
        product_name = in.readString();
        time = in.readString();
        description = in.readString();
        category = in.readString();
        product = in.readString();
        price = in.readInt();
        amount = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + product_name + '\'' +
                ", time='" + time + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(product_name);
        parcel.writeString(time);
        parcel.writeString(description);
        parcel.writeString(category);
        parcel.writeString(product);
        parcel.writeInt(price);
        parcel.writeInt(amount);
    }
}
