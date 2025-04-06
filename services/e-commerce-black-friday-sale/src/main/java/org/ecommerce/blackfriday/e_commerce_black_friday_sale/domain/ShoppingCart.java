package org.ecommerce.blackfriday.e_commerce_black_friday_sale.domain;

import org.ecommerce.blackfriday.e_commerce_black_friday_sale.exceptions.CartItemNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private String customerId;
    private List<Item> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<Item> getItems() {
        return items;
    }

    public BigDecimal getTotal() {
        return items.stream()
                .map(Item::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void addItem (Item item) {
        items.add(item);
    }

    public void removeItem (Long itemId) {
        items.removeIf(item -> item.getItemId().equals(itemId));
    }

    public void updateItem (Item item) {
        items
                .stream()
                .filter(i -> i.getItemId().equals(item.getItemId()))
                .findFirst()
                .ifPresentOrElse(
                        success -> items.set(items.indexOf(success), item),
                        () -> {throw new CartItemNotFoundException(customerId, item.getItemId().toString());}
                );
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
