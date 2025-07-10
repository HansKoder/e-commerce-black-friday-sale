package org.ecommerce.blackfriday.order.infraestructure.persistence.order.adapter;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ecommerce.blackfriday.order.application.port.output.repository.OrderRepository;
import org.ecommerce.blackfriday.order.domain.model.entity.Order;
import org.ecommerce.blackfriday.order.infraestructure.persistence.order.mapper.OrderDataAccessMapper;
import org.ecommerce.blackfriday.order.infraestructure.persistence.order.repository.PanacheOrderRepository;

@ApplicationScoped
public class PanacheOrderAdapter implements OrderRepository {
    private final PanacheOrderRepository repository;

    @Inject
    public PanacheOrderAdapter(PanacheOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Uni<Order> save(Order domain) {
        return Panache.withTransaction(() ->
                repository.persist(OrderDataAccessMapper.orderFromDomainToEntity(domain))
                        .map(OrderDataAccessMapper::orderFromEntityToDomain)
        );
    }
}
