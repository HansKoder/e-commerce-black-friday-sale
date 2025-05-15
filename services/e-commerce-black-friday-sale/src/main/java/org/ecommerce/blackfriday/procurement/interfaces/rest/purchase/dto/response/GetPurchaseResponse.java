package org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record GetPurchaseResponse (
    String uuid,
    LocalDateTime date,
    ProviderResponse provider,
    String status,
    List<PurchaseItemResponse> items,
    BigDecimal total
) { }
