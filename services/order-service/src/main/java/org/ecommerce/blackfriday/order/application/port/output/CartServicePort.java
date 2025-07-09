package org.ecommerce.blackfriday.order.application.port.output;

import io.smallrye.mutiny.Uni;
import org.ecommerce.blackfriday.order.infraestructure.client.dto.Cart;

public interface CartServicePort {
    Uni<Cart> getCart (String customerId);
}
