package org.ecommerce.blackfriday.order.infraestructure.persistence.order.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    public UUID id;

    @Column(name = "customer_id")
    public UUID customerId;

    public BigDecimal total;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    public List<OrderItemEntity> items = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    public OrderStatusJpa status;

    @Override
    public String toString() {
        return "OrderEntity{" +
                "customerId=" + customerId +
                ", id=" + id +
                ", total=" + total +
                ", items=" + items +
                ", status=" + status +
                '}';
    }
}
