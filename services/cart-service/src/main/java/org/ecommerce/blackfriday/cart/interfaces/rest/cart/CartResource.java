package org.ecommerce.blackfriday.cart.interfaces.rest.cart;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ecommerce.blackfriday.cart.application.service.GetCartByCustomerService;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CustomerId;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.mapper.CartMapper;
import org.jboss.resteasy.reactive.RestPath;

import java.util.UUID;

@Path("api/v2/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {

    private final GetCartByCustomerService getCartByCustomerService;

    @Inject
    public CartResource(GetCartByCustomerService getCartByCustomerService) {
        this.getCartByCustomerService = getCartByCustomerService;
    }

    @GET
    @Path("customer/{customerId}")
    public Uni<Response> getCartByCustomerId (@RestPath("customerId") String customerId) {
        return getCartByCustomerService.getCart(new CustomerId(UUID.fromString(customerId)))
                .map(cart -> CartMapper.toDto(cart, customerId))
                .map(response -> Response.ok(response).build());
    }
}
