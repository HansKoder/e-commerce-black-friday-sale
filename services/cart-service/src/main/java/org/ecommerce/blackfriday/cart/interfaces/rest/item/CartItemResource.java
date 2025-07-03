package org.ecommerce.blackfriday.cart.interfaces.rest.item;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ecommerce.blackfriday.cart.application.service.RemoveCartItemService;
import org.ecommerce.blackfriday.cart.application.service.SaveCartItemService;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartItemId;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CustomerId;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.mapper.CartItemMapper;
import org.ecommerce.blackfriday.cart.interfaces.rest.item.dto.DeleteCartItemRequest;
import org.ecommerce.blackfriday.cart.interfaces.rest.item.dto.SaveCartItemRequest;

import java.util.UUID;

@Path("api/v2/cart/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartItemResource {

    private final SaveCartItemService saveCartItemService;
    private final RemoveCartItemService removeCartItemService;

    @Inject
    public CartItemResource(SaveCartItemService saveCartItemService, RemoveCartItemService removeCartItemService) {
        this.saveCartItemService = saveCartItemService;
        this.removeCartItemService = removeCartItemService;
    }

    @POST
    @Path("save")
    public Uni<Response> addCartItem (@Valid SaveCartItemRequest request) {
        return saveCartItemService
                .addCartItem(request.getCustomerId(), CartItemMapper.toDomain(request))
                .map(cart -> Response.status(Response.Status.CREATED).entity(cart).build());
    }

    @DELETE
    @Path("delete")
    public Uni<Response> removeCartItem (@Valid DeleteCartItemRequest deleteCartItemRequest) {
        return removeCartItemService.removeCartByCustomerId(
                new CustomerId(UUID.fromString(deleteCartItemRequest.getCustomerId())),
                new CartItemId(UUID.fromString(deleteCartItemRequest.getCartItemId()))
        ).onItem().transform(nothing -> Response.noContent().build());

    }


}
