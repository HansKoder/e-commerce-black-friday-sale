package org.ecommerce.blackfriday.payment.infrastructure.client.gateway;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:9071")
public interface PaymentGatewayClient {

}
