package org.microservice.fraud;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FraudCheckServices {
    private final  FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public FraudCheckServices(FraudCheckHistoryRepository fraudCheckHistoryRepository) {
        this.fraudCheckHistoryRepository = fraudCheckHistoryRepository;
    }

    public boolean isFradulentCutsomer(Integer customerId){
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraduster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}
