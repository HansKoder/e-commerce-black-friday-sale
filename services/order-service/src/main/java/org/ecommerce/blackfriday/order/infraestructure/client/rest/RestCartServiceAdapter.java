package org.ecommerce.blackfriday.order.infraestructure.client.rest;

import io.smallrye.mutiny.Uni;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.ecommerce.blackfriday.order.application.port.output.client.CartClientPort;
import org.ecommerce.blackfriday.order.infraestructure.client.model.CartModel;
import org.ecommerce.blackfriday.order.infraestructure.client.rest.exception.CartNotFoundException;

@Alternative
@Priority(2)
@ApplicationScoped
public class RestCartServiceAdapter implements CartClientPort {

    @Inject
    @RestClient
    CartClient cartClient;

    @Override
    public Uni<CartModel> getCart(String customerId) {
        return cartClient.getCartByCustomerId(customerId)
                .onItem()
                .transformToUni(resp -> switch (resp.getStatus()) {
                    case 200 -> Uni.createFrom().item(resp.readEntity(CartModel.class));
                    case 404 -> Uni.createFrom().failure(new CartNotFoundException("Cart Not Found"));
                    default -> Uni.createFrom().failure(new RuntimeException("Invalid Request"));
                });
    }
}
