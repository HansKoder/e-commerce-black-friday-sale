package org.ecommerce.blackfriday.payment.infrastructure.client.gateway.dto;

public record AuthorizeCardResponse(boolean success, String reason) {
}
