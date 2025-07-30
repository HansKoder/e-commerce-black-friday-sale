package org.hans.fake.payment_gateway_service.card.infraestructure.adapter.input.rest.dto;

public record AuthorizeCardResponse (
        boolean success,
        String message
) { }
