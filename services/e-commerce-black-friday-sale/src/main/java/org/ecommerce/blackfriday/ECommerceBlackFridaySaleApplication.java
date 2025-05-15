package org.ecommerce.blackfriday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ECommerceBlackFridaySaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceBlackFridaySaleApplication.class, args);
	}

}
