package org.ecommerce.blackfriday.procurement.interfaces.rest.purchase;

import jakarta.validation.Valid;
import org.ecommerce.blackfriday.procurement.application.service.CanceledPurchaseService;
import org.ecommerce.blackfriday.procurement.application.service.CreatePurchaseService;
import org.ecommerce.blackfriday.procurement.application.service.GetPurchaseService;
import org.ecommerce.blackfriday.procurement.domain.model.entity.Purchase;
import org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.dto.request.CanceledPurchaseRequest;
import org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.dto.request.CreatePurchaseRequest;
import org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.dto.response.GetPurchaseResponse;
import org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.mapper.PurchaseRestMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/purchase")
@Validated
public class PurchaseRestController {

    private final CreatePurchaseService createPurchaseService;
    private final CanceledPurchaseService canceledPurchaseService;
    private final GetPurchaseService getPurchaseService;

    public PurchaseRestController(CreatePurchaseService createPurchaseService, CanceledPurchaseService canceledPurchaseService, GetPurchaseService getPurchaseService) {
        this.createPurchaseService = createPurchaseService;
        this.canceledPurchaseService = canceledPurchaseService;
        this.getPurchaseService = getPurchaseService;
    }

    @PostMapping("/create")
    public ResponseEntity<GetPurchaseResponse> create (@Valid @RequestBody CreatePurchaseRequest request) {
        Purchase domain = createPurchaseService.handler(PurchaseRestMapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(PurchaseRestMapper.toResponse(domain));
    }

    @PutMapping("/cancel")
    public ResponseEntity<GetPurchaseResponse> canceled (@Valid @RequestBody CanceledPurchaseRequest request) {
        Purchase domain = canceledPurchaseService.handler(UUID.fromString(request.purchaseId()), request.comment());
        return ResponseEntity.status(HttpStatus.OK)
                .body(PurchaseRestMapper.toResponse(domain));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<GetPurchaseResponse> findById (@PathVariable("uuid") String uuid) {
        return ResponseEntity.ok(PurchaseRestMapper.toResponse(getPurchaseService.handler(uuid)));
    }

}
