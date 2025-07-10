package org.ecommerce.blackfriday.order.infraestructure.client;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.ecommerce.blackfriday.order.application.port.output.client.CartServicePort;
import org.ecommerce.blackfriday.order.infraestructure.client.dto.Cart;
import org.ecommerce.blackfriday.order.infraestructure.client.exception.CartNotFoundException;

@ApplicationScoped
public class CartServiceAdapter implements CartServicePort {

    @Inject
    @RestClient
    CartClient cartClient;

    @Override
    public Uni<Cart> getCart(String customerId) {
        return cartClient.getCartByCustomerId(customerId)
                .onItem()
                .transformToUni(resp -> switch (resp.getStatus()) {
                    case 200 -> Uni.createFrom().item(resp.readEntity(Cart.class));
                    case 404 -> Uni.createFrom().failure(new CartNotFoundException("Cart Not Found"));
                    default -> Uni.createFrom().failure(new RuntimeException("Invalid Request"));
                });
    }
}
