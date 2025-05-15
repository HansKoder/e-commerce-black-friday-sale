package org.ecommerce.blackfriday.common.interfaces.rest.exception;

import org.ecommerce.blackfriday.common.domain.model.exception.InvalidQuantityDomainException;
import org.ecommerce.blackfriday.common.interfaces.rest.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiErrorResponse> handlerNullPointerException (NullPointerException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }

    @ExceptionHandler(InvalidQuantityDomainException.class)
    public ResponseEntity<ApiErrorResponse> handlerInvalidQuantityException (InvalidQuantityDomainException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<ApiErrorResponse> handlerTimeoutException (TimeoutException ex) {
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                .body(new ApiErrorResponse(HttpStatus.GATEWAY_TIMEOUT.value(), ex.getMessage()));
    }

}
