package org.hans.fake.payment_gateway_service.card.infraestructure.adapter.input.rest;

import org.hans.fake.payment_gateway_service.card.infraestructure.adapter.input.rest.dto.AuthorizeCardResponse;
import org.hans.fake.payment_gateway_service.card.model.exception.CardExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class PaymentExceptionGlobal {

    @ExceptionHandler(CardExpiredException.class)
    public Mono<ResponseEntity<AuthorizeCardResponse>> handlerCardExpired (CardExpiredException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new AuthorizeCardResponse(false, ex.getMessage())));
    }

}
