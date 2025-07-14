package org.ecommerce.blackfriday.order.infraestructure.client.model;

import java.math.BigDecimal;
import java.util.List;

public record CartModel(
        String customerId,
        String cartId,
        List<CartItemModel> items,
        BigDecimal total
) {
    @Override
    public String toString() {
        return "CartModel{" +
                "cartId='" + cartId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", items=" + items +
                ", total=" + total +
                '}';
    }
}
