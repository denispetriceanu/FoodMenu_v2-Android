package com.example.foodmenu_v2.Models;

import java.util.ArrayList;

public class Order {
    String waiter_name, time, total_price, table;
    ArrayList<Product> product_list;

    public Order(String waiter_name, String time, String total_price, String table, ArrayList<Product> productsOrder) {
        this.waiter_name = waiter_name;
        this.time = time;
        this.total_price = total_price;
        this.table = table;
        this.product_list = productsOrder;
    }

    public String getWaiter_name() {
        return waiter_name;
    }

    public void setWaiter_name(String waiter_name) {
        this.waiter_name = waiter_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public ArrayList<Product> getProduct_list() {
        return product_list;
    }

    public void setProduct_list(ArrayList<Product> product_list) {
        this.product_list = product_list;
    }
}
