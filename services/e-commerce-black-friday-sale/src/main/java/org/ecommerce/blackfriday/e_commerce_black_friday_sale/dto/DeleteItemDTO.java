package org.ecommerce.blackfriday.e_commerce_black_friday_sale.dto;

public class DeleteItemDTO {

    private String customerId;
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
