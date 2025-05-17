package org.ecommerce.blackfriday.stock.interfaces.rest.stock.exception;

import org.ecommerce.blackfriday.common.interfaces.rest.dto.ApiErrorResponse;
import org.ecommerce.blackfriday.stock.domain.model.exception.StockMustBePositive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StockGlobalExceptionHandler {

    @ExceptionHandler(StockNotFoundRestException.class)
    ResponseEntity<ApiErrorResponse> stockNotFoundException (StockNotFoundRestException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(StockMustBePositive.class)
    ResponseEntity<ApiErrorResponse> stockMustBePositive (StockMustBePositive ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleEnumConversionError(HttpMessageNotReadableException ex) {
        if (ex.getMessage().contains("StockOperation")) {
            return ResponseEntity.badRequest().body("Operación de stock no válida. Usa: INCREASE o DECREASE");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en el formato de la petición.");
    }

}
