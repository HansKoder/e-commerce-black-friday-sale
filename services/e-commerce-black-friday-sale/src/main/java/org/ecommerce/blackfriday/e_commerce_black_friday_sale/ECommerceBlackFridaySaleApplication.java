package org.ecommerce.blackfriday.e_commerce_black_friday_sale;

import org.ecommerce.blackfriday.e_commerce_black_friday_sale.redis.ClusterConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class ECommerceBlackFridaySaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceBlackFridaySaleApplication.class, args);
	}

}
