package org.ecommerce.blackfriday.procurement.interfaces.rest.purchase;

import jakarta.validation.Valid;
import org.ecommerce.blackfriday.procurement.application.service.CreatePurchaseService;
import org.ecommerce.blackfriday.procurement.domain.model.entity.Purchase;
import org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.dto.request.CreatePurchaseRequest;
import org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.dto.response.GetPurchaseResponse;
import org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.mapper.PurchaseRestMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/purchase")
@Validated
public class PurchaseRestController {

    private final CreatePurchaseService createPurchaseService;

    public PurchaseRestController(CreatePurchaseService createPurchaseService) {
        this.createPurchaseService = createPurchaseService;
    }

    @PostMapping("/create")
    public ResponseEntity<GetPurchaseResponse> create (@Valid @RequestBody CreatePurchaseRequest request) {
        Purchase domain = createPurchaseService.handler(PurchaseRestMapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(PurchaseRestMapper.toResponse(domain));
    }

}
