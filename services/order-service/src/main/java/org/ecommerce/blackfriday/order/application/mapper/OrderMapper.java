package org.ecommerce.blackfriday.order.application.mapper;

import io.quarkus.logging.Log;
import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;
import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductId;
import org.ecommerce.blackfriday.order.application.dto.OrderResponse;
import org.ecommerce.blackfriday.order.domain.model.entity.Order;
import org.ecommerce.blackfriday.order.domain.model.entity.OrderItem;
import org.ecommerce.blackfriday.order.domain.model.entity.Product;
import org.ecommerce.blackfriday.order.domain.model.valueobject.CustomerId;
import org.ecommerce.blackfriday.order.domain.model.valueobject.OrderItemId;
import org.ecommerce.blackfriday.order.infraestructure.client.model.CartModel;
import org.ecommerce.blackfriday.order.infraestructure.client.model.CartItemModel;

import java.util.List;
import java.util.UUID;

public class OrderMapper {

    public static Order toDomain (CartModel cartDto) {
        Log.infof("[Mapper] order toDomain, cartDTO: %s", cartDto);
        // OrderId orderId = new OrderId(UUID.randomUUID());
        Order domain = Order.Builder.anOrder()
                .customerId(new CustomerId(UUID.fromString(cartDto.customerId())))
                .items(buildItems(cartDto.items()))
                .total(new Money(cartDto.total()))
                .build();

        Log.infof("[Mapper] from dto to domain, this order is mapped %s", domain);
        return domain;
    }

    private static List<OrderItem> buildItems (List<CartItemModel> cartItems) {
        return cartItems.stream()
                .map(OrderMapper::orderItemToDomain)
                .toList();
    }

    private static OrderItem orderItemToDomain (CartItemModel cartItemDto) {
        Log.infof("[Mapper] orderItem toDomain, payload %s", cartItemDto);
        return OrderItem.Builder
                .anOrderItem()
                .product(productToDomain(cartItemDto))
                .price(new Money(cartItemDto.price()))
                .orderItemId(new OrderItemId(UUID.fromString(cartItemDto.cartItemId())))
                .subtotal(new Money(cartItemDto.total()))
                .quantity(cartItemDto.quantity())
                .build();
    }

    private static Product productToDomain (CartItemModel cartItem) {
        Log.infof("[Mapper] product to domain, payload: %s", cartItem);
        Product product = Product.Builder.aProduct()
                .id(new ProductId(UUID.fromString(cartItem.productId())))
                .price(new Money(cartItem.price()))
                .build();

        Log.infof("[Mapper] product to domain, mapped, this is the product domain: %s", product);

        return product;
    }

    public static OrderResponse toResponse (Order domain) {
        Log.infof("[Mapper] orderResponse to response, payload: %s", domain);
        return new OrderResponse(domain.getTrackingId().getValue().toString(), "", "");
    }
}
