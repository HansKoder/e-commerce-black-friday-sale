package org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.mapper;

import org.ecommerce.blackfriday.cart.domain.model.entity.CartItem;
import org.ecommerce.blackfriday.cart.domain.model.entity.Product;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.Quantity;
import org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.model.RedisCartItemModel;
import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;
import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductId;

import java.math.BigDecimal;
import java.util.UUID;

public class RedisCartItemMapper {

    public static CartItem toDomain (RedisCartItemModel itemModel) {
        ProductId productId = new ProductId(UUID.fromString(itemModel.productId()));
        Product product = new Product.Builder()
                .withProductId(productId)
                .withPrice(new Money(itemModel.productPrice()))
                .build();

        return new CartItem(product, new Quantity(itemModel.quantity()));
    }

    public static RedisCartItemModel toRedisCart (CartItem domain) {
        String productId = domain.getProduct().getId().getValue().toString();
        BigDecimal price = domain.getProduct().getPrice().getAmount();
        int quantity = domain.getQuantity().getValue();
        BigDecimal total = domain.getTotal().getAmount();

        return new RedisCartItemModel(productId, price, quantity, total);
    }
}
