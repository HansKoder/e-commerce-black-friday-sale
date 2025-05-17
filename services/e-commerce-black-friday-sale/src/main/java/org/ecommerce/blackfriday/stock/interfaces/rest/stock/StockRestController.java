package org.ecommerce.blackfriday.stock.interfaces.rest.stock;

import jakarta.validation.Valid;
import org.ecommerce.blackfriday.stock.application.usecase.AdjustStockUseCase;
import org.ecommerce.blackfriday.stock.application.usecase.GetStockUseCase;
import org.ecommerce.blackfriday.stock.infrastructure.StockLogger;
import org.ecommerce.blackfriday.stock.interfaces.rest.stock.dto.request.UpdateStockRequest;
import org.ecommerce.blackfriday.stock.interfaces.rest.stock.dto.response.GetStockResponse;
import org.ecommerce.blackfriday.stock.interfaces.rest.stock.mapper.StockRestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/stock")
@Validated
public class StockRestController {

    private final AdjustStockUseCase adjustStockUseCase;
    private final GetStockUseCase getStockUseCase;

    public StockRestController(AdjustStockUseCase adjustStockUseCase, GetStockUseCase getStockUseCase) {
        this.adjustStockUseCase = adjustStockUseCase;
        this.getStockUseCase = getStockUseCase;
    }

    @GetMapping("/product/{productId}")
    ResponseEntity<GetStockResponse> getByProductId (@PathVariable("productId") String productId) {
        StockLogger.info("[API] (get stock productId) param productId {}", productId);
        return ResponseEntity.ok(StockRestMapper.toResponse(getStockUseCase.handler(productId)));
    }

    @PostMapping("/update")
    ResponseEntity<GetStockResponse> update (@Valid @RequestBody UpdateStockRequest request) {
        StockLogger.info("[API] (update stock) param request {}", request);
        return ResponseEntity.ok(StockRestMapper.toResponse(adjustStockUseCase.handler(request)));
    }
}
