package org.microservice.customer;

public record CustomerRegistrationRequest(
        String name,
        String lastname,
        String email) {


}
