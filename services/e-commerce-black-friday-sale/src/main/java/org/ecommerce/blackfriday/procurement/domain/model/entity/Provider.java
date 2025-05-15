package org.ecommerce.blackfriday.procurement.domain.model.entity;

import org.ecommerce.blackfriday.common.domain.model.entity.BaseEntity;
import org.ecommerce.blackfriday.procurement.domain.model.valueobject.ProviderId;

public class Provider extends BaseEntity<ProviderId> {
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

    public static final class ProviderBuilder {
        private String name;
        private String phone;
        private String email;

        private ProviderBuilder() {
        }

        public static ProviderBuilder aProvider() {
            return new ProviderBuilder();
        }

        public ProviderBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProviderBuilder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public ProviderBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Provider build() {
            Provider provider = new Provider();
            provider.setName(name);
            provider.setPhone(phone);
            provider.setEmail(email);
            return provider;
        }
    }
}
