package org.ecommerce.blackfriday.stock.interfaces.rest.stock.mapper;

import org.ecommerce.blackfriday.stock.domain.model.entity.Stock;
import org.ecommerce.blackfriday.stock.interfaces.rest.stock.dto.response.GetStockResponse;

public class StockRestMapper {
    public static GetStockResponse toResponse (Stock domain) {
        return new GetStockResponse(domain.getProductId().getValue().toString(), domain.getAmount());
    }
}
