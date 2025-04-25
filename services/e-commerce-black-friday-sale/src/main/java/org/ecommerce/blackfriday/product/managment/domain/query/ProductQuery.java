package org.ecommerce.blackfriday.product.managment.domain.query;

import org.ecommerce.blackfriday.common.domain.model.query.Pagination;

public class ProductQuery {

    private final String productName;
    private final String productDescription;
    private final Pagination pagination;

    private ProductQuery (ProductQueryBuilder builder) {
        this.productName = builder.productName;
        this.productDescription = builder.productDescription;
        this.pagination = builder.pagination;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public static final class ProductQueryBuilder {
        private String productName;
        private String productDescription;
        private Pagination pagination;

        private ProductQueryBuilder() {
        }

        public static ProductQueryBuilder aProductQuery() {
            return new ProductQueryBuilder();
        }

        public ProductQueryBuilder withProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public ProductQueryBuilder withProductDescription(String productDescription) {
            this.productDescription = productDescription;
            return this;
        }

        public ProductQueryBuilder withPagination(Pagination pagination) {
            this.pagination = pagination;
            return this;
        }

        public ProductQuery build() {
            return new ProductQuery(this);
        }
    }
}
