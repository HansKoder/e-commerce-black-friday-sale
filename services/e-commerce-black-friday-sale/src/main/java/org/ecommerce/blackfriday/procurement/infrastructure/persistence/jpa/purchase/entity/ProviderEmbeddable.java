package org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.purchase.entity;


import jakarta.persistence.Embeddable;

@Embeddable
public class ProviderEmbeddable {

    private String name;
    private String phone;
    private String email;

    public ProviderEmbeddable() {
    }

    public ProviderEmbeddable(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

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

        public ProviderEmbeddable build() {
            ProviderEmbeddable providerEmbeddable = new ProviderEmbeddable();
            providerEmbeddable.setName(name);
            providerEmbeddable.setPhone(phone);
            providerEmbeddable.setEmail(email);
            return providerEmbeddable;
        }
    }

    @Override
    public String toString() {
        return "ProviderEmbeddable{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
