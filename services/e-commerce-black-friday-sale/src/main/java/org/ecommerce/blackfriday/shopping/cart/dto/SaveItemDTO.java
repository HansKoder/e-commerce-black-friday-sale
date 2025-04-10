package org.ecommerce.blackfriday.shopping.cart.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class SaveItemDTO {

    @NotNull(message = "CustomerId cannot be null")
    @NotEmpty(message = "CustomerId cannot be empty")
    private String customerId;

    @NotNull(message = "ItemId cannot be null")
    private Long itemId;

    @Min(value = 0, message = "Cant must be greater or equal to zero")
    private int cant;

    @DecimalMin(value = "0.0", message = "Price must be greater or equal to ZERO")
    private BigDecimal price;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "AddItemDTO{" +
                "customerId='" + customerId + '\'' +
                ", itemId=" + itemId +
                ", cant=" + cant +
                ", price=" + price +
                '}';
    }
}
