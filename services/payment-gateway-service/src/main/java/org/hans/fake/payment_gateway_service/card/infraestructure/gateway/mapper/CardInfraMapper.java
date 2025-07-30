package org.hans.fake.payment_gateway_service.card.infraestructure.gateway.mapper;

import org.hans.fake.payment_gateway_service.card.infraestructure.gateway.model.CardEntity;
import org.hans.fake.payment_gateway_service.card.model.entity.Card;
import org.hans.fake.payment_gateway_service.card.model.valueobject.CardId;
import org.hans.fake.payment_gateway_service.common.domain.model.valueobject.Money;

import java.util.UUID;

public class CardInfraMapper {

    public static CardEntity fromDomainToEntity (Card domain) {
        return new CardEntity(
                domain.getId().getValue().toString(),
                domain.getCardNumber(),
                domain.getBalance().getAmount(),
                domain.isMarkAsFraud(),
                domain.getExpiration()
        );
    }

    public static Card fromEntityToDomain (CardEntity entity) {
        return Card.Builder.aCard()
                .id(new CardId(UUID.fromString(entity.cardId())))
                .cardNumber(entity.number())
                .balance(new Money(entity.balance()))
                .markAsFraud(entity.fraud())
                .expiration(entity.expiration())
                .build();
    }

}
