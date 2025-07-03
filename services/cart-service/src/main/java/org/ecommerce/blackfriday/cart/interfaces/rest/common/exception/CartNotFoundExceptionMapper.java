package org.ecommerce.blackfriday.cart.interfaces.rest.common.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.ecommerce.blackfriday.common.interfaces.rest.dto.ApiErrorResponse;
import org.jboss.resteasy.reactive.RestResponse;

@Provider
public class CartNotFoundExceptionMapper implements ExceptionMapper<CartNotFoundException> {

    @Override
    public Response toResponse(CartNotFoundException e) {
        return Response
                .status(RestResponse.Status.NOT_FOUND)
                .entity(new ApiErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage()))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
