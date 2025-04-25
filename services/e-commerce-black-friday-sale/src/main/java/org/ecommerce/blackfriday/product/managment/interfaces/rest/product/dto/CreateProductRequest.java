package org.ecommerce.blackfriday.product.managment.interfaces.rest.product.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CreateProductRequest(
        @NotNull
        @NotEmpty
        @Length(min = 3)
        String productName,
        @NotNull
        @NotEmpty
        @Length(min = 10)
        String productDescription
) { }
