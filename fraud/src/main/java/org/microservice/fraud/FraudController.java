package org.microservice.fraud;

import lombok.extern.slf4j.Slf4j;
import org.microservice.clients.fraud.FraudCheckResponse;
import org.springframework.web.bind.annotation.*;

@Slf4j
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
        log.info("fraud check request for customer {}", customerId);
        return new FraudCheckResponse(isFradulentCutsomer);

    }
}
