package org.ecommerce.blackfriday.cart.domain.model.exception;

import org.ecommerce.blackfriday.common.domain.model.exception.DomainException;

public class ProductPriceIsMandatoryDomainException extends DomainException {

    public ProductPriceIsMandatoryDomainException () {
        super("Product price must be mandatory");
    }

}
