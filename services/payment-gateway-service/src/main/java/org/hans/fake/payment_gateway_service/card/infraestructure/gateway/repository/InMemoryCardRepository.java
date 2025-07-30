package org.hans.fake.payment_gateway_service.card.infraestructure.gateway.repository;

import org.hans.fake.payment_gateway_service.card.infraestructure.gateway.model.CardEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryCardRepository {

    private final Map<String, CardEntity> cards = new ConcurrentHashMap<>();

    public InMemoryCardRepository() {
        load();
    }

    private void load () {
        cards.put(getCardExpired().number(), getCardExpired());
        cards.put(getCardWithFunds().number(), getCardWithFunds());
    }

    private CardEntity getCardWithFunds () {
        String cardId = "9ebfcae5-8801-44c7-9931-36b36b0704ca";

        return new CardEntity(
                cardId,
                "9ebfcae5",
                BigDecimal.valueOf(5000),
                false,
                YearMonth.now().plusYears(2));
    }

    private CardEntity getCardExpired () {
        String cardId = "0f7a8b93-3406-4deb-b438-9f6f5131df87";

        return new CardEntity(
                cardId,
                "0f7a8b93",
                BigDecimal.valueOf(5000),
                false,
                YearMonth.now().minusMonths(2));
    }

    public Mono<CardEntity> findCard (String accountNumber) {
        return Mono.justOrEmpty(cards.get(accountNumber));
    }


}
