package org.ecommerce.blackfriday.cart.interfaces.rest.quantity;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ecommerce.blackfriday.cart.application.service.DecrementQuantityService;
import org.ecommerce.blackfriday.cart.application.service.IncrementQuantityService;
import org.ecommerce.blackfriday.cart.application.service.UpdateQuantityService;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartItemId;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CustomerId;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.mapper.CartMapper;
import org.ecommerce.blackfriday.cart.interfaces.rest.quantity.dto.DecrementQuantityRequest;
import org.ecommerce.blackfriday.cart.interfaces.rest.quantity.dto.IncrementQuantityRequest;
import org.ecommerce.blackfriday.cart.interfaces.rest.quantity.dto.UpdateQuantityRequest;
import java.util.UUID;

@Path("api/v2/cart/item/quantity")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuantityResource {

    private final IncrementQuantityService incrementQuantityService;
    private final DecrementQuantityService decrementQuantityService;
    private final UpdateQuantityService updateQuantityService;

    @Inject
    public QuantityResource(IncrementQuantityService incrementQuantityService, DecrementQuantityService decrementQuantityService, UpdateQuantityService updateQuantityService) {
        this.incrementQuantityService = incrementQuantityService;
        this.decrementQuantityService = decrementQuantityService;
        this.updateQuantityService = updateQuantityService;
    }

    @PUT
    @Path("increment")
    public Uni<Response> increment (@Valid IncrementQuantityRequest request) {
        return incrementQuantityService
                .incrementQuantity(
                        new CustomerId(UUID.fromString(request.customerId())),
                        new CartItemId(UUID.fromString(request.cartItemId()))
                )
                .map(cart -> CartMapper.toDto(cart, request.customerId()))
                .map(response -> Response.ok(response).build());
    }

    @PUT
    @Path("decrement")
    public Uni<Response> decrement (@Valid DecrementQuantityRequest request) {
        return decrementQuantityService.decrement(
                        new CustomerId(UUID.fromString(request.customerId())),
                        new CartItemId(UUID.fromString(request.cartItemId()))
                )
                .map(cart -> CartMapper.toDto(cart, request.customerId()))
                .map(response -> Response.ok(response).build());
    }

    @PUT
    @Path("update")
    public Uni<Response> update (@Valid UpdateQuantityRequest request) {
        return updateQuantityService.update(
                        new CustomerId(UUID.fromString(request.customerId())),
                        new CartItemId(UUID.fromString(request.cartItemId())),
                        request.quantity()
                ).map(cart -> CartMapper.toDto(cart, request.customerId()))
                .map(response -> Response.ok(response).build());
    }

}
