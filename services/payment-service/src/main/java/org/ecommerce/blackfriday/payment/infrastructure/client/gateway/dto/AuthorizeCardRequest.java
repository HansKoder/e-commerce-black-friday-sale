package org.ecommerce.blackfriday.payment.infrastructure.client.gateway.dto;

import java.math.BigDecimal;

public record AuthorizeCardRequest(
        String accountNumber,
        BigDecimal amount
) { }
