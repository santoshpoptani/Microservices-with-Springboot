package org.microservice.customer;

import org.microservice.clients.fraud.FraudCheckResponse;
import org.microservice.clients.fraud.FraudClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;

    public CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate, FraudClient fraudClient) {
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
        this.fraudClient = fraudClient;
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
        if (fraudCheckResponse.isfraudster()) {
            throw new IllegalStateException("Fraduster");
        }


    }
}
