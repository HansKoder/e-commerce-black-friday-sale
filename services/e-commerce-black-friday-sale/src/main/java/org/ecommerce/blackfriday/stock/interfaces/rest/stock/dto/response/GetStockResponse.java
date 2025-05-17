package org.ecommerce.blackfriday.stock.interfaces.rest.stock.dto.response;

public record GetStockResponse (
        String productId,
        int value
) { }
