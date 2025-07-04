package org.ecommerce.blackfriday.cart.interfaces.rest.common.exception;

import org.ecommerce.blackfriday.cart.domain.model.exception.CartItemNotFoundDomainException;
import org.ecommerce.blackfriday.common.interfaces.rest.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CartGlobalExceptionHandler {

    @ExceptionHandler(CartByCustomerNotFoundRestException.class)
    public ResponseEntity<ApiErrorResponse> cartByCustomerIdNotFound (CartByCustomerNotFoundRestException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(CartItemNotFoundDomainException.class)
    public ResponseEntity<ApiErrorResponse> cartItemNotFoundException (CartItemNotFoundDomainException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(RateLimitExceededException.class)
    public ResponseEntity<ApiErrorResponse> rateLimitExceededException (RateLimitExceededException ex) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(new ApiErrorResponse(HttpStatus.TOO_MANY_REQUESTS.value(), ex.getMessage()));
    }

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<ApiErrorResponse> timeoutException (TimeoutException ex) {
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                .body(new ApiErrorResponse(HttpStatus.REQUEST_TIMEOUT.value(), ex.getMessage()));
    }


}
