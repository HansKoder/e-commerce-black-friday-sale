package org.ecommerce.blackfriday.payment.domain.model.entity;

import org.ecommerce.blackfriday.common.domain.model.entity.AggregateRoot;
import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;
import org.ecommerce.blackfriday.payment.domain.model.exception.AmountMustBePositiveException;
import org.ecommerce.blackfriday.payment.domain.model.valueobject.OrderId;
import org.ecommerce.blackfriday.payment.domain.model.valueobject.PaymentId;
import org.ecommerce.blackfriday.payment.domain.model.valueobject.PaymentStatus;

import java.time.Instant;
import java.util.Objects;

public class Payment extends AggregateRoot<PaymentId> {

    private final OrderId orderId;
    private final Money amount;
    private final Instant timestamp;

    private PaymentStatus status;
    private String reason;

    // Business Rules
    public void mustBePositive () {
        if (Objects.isNull(amount)) return;

        if (!amount.isGreaterThanZero())
            throw new AmountMustBePositiveException("Amount must be positive");
    }

    public void updateStatusOnBaseResponse (String response) {
        reason = response;

        status = response.toUpperCase().contains("APPROVED")
                ? PaymentStatus.APPROVED
                : PaymentStatus.REJECTED;
    }

    // Constructor - Getters - Builder
    private Payment(Builder builder) {
        setId(builder.id);
        orderId = builder.orderId;
        status = builder.status;
        amount = builder.amount;
        reason = builder.reason;
        timestamp = builder.timestamp;
    }

    public Money getAmount() {
        return amount;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public String getReason() {
        return reason;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public static final class Builder {
        private Money amount;
        private OrderId orderId;
        private PaymentStatus status;
        private Instant timestamp;
        private String reason;
        private PaymentId id;

        private Builder() {
        }

        public static Builder aPayment() {
            return new Builder();
        }

        public Builder amount(Money amount) {
            this.amount = amount;
            return this;
        }

        public Builder orderId(OrderId orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder status(PaymentStatus status) {
            this.status = status;
            return this;
        }

        public Builder timestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder reason(String reason) {
            this.reason = reason;
            return this;
        }

        public Builder id(PaymentId id) {
            this.id = id;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }

}
