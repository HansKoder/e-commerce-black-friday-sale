package org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.dto.response;

public class ProviderResponse {

    private String name;
    private String phone;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public static final class Builder {
        private String name;
        private String phone;
        private String email;

        private Builder() {
        }

        public static Builder aProvider() {
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

        public ProviderResponse build() {
            ProviderResponse providerResponse = new ProviderResponse();
            providerResponse.setName(name);
            providerResponse.setPhone(phone);
            providerResponse.setEmail(email);
            return providerResponse;
        }
    }
}
