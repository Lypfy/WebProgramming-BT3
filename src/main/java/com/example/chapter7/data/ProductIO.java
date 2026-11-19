package com.example.chapter7.data;

import com.example.chapter7.model.Product;

import java.util.List;


public class ProductIO {
    private static final List<Product> PRODUCTS = List.of(
            new Product("1", "86 (the band) - True Life Songs and Pictures", 14.95),
            new Product("2", "Paddlefoot - The first CD", 12.95),
            new Product("3", "Paddlefoot - The second CD", 14.95),
            new Product("4", "Joe Rut - Genuine Wood Grained Finish", 14.95)
    );
    public static List<Product> getProducts() {
        return PRODUCTS;
    }
    public static Product getProduct(String productId) {
        for (Product p : PRODUCTS) {
            if (p.getProductId().equalsIgnoreCase(productId)) {
                return p;
            }
        }
        return null;
    }
}
