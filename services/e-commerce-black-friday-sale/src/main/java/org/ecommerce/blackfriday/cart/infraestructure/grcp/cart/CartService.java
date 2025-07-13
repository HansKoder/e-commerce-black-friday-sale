package org.ecommerce.blackfriday.cart.infraestructure.grcp.cart;

import io.grpc.stub.StreamObserver;
import org.ecommerce.blackfriday.cart.grpc.CartRequest;
import org.ecommerce.blackfriday.cart.grpc.CartResponse;
import org.ecommerce.blackfriday.cart.grpc.CartServiceGrpc;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class CartService extends CartServiceGrpc.CartServiceImplBase {

    @Override
    public void getCartEnabled(CartRequest request, StreamObserver<CartResponse> responseObserver) {
        super.getCartEnabled(request, responseObserver);
    }
}
