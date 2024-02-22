package org.microservice.customer;

import org.microservice.amqp.RabbitMQPublisher;
import org.microservice.clients.fraud.FraudCheckResponse;
import org.microservice.clients.fraud.FraudClient;
import org.microservice.clients.fraud.Notification.NotificationClient;
import org.microservice.clients.fraud.Notification.NotificationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQPublisher rabbitMQPublisher;

    public CustomerService(CustomerRepository customerRepository, FraudClient fraudClient, RabbitMQPublisher rabbitMQPublisher) {
        this.customerRepository = customerRepository;
        this.fraudClient = fraudClient;
        this.rabbitMQPublisher = rabbitMQPublisher;
    }

    public void registerCustomer(CustomerRegistrationRequest customerRequest) {
        CustomerEntity customerEntity = CustomerEntity.builder()
                .name(customerRequest.name())
                .lastname(customerRequest.lastname())
                .email(customerRequest.email())
                .build();

        customerRepository.saveAndFlush(customerEntity);

/*
 we refactored the code with implementation of OpenFiegn
 We deleted the class FraudResponse



              FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraudcheck/{customerId}",
                FraudCheckResponse.class,
                customerEntity.getId()
        );*/

        
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customerEntity.getId());

        NotificationRequest notificationRequest = new NotificationRequest(customerEntity.getId(),
                customerEntity.getEmail(),
                String.format("Hi %s, welcome to microservices...",
                        customerEntity.getName()));

        rabbitMQPublisher.publish(notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key");


        if (fraudCheckResponse.isfraudster()) {
            throw new IllegalStateException("Fraduster");
        }


    }
}
