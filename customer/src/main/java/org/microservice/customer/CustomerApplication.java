package org.microservice.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
@SpringBootApplication(
        scanBasePackages = {
                "org.microservice.customer",
                "org.microservice.amqp"
        }
)
@EnableDiscoveryClient
@EnableFeignClients(
        basePackages = "org.microservice.clients"
)
public class CustomerApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(CustomerApplication.class,args);
    }
}
