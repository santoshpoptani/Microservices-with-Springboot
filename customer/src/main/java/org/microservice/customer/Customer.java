package org.microservice.customer;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Customer {
    private int id;
    private String name;
    private String lastname;
    private String email;

}
