package org.ecommerce.blackfriday.cart.infraestructure.grpc.cart;

import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.ecommerce.blackfriday.cart.application.service.GetCartEnabledService;
import org.ecommerce.blackfriday.cart.grpc.CartRequest;
import org.ecommerce.blackfriday.cart.grpc.CartResponse;
import org.ecommerce.blackfriday.cart.grpc.CartService;

@GrpcService
public class GrpcCartService implements CartService {

    private final GetCartEnabledService cartEnabledService;

    @Inject
    public GrpcCartService(GetCartEnabledService cartEnabledService) {
        this.cartEnabledService = cartEnabledService;
    }

    @Override
    public Uni<CartResponse> getCartEnabled(CartRequest request) {
        return cartEnabledService.handler(request.getCustomerId())
                .map(GrpcCartMapper::fromDomainToResponse);
    }
}
