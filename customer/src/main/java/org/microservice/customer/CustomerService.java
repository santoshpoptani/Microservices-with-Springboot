package org.microservice.customer;

import org.springframework.stereotype.Service;

@Service
public record CustomerService() {

    public void registerCustomer(CustomerRegistrationRequest customerRequest) {
        Customer customer = Customer.builder()
                .name(customerRequest.name())
                .lastname(customerRequest.lastname())
                .email(customerRequest.email())
                .build();

        //todo: validation for above fileds
    }
}
