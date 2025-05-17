package org.ecommerce.blackfriday.stock.domain.model.entity;

import org.ecommerce.blackfriday.common.domain.model.entity.BaseEntity;
import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductId;
import org.ecommerce.blackfriday.stock.domain.model.exception.StockMustBePositive;
import org.ecommerce.blackfriday.stock.domain.model.valueobject.StockId;

import java.util.UUID;

public class Stock extends BaseEntity<StockId> {
    private final ProductId productId;
    private int amount;

    private Stock(StockId stockId, int amount, ProductId productId) {
        this.amount = amount;
        this.productId = productId;
        super.setId(stockId);
    }

    public ProductId getProductId() {
        return productId;
    }

    public int getAmount() {
        return amount;
    }

    public static Stock create (ProductId productId) {
        return new Stock(new StockId(UUID.randomUUID()), 0, productId);
    }

    public static Stock rebuild (StockId stockId, int amount, ProductId productId) {
        return new Stock(stockId, amount, productId);
    }

    public void increase (int value) {
        System.out.println("[DOMAIN] (increase) params amount {" + amount + "}, increase this value {" + value + "}");
        if (value < 1)
            throw new StockMustBePositive(productId.getValue().toString());

        this.amount += value;
    }

    public void decrease (int value) {
        System.out.println("[DOMAIN] (decrease) params amount {" + amount + "}, increase this value {" + value + "}");
        if (value < 1 || (amount - value) < 1)
            throw new StockMustBePositive(productId.getValue().toString());

        this.amount -= value;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "productId=" + productId +
                ", amount=" + amount +
                '}';
    }
}
