package org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.dto.request;

import jakarta.validation.constraints.Email;

public class ProviderRequest {

    private String name;
    private String phone;
    @Email(message = "The email is mandatory!!")
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
}
