package org.hans.fake.payment_gateway_service.card.infraestructure.adapter.input.rest.dto;

import java.math.BigDecimal;

public record AuthorizeCardRequest (
        String accountNumber,
        BigDecimal amount
) {}
