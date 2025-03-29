package org.ecommerce.blackfriday.e_commerce_black_friday_sale.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private String customerId;
    private List<DetailShoppingCart> items;
    private BigDecimal total;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void addItem (DetailShoppingCart item) {
        items.add(item);
    }

    public void removeItem (Long itemId) {
        items.removeIf(item -> item.getItemId().equals(itemId));
    }

    public void updateItem (DetailShoppingCart item) {
        items
                .stream()
                .filter(i -> i.getItemId().equals(item.getItemId()))
                .findFirst()
                .ifPresent(data -> {
                    items.set(items.indexOf(data), item);
                });
    }
}
