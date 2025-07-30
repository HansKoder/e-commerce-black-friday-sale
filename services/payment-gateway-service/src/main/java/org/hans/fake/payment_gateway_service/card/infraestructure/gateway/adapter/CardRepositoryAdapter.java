package org.hans.fake.payment_gateway_service.card.infraestructure.gateway.adapter;

import org.hans.fake.payment_gateway_service.card.infraestructure.gateway.mapper.CardInfraMapper;
import org.hans.fake.payment_gateway_service.card.infraestructure.gateway.repository.InMemoryCardRepository;
import org.hans.fake.payment_gateway_service.card.model.entity.Card;
import org.hans.fake.payment_gateway_service.card.model.repository.CardRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CardRepositoryAdapter implements CardRepository {

    private final InMemoryCardRepository repository;

    public CardRepositoryAdapter(InMemoryCardRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Card> getCardByCardNumber(String cardNumber) {
        return repository.findCard(cardNumber)
                .map(CardInfraMapper::fromEntityToDomain);
    }
}
