package org.hans.fake.payment_gateway_service.card.model.entity;

import org.hans.fake.payment_gateway_service.card.model.exception.CardExpiredException;
import org.hans.fake.payment_gateway_service.card.model.valueobject.CardId;
import org.hans.fake.payment_gateway_service.common.domain.model.entity.AggregateRoot;
import org.hans.fake.payment_gateway_service.common.domain.model.valueobject.Money;

import java.time.YearMonth;

public class Card extends AggregateRoot<CardId> {

    private String cardNumber;
    private Money balance;
    private boolean markAsFraud;
    private YearMonth expiration;

    // Cons - Getter
    private Card(Builder builder) {
        setId(builder.id);
        cardNumber = builder.cardNumber;
        balance = builder.balance;
        markAsFraud = builder.markAsFraud;
        expiration = builder.expiration;
    }

    public Money getBalance() {
        return balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public boolean isMarkAsFraud() {
        return markAsFraud;
    }

    public YearMonth getExpiration() {
        return expiration;
    }

    // Business Rules
    public void authorizeCard (Money amount) {
        isExpired();
        insufficientFounds(amount);
        fraudSuspected();
        withdraw();
    }

    private void isExpired () {
        if (expiration.isBefore(YearMonth.now()))
            throw new CardExpiredException("CARD IS EXPIRED");
    }
    private void insufficientFounds (Money amount) {}
    private void fraudSuspected () {}

    private void withdraw () {}


    // Builder
    public static final class Builder {
        private Money balance;
        private String cardNumber;
        private boolean markAsFraud;
        private CardId id;
        private YearMonth expiration;

        private Builder() {
        }

        public static Builder aCard() {
            return new Builder();
        }

        public Builder balance(Money balance) {
            this.balance = balance;
            return this;
        }

        public Builder cardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public Builder markAsFraud(boolean markAsFraud) {
            this.markAsFraud = markAsFraud;
            return this;
        }

        public Builder expiration(YearMonth expiration) {
            this.expiration = expiration;
            return this;
        }

        public Builder id(CardId id) {
            this.id = id;
            return this;
        }

        public Card build() {
            return new Card(this);
        }
    }
}
