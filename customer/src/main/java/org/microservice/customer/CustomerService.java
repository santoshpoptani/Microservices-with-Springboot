package org.microservice.customer;

import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {

    public void registerCustomer(CustomerRegistrationRequest customerRequest) {
        CustomerEntity customerEntity = CustomerEntity.builder()
                .name(customerRequest.name())
                .lastname(customerRequest.lastname())
                .email(customerRequest.email())
                .build();

        //todo: validation for above fileds

        customerRepository.save(customerEntity);
    }
}
