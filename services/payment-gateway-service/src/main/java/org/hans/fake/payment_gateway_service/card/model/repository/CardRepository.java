package org.hans.fake.payment_gateway_service.card.model.repository;

import org.hans.fake.payment_gateway_service.card.model.entity.Card;
import reactor.core.publisher.Mono;

public interface CardRepository {

    Mono<Card> getCardByCardNumber (String cardNumber);

}
