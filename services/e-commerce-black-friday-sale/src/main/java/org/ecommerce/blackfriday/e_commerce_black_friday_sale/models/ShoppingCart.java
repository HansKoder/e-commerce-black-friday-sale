package org.ecommerce.blackfriday.e_commerce_black_friday_sale.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private String customerId;
    private List<DetailShoppingCart> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<DetailShoppingCart> getItems() {
        return items;
    }

    public BigDecimal getTotal() {
        return items.stream()
                .map(DetailShoppingCart::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
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

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "customerId='" + customerId + '\'' +
                ", items=" + items +
                ", total=" + getTotal() +
                '}';
    }
}
