package com.example.chapter7.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class Product implements Serializable {
    private String productId;
    private String name;
    private double price;

    public Product() {
        this.productId = "";
        this.name = "";
        this.price = 0;
    }

    public Product(String product_id, String name, double price) {
        this.productId = product_id;
        this.name = name;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPriceCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
        return currency.format(this.price);
    }
}
