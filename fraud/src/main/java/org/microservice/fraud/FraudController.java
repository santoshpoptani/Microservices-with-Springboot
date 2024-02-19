package org.microservice.fraud;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraudcheck")
public class FraudController {

    private final FraudCheckServices fraudCheckServices;

    public FraudController(FraudCheckServices fraudCheckServices) {
        this.fraudCheckServices = fraudCheckServices;
    }

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId){

        boolean isFradulentCutsomer = fraudCheckServices.isFradulentCutsomer(customerId);
        return new FraudCheckResponse(isFradulentCutsomer);

    }
}
