package org.ecommerce.blackfriday.cart.domain.model.valueobject;

import org.ecommerce.blackfriday.cart.domain.model.exception.PriceIsNegativeDomainException;
import org.ecommerce.blackfriday.cart.domain.model.exception.ProductPriceIsMandatoryDomainException;
import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;

import java.util.Objects;

public record ProductPrice (Money value) {

    public ProductPrice {
        if (Objects.isNull(value))
            throw new ProductPriceIsMandatoryDomainException();

        if (!value.isGreaterThanZero() )
            throw new PriceIsNegativeDomainException();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductPrice that = (ProductPrice) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
