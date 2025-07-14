package org.ecommerce.blackfriday.order.infraestructure.persistence.order.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.ecommerce.blackfriday.order.infraestructure.persistence.order.entity.OrderEntity;

import java.util.UUID;

@ApplicationScoped
public class PanacheOrderRepository implements PanacheRepositoryBase<OrderEntity, UUID> { }
