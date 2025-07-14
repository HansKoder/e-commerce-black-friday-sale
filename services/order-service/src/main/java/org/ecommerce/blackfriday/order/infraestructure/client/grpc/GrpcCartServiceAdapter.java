package org.ecommerce.blackfriday.order.infraestructure.client.grpc;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.ecommerce.blackfriday.order.application.port.output.client.CartClientPort;
import org.ecommerce.blackfriday.order.infraestructure.client.model.CartModel;

@ApplicationScoped
public class GrpcCartServiceAdapter implements CartClientPort {

    private final CartClientGrpc cartClientGrpc;

    public GrpcCartServiceAdapter(CartClientGrpc cartClientGrpc) {
        this.cartClientGrpc = cartClientGrpc;
    }

    @Override
    public Uni<CartModel> getCart(String customerId) {
        return cartClientGrpc.getCartEnabled(customerId);
    }
}
