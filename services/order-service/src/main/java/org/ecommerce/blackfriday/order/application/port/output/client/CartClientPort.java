package org.ecommerce.blackfriday.order.application.port.output.client;

import io.smallrye.mutiny.Uni;
import org.ecommerce.blackfriday.order.infraestructure.client.model.CartModel;

public interface CartClientPort {
    Uni<CartModel> getCart (String customerId);
}
