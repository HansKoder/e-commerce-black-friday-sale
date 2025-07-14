package org.ecommerce.blackfriday.order.infraestructure.persistence.order.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_items")
public class OrderItemEntity {

    @Id
    public UUID id;

    @Column(name = "product_id")
    public UUID productId;

    public BigDecimal price;

    @Column(name = "subtotal")
    public BigDecimal subTotal;

    @ManyToOne
    @JoinColumn(name = "order_id")
    public OrderEntity order;

    public Integer quantity;
}
