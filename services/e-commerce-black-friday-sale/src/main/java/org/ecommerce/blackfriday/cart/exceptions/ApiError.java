package org.ecommerce.blackfriday.cart.exceptions;

public record ApiError(int status, String message) {
}
