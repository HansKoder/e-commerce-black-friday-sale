package org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.exception;

public class PurchaseItemInvalidException extends RuntimeException{
    public PurchaseItemInvalidException(String productIds) {
        super("Purchase has some invalid products, ids: [" + productIds + "], the purchase does not continue until validate them");
    }
}
