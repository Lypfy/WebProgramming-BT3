package com.example.chapter7.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cart implements Serializable {
    private List<LineItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<LineItem> getItems() {
        return items;
    }

    public int getCount() {
        return items.size();
    }

    public void addItem(LineItem item) {
        if (item == null || item.getProduct() == null) return;
        String productId = item.getProduct().getProductId();

        for (LineItem lineItem : items) {
            if (lineItem.getProduct().getProductId().equalsIgnoreCase(productId)) {
                lineItem.setQuantity(lineItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        items.add(item);
    }

    public void removeItem(String productId) {
        if (productId == null) return;
        items.removeIf(item -> item.getProduct().getProductId().equalsIgnoreCase(productId));
    }

    public void updateItem(String productId, int quantity) {
        if (productId == null) return;
        if (quantity <= 0) {
            removeItem(productId);
            return;
        }
        for (LineItem lineItem : items) {
            if (lineItem.getProduct().getProductId().equalsIgnoreCase(productId)) {
                lineItem.setQuantity(quantity);
                return;
            }
        }
    }

    public double getTotal() {
        double total = 0;
        for(LineItem lineItem : items) {
            total += lineItem.getTotal();
        }
        return total;
    }

    public String getTotalCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
        return currency.format(this.getTotal());
    }
}
