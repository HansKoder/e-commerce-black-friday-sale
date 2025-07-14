package org.ecommerce.blackfriday.order.infraestructure.client.grpc;

import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import org.ecommerce.blackfriday.order.infraestructure.client.model.CartModel;

@Alternative
@Priority(1)
@ApplicationScoped
public class CartClientGrpc {

    @GrpcClient("cart")
    private org.ecommerce.blackfriday.cart.grpc.CartService cartService;

    public Uni<CartModel> getCartEnabled (String customerId) {
        return cartService.getCartEnabled(makeRequest(customerId))
                .onItem()
                .transform(cartResponse -> CartClientMapper.fromResponseToEntity(cartResponse, customerId));
    }

    private org.ecommerce.blackfriday.cart.grpc.CartRequest makeRequest (String customerId) {
        return org.ecommerce.blackfriday.cart.grpc.CartRequest.newBuilder()
                .setCustomerId(customerId).build();
    }

}
