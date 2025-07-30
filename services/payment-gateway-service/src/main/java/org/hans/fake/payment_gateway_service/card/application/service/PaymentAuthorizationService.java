package org.hans.fake.payment_gateway_service.card.application.service;

import org.hans.fake.payment_gateway_service.card.infraestructure.adapter.input.rest.dto.AuthorizeCardRequest;
import org.hans.fake.payment_gateway_service.card.infraestructure.adapter.input.rest.dto.AuthorizeCardResponse;
import org.hans.fake.payment_gateway_service.card.model.repository.CardRepository;
import org.hans.fake.payment_gateway_service.common.domain.model.valueobject.Money;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PaymentAuthorizationService {

    private final CardRepository cardRepository;

    public PaymentAuthorizationService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Mono<AuthorizeCardResponse> handler (AuthorizeCardRequest request) {
        return cardRepository.getCardByCardNumber(request.accountNumber())
                .map(card ->  {
                    card.authorizeCard(new Money(request.amount()));
                    return card;
                })
                .map(card -> new AuthorizeCardResponse(true, "APPROVED"));
    }


}
