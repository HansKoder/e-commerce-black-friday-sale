package org.ecommerce.blackfriday.cart.interfaces.rest.item;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ecommerce.blackfriday.cart.application.service.SaveCartItemService;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.mapper.CartItemMapper;
import org.ecommerce.blackfriday.cart.interfaces.rest.item.dto.SaveCartItemRequest;

@Path("api/v2/cart/items")
@Produces(MediaType.APPLICATION_JSON)
public class CartItemResource {

    private final SaveCartItemService saveCartItemService;

    @Inject
    public CartItemResource(SaveCartItemService saveCartItemService) {
        this.saveCartItemService = saveCartItemService;
    }

    @POST
    @Path("save")
    public Uni<Response> addCartItem (@Valid SaveCartItemRequest request) {
        return saveCartItemService
                .addCartItem(request.getCustomerId(), CartItemMapper.toDomain(request))
                .map(cart -> Response.status(Response.Status.CREATED).entity(cart).build());
    }
}
