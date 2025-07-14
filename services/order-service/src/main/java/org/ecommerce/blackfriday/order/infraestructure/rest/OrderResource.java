package org.ecommerce.blackfriday.order.infraestructure.rest;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.ecommerce.blackfriday.order.application.dto.CreateOrderCommand;
import org.ecommerce.blackfriday.order.application.dto.OrderResponse;
import org.ecommerce.blackfriday.order.application.port.input.service.OrderApplicationService;

@Path(value = "api/v2/order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    private final OrderApplicationService orderApplicationService;

    @Inject
    public OrderResource(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    @POST
    @Path(value = "create-order")
    public Uni<OrderResponse> createOrder (CreateOrderCommand createOrderCommand) {
        Log.infof("[RESOURCE] create Order, payload: %s",createOrderCommand);
        return orderApplicationService.createOrder(createOrderCommand);
    }

}
