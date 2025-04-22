package org.ecommerce.blackfriday.cart.model;

import java.math.BigDecimal;

public class Item {

    private Long itemId;
    private BigDecimal price;
    private int cant;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public BigDecimal getAmount() {
        return price.multiply(BigDecimal.valueOf(cant));
    }

    @Override
    public String toString() {
        return "DetailShoppingCart{" +
                "itemId=" + itemId +
                ", price=" + price +
                ", cant=" + cant +
                '}';
    }
}
