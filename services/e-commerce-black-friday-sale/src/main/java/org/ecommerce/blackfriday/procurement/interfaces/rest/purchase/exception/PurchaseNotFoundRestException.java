package org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.exception;

public class PurchaseNotFoundRestException extends RuntimeException{

    public PurchaseNotFoundRestException(String purchaseId) {
        super("The Purchase with the ID " + purchaseId + " Does not found, you need to check");
    }
}
