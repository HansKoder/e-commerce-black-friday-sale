package org.ecommerce.blackfriday.order.infraestructure.client;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestPath;

@RegisterRestClient(baseUri = "http://localhost:9081")
@Path("/api/v2/cart")
public interface CartClient {

    @GET
    @Path("/customer/{customerId}")
    Uni<Response> getCartByCustomerId (@RestPath("customerId") String customerId);
}
