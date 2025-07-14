package org.ecommerce.blackfriday.stock.interfaces.rest.stock;

import org.ecommerce.blackfriday.stock.application.usecase.GetStockUseCase;
import org.ecommerce.blackfriday.stock.infrastructure.StockLogger;
import org.ecommerce.blackfriday.stock.interfaces.rest.stock.dto.response.GetStockResponse;
import org.ecommerce.blackfriday.stock.interfaces.rest.stock.mapper.StockRestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/stock")
public class StockRestController {

    private final GetStockUseCase getStockUseCase;

    public StockRestController(GetStockUseCase getStockUseCase) {
        this.getStockUseCase = getStockUseCase;
    }

    @GetMapping("/product/{productId}")
    ResponseEntity<GetStockResponse> getByProductId (@PathVariable("productId") String productId) {
        StockLogger.info("[API] (get stock productId) param productId {}", productId);
        return ResponseEntity.ok(StockRestMapper.toResponse(getStockUseCase.handler(productId)));
    }

}
