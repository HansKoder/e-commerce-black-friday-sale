package org.hans.fake.payment_gateway_service.card.infraestructure.adapter.input.rest;

import org.hans.fake.payment_gateway_service.card.application.service.PaymentAuthorizationService;
import org.hans.fake.payment_gateway_service.card.infraestructure.adapter.input.rest.dto.AuthorizeCardRequest;
import org.hans.fake.payment_gateway_service.card.infraestructure.adapter.input.rest.dto.AuthorizeCardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/payment-gateway")
public class PaymentRestController {

    private final PaymentAuthorizationService service;

    public PaymentRestController(PaymentAuthorizationService service) {
        this.service = service;
    }

    @PostMapping("/authorize-card")
    Mono<ResponseEntity<AuthorizeCardResponse>> authorizeCard (@RequestBody AuthorizeCardRequest request) {
        return service.handler(request).map(ResponseEntity::ok);
    }

}
