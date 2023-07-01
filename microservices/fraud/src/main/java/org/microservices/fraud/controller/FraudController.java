package org.microservices.fraud.controller;

import lombok.RequiredArgsConstructor;
import org.microservices.clients.fraud.FraudCheckResponse;
import org.microservices.fraud.service.FraudCheckService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fraud-check")
@RequiredArgsConstructor
public class FraudController {

    private final FraudCheckService fraudCheckService;

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isCustomerFraudster(@PathVariable Long customerId){
        boolean isFraudsterCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        return new FraudCheckResponse(isFraudsterCustomer);
    }
}
