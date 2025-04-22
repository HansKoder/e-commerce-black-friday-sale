package org.ecommerce.blackfriday.common.infraestructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenAPI () {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.description("Development");

        Contact contact = new Contact();
        contact.name("Hans Arias");

        Info info = new Info()
                .title("E-commerce Black Friday Sale System")
                .version("1.0.1")
                .description("This API exposes endpoints about an e-commerce which has as goal to replicate an event called black friday sale")
                .contact(contact);

        return new OpenAPI().info(info).servers(List.of(server));
    }

}
