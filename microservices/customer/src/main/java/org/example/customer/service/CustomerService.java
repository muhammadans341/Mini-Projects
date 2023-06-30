package org.example.customer.service;

import org.example.customer.entitiy.CustomerEntity;
import org.example.customer.mapper.CustomerMapper;
import org.example.customer.model.requests.Customer;
import org.example.customer.model.responses.FraudCheckResponse;
import org.example.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    public CustomerService(CustomerMapper customerMapper, CustomerRepository customerRepository, RestTemplate restTemplate){
        this.customerRepository=customerRepository;
        this.customerMapper=customerMapper;
        this.restTemplate = restTemplate;
    }
    public void registerCustomer(Customer customer){
        CustomerEntity entity = customerMapper.customerToCustomerEntity(customer);
        CustomerEntity result = customerRepository.saveAndFlush(entity);

        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://localhost:8091/api/v1/fraud-check/{customerId}", FraudCheckResponse.class,entity.getId());
        if(fraudCheckResponse!=null && fraudCheckResponse.isFraudster()){
            throw new IllegalStateException();
        }

    }
}
