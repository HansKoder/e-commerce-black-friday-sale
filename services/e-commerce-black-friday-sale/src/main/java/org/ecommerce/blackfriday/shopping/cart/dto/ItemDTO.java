package org.ecommerce.blackfriday.shopping.cart.dto;

import java.math.BigDecimal;

public class ItemDTO {

    private Long itemId;
    private BigDecimal price;
    private int cant;
    private BigDecimal amount;

    public ItemDTO(Long itemId, BigDecimal price, int cant, BigDecimal amount) {
        this.itemId = itemId;
        this.price = price;
        this.cant = cant;
        this.amount = amount;
    }

    public ItemDTO() {
    }

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
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public static final class ItemDTOBuilder {
        private Long itemId;
        private BigDecimal price;
        private int cant;
        private BigDecimal amount;

        private ItemDTOBuilder() {
        }

        public static ItemDTOBuilder anItemDTO() {
            return new ItemDTOBuilder();
        }

        public ItemDTOBuilder withItemId(Long itemId) {
            this.itemId = itemId;
            return this;
        }

        public ItemDTOBuilder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ItemDTOBuilder withCant(int cant) {
            this.cant = cant;
            return this;
        }

        public ItemDTOBuilder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public ItemDTO build() {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setItemId(itemId);
            itemDTO.setPrice(price);
            itemDTO.setCant(cant);
            itemDTO.setAmount(amount);
            return itemDTO;
        }
    }
}
