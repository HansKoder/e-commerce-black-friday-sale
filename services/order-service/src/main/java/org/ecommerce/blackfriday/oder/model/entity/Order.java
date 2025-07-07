package org.ecommerce.blackfriday.oder.model.entity;

import org.ecommerce.blackfriday.common.domain.model.entity.AggregateRoot;
import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;
import org.ecommerce.blackfriday.oder.model.valueobject.OrderId;

import java.util.List;
import java.util.UUID;

public class Order extends AggregateRoot<OrderId> {
    private final List<OrderItem> items;
    private final Customer customer;
    private final Money total;

    private Order(List<OrderItem> items, Customer customer, Money total) {
        this.setId(new OrderId(UUID.randomUUID()));
        this.items = items;
        this.customer = customer;
        this.total = total;
    }

    public static Order create (List<OrderItem> items, Customer customer, Money total) {
        return new Order(items, customer, total);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Money getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "items=" + items +
                ", customer=" + customer +
                ", total=" + total +
                '}';
    }
}
