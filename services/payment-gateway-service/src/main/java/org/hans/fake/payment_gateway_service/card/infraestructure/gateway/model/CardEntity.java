package org.hans.fake.payment_gateway_service.card.infraestructure.gateway.model;

import java.math.BigDecimal;
import java.time.YearMonth;

public record CardEntity (
        String cardId,
        String number,
        BigDecimal balance,
        boolean fraud,
        YearMonth expiration
) { }
