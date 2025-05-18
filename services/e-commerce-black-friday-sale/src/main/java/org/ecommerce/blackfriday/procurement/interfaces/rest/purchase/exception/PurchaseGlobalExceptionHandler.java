package org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.exception;

import org.ecommerce.blackfriday.common.interfaces.rest.dto.ApiErrorResponse;
import org.ecommerce.blackfriday.procurement.domain.model.exception.InvalidStatusPurchaseDomainException;
import org.ecommerce.blackfriday.procurement.domain.model.valueobject.PurchaseItemId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PurchaseGlobalExceptionHandler {

    @ExceptionHandler(PurchaseNotFoundRestException.class)
    ResponseEntity<ApiErrorResponse> purchaseNotFoundRestException (PurchaseNotFoundRestException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(InvalidStatusPurchaseDomainException.class)
    ResponseEntity<ApiErrorResponse> invalidStatusPurchaseDomainException (InvalidStatusPurchaseDomainException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler(PurchaseItemInvalidException.class)
    ResponseEntity<ApiErrorResponse> purchaseItemInvalidException (PurchaseItemInvalidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }
}
