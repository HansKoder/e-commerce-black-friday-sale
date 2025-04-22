package org.ecommerce.blackfriday.product.managment.interfaces.rest.product.exception;

import org.ecommerce.blackfriday.common.interfaces.rest.dto.ApiErrorResponse;
import org.ecommerce.blackfriday.product.managment.domain.exception.ProductDomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductGlobalExceptionHandler {

    @ExceptionHandler(ProductDomainException.class)
    ResponseEntity<ApiErrorResponse> productDomainException (ProductDomainException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }

}
