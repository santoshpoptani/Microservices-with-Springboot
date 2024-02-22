package org.microservice.notification;

import org.microservice.amqp.RabbitMQPublisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(
        scanBasePackages = {
                "org.microservice.notification",
                "org.microservice.amqp"
        }
)
@EnableEurekaClient
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class,args);
    }

   /* @Bean
    CommandLineRunner commandLineRunner(RabbitMQPublisher mqPublisher, NotificationConfig notificationConfig){
        return args -> {
            mqPublisher.publish(new Person("James",59),
                    notificationConfig.getInternalExchange(),
                    notificationConfig.getInternalNoificationRoutingKey());
        };
    }

    record Person(String name , int age){}*/
}


