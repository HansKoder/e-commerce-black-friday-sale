package org.ecommerce.blackfriday.cart.infraestructure.grcp.cart;

import io.grpc.stub.StreamObserver;
import org.ecommerce.blackfriday.cart.application.service.GetCartEnabledService;
import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.grpc.CartRequest;
import org.ecommerce.blackfriday.cart.grpc.CartResponse;
import org.ecommerce.blackfriday.cart.grpc.CartServiceGrpc;
import org.ecommerce.blackfriday.cart.infraestructure.CartLogger;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class CartService extends CartServiceGrpc.CartServiceImplBase {

    private final GetCartEnabledService cartEnabledService;

    public CartService(GetCartEnabledService cartEnabledService) {
        this.cartEnabledService = cartEnabledService;
    }

    @Override
    public void getCartEnabled(CartRequest request, StreamObserver<CartResponse> responseObserver) {
        String customerId = request.getCustomerId();
        CartLogger.info("[CART] (grpc) getCartEnabled, customer:{}", customerId);
        Cart domain = cartEnabledService.handler(customerId);
        CartResponse response = CartServiceMapper.fromDomainToResponse(domain);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
