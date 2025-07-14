package org.ecommerce.blackfriday.order.infraestructure.client.model;

import java.math.BigDecimal;

public record CartItemModel(
        String cartItemId,
        String productId,
        BigDecimal price,
        int quantity,
        BigDecimal total
) {
    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId='" + cartItemId + '\'' +
                ", productId='" + productId + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", total=" + total +
                '}';
    }
}
