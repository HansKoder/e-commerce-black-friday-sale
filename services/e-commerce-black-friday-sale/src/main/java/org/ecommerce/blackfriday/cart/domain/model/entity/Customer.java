package org.ecommerce.blackfriday.cart.domain.model.entity;

import org.ecommerce.blackfriday.cart.domain.model.valueobject.CustomerId;
import org.ecommerce.blackfriday.common.domain.model.entity.BaseEntity;

public class Customer extends BaseEntity<CustomerId> {

    private String name;
    private String phone;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static final class Builder {
        private String name;
        private String phone;
        private String email;
        private CustomerId customerId;

        private Builder() {
        }

        public static Builder aCustomer() {
            return new Builder();
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withId(CustomerId customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder but() {
            return aCustomer().withName(name).withPhone(phone).withEmail(email).withId(customerId);
        }

        public Customer build() {
            Customer customer = new Customer();
            customer.setName(name);
            customer.setPhone(phone);
            customer.setEmail(email);
            customer.setId(customerId);
            return customer;
        }
    }
}
