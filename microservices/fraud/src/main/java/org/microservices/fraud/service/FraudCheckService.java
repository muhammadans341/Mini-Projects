package org.microservices.fraud.service;

import org.microservices.fraud.entity.FraudCheckHistory;
import org.microservices.fraud.repository.FraudCheckRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FraudCheckService {
    private final FraudCheckRepository fraudCheckRepository;

    public FraudCheckService(FraudCheckRepository fraudCheckRepository) {
        this.fraudCheckRepository = fraudCheckRepository;
    }

    public boolean isFraudulentCustomer(Long customerId){
        fraudCheckRepository.save(FraudCheckHistory.builder()
                .customerId(customerId)
                .isCustomerFraudster(false)
                .createdAt(LocalDate.now()).build());
        return false;
    }
}
