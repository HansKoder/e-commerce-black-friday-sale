package org.ecommerce.blackfriday.e_commerce_black_friday_sale.dto;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartDTO {

    private String customerId;
    private List<ItemDTO> items;
    private BigDecimal total;

    public ShoppingCartDTO(List<ItemDTO> items, String customerId, BigDecimal total) {
        this.items = items;
        this.customerId = customerId;
        this.total = total;
    }

    public ShoppingCartDTO() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }


    public static final class ShoppingCartDTOBuilder {
        private String customerId;
        private List<ItemDTO> items;
        private BigDecimal total;

        private ShoppingCartDTOBuilder() {
        }

        public static ShoppingCartDTOBuilder aShoppingCartDTO() {
            return new ShoppingCartDTOBuilder();
        }

        public ShoppingCartDTOBuilder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public ShoppingCartDTOBuilder withItems(List<ItemDTO> items) {
            this.items = items;
            return this;
        }

        public ShoppingCartDTOBuilder withTotal(BigDecimal total) {
            this.total = total;
            return this;
        }

        public ShoppingCartDTO build() {
            ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
            shoppingCartDTO.setCustomerId(customerId);
            shoppingCartDTO.setItems(items);
            shoppingCartDTO.setTotal(total);
            return shoppingCartDTO;
        }
    }
}
