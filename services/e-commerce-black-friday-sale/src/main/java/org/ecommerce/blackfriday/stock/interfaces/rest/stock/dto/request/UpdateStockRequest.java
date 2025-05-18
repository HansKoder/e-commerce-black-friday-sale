package org.ecommerce.blackfriday.stock.interfaces.rest.stock.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.ecommerce.blackfriday.stock.domain.model.enums.AdjustmentReason;
import org.ecommerce.blackfriday.stock.domain.model.enums.StockOperation;

public record UpdateStockRequest(
        @NotNull(message = "ProductId is mandatory")
        @NotEmpty(message = "ProductId never must be empty")
        String productId,
        @Min(value = 1, message = "The value must be greater or equal to ONE (1)")
        int value,
        @NotNull(message = "Stock Operation is mandatory")
        StockOperation operation,
        AdjustmentReason reason
) {
        @Override
        public String toString() {
                return "UpdateStockRequest{" +
                        "productId='" + productId + '\'' +
                        ", value=" + value +
                        ", operation=" + operation +
                        ", reason=" + reason +
                        '}';
        }
}
