package org.microservices.customer.controller;

import lombok.extern.slf4j.Slf4j;
import org.microservices.customer.model.requests.Customer;
import org.microservices.customer.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/customers")
@Slf4j
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }

    @PostMapping("/register")
    public void registerCustomer(@Valid @RequestBody Customer customer){
        log.info("Got request: "+customer);
        customerService.registerCustomer(customer);
    }
}
