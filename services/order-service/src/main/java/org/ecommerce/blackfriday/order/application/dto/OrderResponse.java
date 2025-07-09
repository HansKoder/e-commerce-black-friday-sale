package org.ecommerce.blackfriday.order.application.dto;

public record OrderResponse (
        String trackId,
        String status,
        String message
) { }
