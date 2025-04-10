package org.ecommerce.blackfriday.shopping.cart.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class DeleteItemDTO {

    @NotNull(message = "CustomerId cannot be null")
    @NotEmpty(message = "CustomerId cannot be empty")
    private String customerId;

    @NotNull(message = "ItemId cannot be null")
    private Long itemId;

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
}
