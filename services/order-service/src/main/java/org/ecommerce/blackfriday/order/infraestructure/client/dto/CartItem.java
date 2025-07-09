package org.ecommerce.blackfriday.order.infraestructure.client.dto;

import java.math.BigDecimal;

public record CartItem (
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
