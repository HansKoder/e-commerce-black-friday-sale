package org.ecommerce.blackfriday.shopping.cart.exceptions;

public record ApiError(int status, String message) {
}
