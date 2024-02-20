package org.microservice.customer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    public CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
    }

    public void registerCustomer(CustomerRegistrationRequest customerRequest) {
        CustomerEntity customerEntity = CustomerEntity.builder()
                .name(customerRequest.name())
                .lastname(customerRequest.lastname())
                .email(customerRequest.email())
                .build();

        customerRepository.saveAndFlush(customerEntity);

        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraudcheck/{customerId}",
                FraudCheckResponse.class,
                customerEntity.getId()
        );
        if(fraudCheckResponse.isfraudster()){
            throw new IllegalStateException("Fraduster");
        }


    }
}
