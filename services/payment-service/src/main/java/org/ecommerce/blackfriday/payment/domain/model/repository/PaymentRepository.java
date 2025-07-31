package org.ecommerce.blackfriday.payment.domain.model.repository;

import io.smallrye.mutiny.Uni;
import org.ecommerce.blackfriday.payment.domain.model.entity.Payment;

public interface PaymentRepository {
    Uni<Payment> save(Payment domain);
}
